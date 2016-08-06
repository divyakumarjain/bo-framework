package org.divy.common.bo;

import org.divy.common.bo.command.ICreateCommand;

import java.util.Calendar;
import java.util.UUID;

public abstract class AbstractDatabaseCreateCommand<E extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<E, UUID> implements
        ICreateCommand<E, UUID>
{
    protected AbstractDatabaseCreateCommand(
            Class<? extends E> typeParameterClass, IDBCommandContext context)
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
