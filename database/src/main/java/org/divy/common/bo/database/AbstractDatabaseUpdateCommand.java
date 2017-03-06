package org.divy.common.bo.database;

import org.divy.common.bo.command.IUpdateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.MapperBuilder;

import java.time.OffsetDateTime ;
import java.util.UUID;


public abstract class AbstractDatabaseUpdateCommand<E extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<E, UUID> implements
        IUpdateCommand<E, UUID> {

    private final IBOMapper<E, E> updateMapper;

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
                .buildMapper();
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

        E fromPersistence;
        boolean isUpdateSuccess = false;
        try {
            fromPersistence = getEntityManager().getReference(
                    getEntityType(), id);

            merge(entity, fromPersistence);

            getEntityManager().merge(fromPersistence);

            fromPersistence.setLastUpdateTimestamp(OffsetDateTime .now());
            isUpdateSuccess = true;

        } catch (Exception e) {
            transactionRollback();
            throw e;
        } finally {
            if(isUpdateSuccess){
                transactionCommit();
            }
        }



        return fromPersistence;
    }
}
