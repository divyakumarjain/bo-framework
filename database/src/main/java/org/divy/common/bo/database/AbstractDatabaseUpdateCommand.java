package org.divy.common.bo.database;

import org.divy.common.bo.command.IUpdateCommand;

import java.util.Calendar;
import java.util.UUID;

import org.divy.common.bo.database.context.EntityManagerCommandContext;


public abstract class AbstractDatabaseUpdateCommand<E extends AbstractBusinessObject>
        extends AbstractDatabaseCommand<E, UUID> implements
        IUpdateCommand<E, UUID>
{
    protected AbstractDatabaseUpdateCommand(
            Class<E> typeParameterClass,EntityManagerCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    protected abstract void merge(E source, E target);

    @Override
    public E update(UUID id, E entity)
    {
        return doUpdate(id, entity);
    }

    /**
     *
     * @param id
     * @param entity
     * @return
     */
    private E doUpdate(UUID id, E entity) {

        transactionBegin();

        E fromPersistence = null;
        try {
            fromPersistence = getEntityManager().getReference(
                    getEntityType(), id);

            merge(entity, fromPersistence);

            getEntityManager().merge(fromPersistence);

            fromPersistence.setLastUpdateTimestamp(Calendar.getInstance());
        } catch (Exception e) {
            transactionRollback();
            throw e;
        } finally {
            transactionCommit();
        }



        return fromPersistence;
    }
}
