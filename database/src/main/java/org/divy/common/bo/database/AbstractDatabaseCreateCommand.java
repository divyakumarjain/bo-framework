package org.divy.common.bo.database;

import org.divy.common.bo.command.ICreateCommand;

import java.util.Calendar;
import java.util.UUID;
import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

public abstract class AbstractDatabaseCreateCommand<E extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<E, UUID> implements
        ICreateCommand<E>
{
    protected AbstractDatabaseCreateCommand(
            Class<? extends E> typeParameterClass, EntityManagerCommandContext context)
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
        entity.setCreateTimestamp(Calendar.getInstance());
        entity.setLastUpdateTimestamp(Calendar.getInstance());
        getEntityManager().merge(entity);

        transactionCommit();
    }

    @Override
    public E create(E entity)
    {
        persist(entity);
        return entity;
    }
}
