package org.divy.common.bo.database.command.impl;

import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.database.context.CommandContext;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.exception.NotFoundException;

import java.util.UUID;


public abstract class AbstractDatabaseUpdateCommand<E extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<E, UUID> implements
        org.divy.common.bo.database.command.UpdateCommand<E, UUID>
{

    private final BOMapper<E, E> updateMapper;

    protected AbstractDatabaseUpdateCommand( Class<E> typeParameterClass
            , CommandContext context
            , BOMapper<E, E> updateMapper) {
        super(typeParameterClass);
        this.setContext(context);

        this.updateMapper = updateMapper;
    }

    protected AbstractDatabaseUpdateCommand(Class<E> typeParameterClass
            , CommandContext context
            , MapperBuilder mapperBuilder) {

        super(typeParameterClass);
        this.setContext(context);

        this.updateMapper = mapperBuilder.mapping(typeParameterClass, typeParameterClass)
                .buildMapper();
    }

    private void merge(E source, E target) {
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


        E fromPersistence;
        var isUpdateSuccess = false;
        try {
            fromPersistence = getReference(id);

            if(fromPersistence == null) {
                throw new NotFoundException("Could not find " + getEntityType().getSimpleName() + " with id "+ id.toString());
            }

            merge(entity, fromPersistence);

            transactionBegin();

            doPersist(fromPersistence);

            fromPersistence.refreshLastUpdateTimestamp();
            isUpdateSuccess = true;

        } finally {
            if(isUpdateSuccess){
                transactionCommit();
            } else {
                transactionRollback();
            }
        }



        return fromPersistence;
    }

    protected abstract E getReference(UUID id);

    protected abstract void doPersist(E entity);
}
