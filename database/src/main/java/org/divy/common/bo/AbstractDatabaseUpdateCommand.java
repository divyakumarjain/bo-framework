package org.divy.common.bo;

import java.util.Calendar;
import java.util.UUID;
import org.divy.common.bo.command.IUpdateCommand;


public abstract class AbstractDatabaseUpdateCommand<ENTITY extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<ENTITY, UUID> implements
        IUpdateCommand<ENTITY, UUID>
{
    protected AbstractDatabaseUpdateCommand(
            Class<? extends ENTITY> typeParameterClass,IDBCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    protected abstract void merge(ENTITY source, ENTITY target);

    @Override
    public ENTITY update(ENTITY entity)
    {
        return doUpdate(entity);
    }

    /**
     * @param entity
     * @return
     */
    private ENTITY doUpdate(ENTITY entity) {

        transactionBegin();

        ENTITY fromPersistence = getEntityManager().getReference(
                getEntityType(), entity.identity());

        merge(entity, fromPersistence);

        getEntityManager().merge(fromPersistence);

        fromPersistence.setLastUpdateTimestamp(Calendar.getInstance());

        transactionCommit();

        return fromPersistence;
    }
}
