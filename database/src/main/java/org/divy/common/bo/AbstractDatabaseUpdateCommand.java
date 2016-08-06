package org.divy.common.bo;

import org.divy.common.bo.command.IUpdateCommand;

import java.util.Calendar;
import java.util.UUID;


public abstract class AbstractDatabaseUpdateCommand<E extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<E, UUID> implements
        IUpdateCommand<E, UUID>
{
    protected AbstractDatabaseUpdateCommand(
            Class<? extends E> typeParameterClass, IDBCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    protected abstract void merge(E source, E target);

    @Override
    public E update(E entity)
    {
        return doUpdate(entity);
    }

    /**
     * @param entity
     * @return
     */
    private E doUpdate(E entity) {

        transactionBegin();

        E fromPersistence = getEntityManager().getReference(
                getEntityType(), entity.identity());

        merge(entity, fromPersistence);

        getEntityManager().merge(fromPersistence);

        fromPersistence.setLastUpdateTimestamp(Calendar.getInstance());

        transactionCommit();

        return fromPersistence;
    }
}
