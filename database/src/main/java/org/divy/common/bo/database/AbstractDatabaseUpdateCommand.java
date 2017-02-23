package org.divy.common.bo.database;

import org.divy.common.bo.command.IUpdateCommand;

import java.time.LocalDateTime;
import java.util.UUID;

import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.MapperBuilder;


public abstract class AbstractDatabaseUpdateCommand<E extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<E, UUID> implements
        IUpdateCommand<E, UUID> {

    private IBOMapper<E, E> updateMapper;

    protected AbstractDatabaseUpdateCommand( Class<E> typeParameterClass
            , EntityManagerCommandContext context
            , IBOMapper<E, E> updateMapper) {
        super(typeParameterClass);
        this.setContext(context);

        this.updateMapper = updateMapper;
    }

    protected AbstractDatabaseUpdateCommand(Class<E> typeParameterClass
            , EntityManagerCommandContext context
            , MapperBuilder mapperBuilder) {

        super(typeParameterClass);
        this.setContext(context);

        this.updateMapper = mapperBuilder.mapping(typeParameterClass, typeParameterClass)
                .build();
    }

    protected void merge(E source, E target) {
        if(source != target) {
            updateMapper.mapToBO(source, target);
        }
    }

    @Override
    public E update(UUID id, E entity)
    {
        return doUpdate(id, entity);
    }

    private E doUpdate(UUID id, E entity) {

        transactionBegin();

        E fromPersistence = null;
        try {
            fromPersistence = getEntityManager().getReference(
                    getEntityType(), id);

            merge(entity, fromPersistence);


            getEntityManager().merge(fromPersistence);

            fromPersistence.setLastUpdateTimestamp(LocalDateTime.now());
        } catch (Exception e) {
            transactionRollback();
            throw e;
        } finally {
            transactionCommit();
        }



        return fromPersistence;
    }
}
