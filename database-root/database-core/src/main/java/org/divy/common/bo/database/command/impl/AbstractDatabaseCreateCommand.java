package org.divy.common.bo.database.command.impl;

import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.database.command.CreateCommand;
import org.divy.common.bo.database.context.CommandContext;

import java.util.UUID;

public abstract class AbstractDatabaseCreateCommand<E extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<E, UUID> implements CreateCommand<E>
{
    protected AbstractDatabaseCreateCommand(
            Class<E> typeParameterClass, CommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    protected void persist(E entity)
    {
        transactionBegin();
        var operationSuccess = false;

        try {
            entity.refreshCreateUpdateTimestamp();
            entity.refreshLastUpdateTimestamp();
            this.doPersist(entity);
            operationSuccess = true;
        } finally {
            if(operationSuccess){
                transactionCommit();
            } else {
                transactionRollback();
            }
        }
    }

    protected abstract void doPersist(E entity);

    @Override
    public E create(E entity)
    {
        persist(entity);
        return entity;
    }
}
