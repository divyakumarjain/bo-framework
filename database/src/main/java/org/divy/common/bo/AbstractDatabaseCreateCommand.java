package org.divy.common.bo;

import java.util.Calendar;
import java.util.UUID;
import org.divy.common.bo.command.ICreateCommand;

public abstract class AbstractDatabaseCreateCommand<ENTITY extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<ENTITY, UUID> implements
        ICreateCommand<ENTITY, UUID>
{
    protected AbstractDatabaseCreateCommand(
            Class<? extends ENTITY> typeParameterClass, IDBCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    protected void persist(ENTITY entity)
    {
        doPersist(entity);
    }

    /**
     * @param entity
     */
    private void doPersist(ENTITY entity) {
        transactionBegin();
        entity.setCreateTimestamp(Calendar.getInstance());
        getEntityManager().merge(entity);

        transactionCommit();
    }

    public ENTITY create(ENTITY entity)
    {
        persist(entity);
        return entity;
    }
}
