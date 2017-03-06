package org.divy.common.bo.database;

import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

import java.time.OffsetDateTime ;
import java.util.UUID;

public abstract class AbstractDatabaseCreateCommand<E extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<E, UUID> implements
        ICreateCommand<E>
{
    protected AbstractDatabaseCreateCommand(
            Class<E> typeParameterClass, EntityManagerCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    protected void persist(E entity)
    {
        doPersist(entity);
    }

    /**
     * @param entity
     */
    private void doPersist(E entity) {
        transactionBegin();
        boolean operationSuccess = false;

        try {
            entity.setCreateTimestamp(OffsetDateTime .now());
            entity.setLastUpdateTimestamp(OffsetDateTime .now());
            getEntityManager().merge(entity);
            operationSuccess = true;
        } finally {
            if(operationSuccess){
                transactionCommit();
            } else {
                transactionRollback();
            }
        }
    }

    @Override
    public E create(E entity)
    {
        persist(entity);
        return entity;
    }
}
