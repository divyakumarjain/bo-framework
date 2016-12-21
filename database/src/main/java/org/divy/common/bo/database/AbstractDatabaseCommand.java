package org.divy.common.bo.database;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

import javax.persistence.EntityManager;

public abstract class AbstractDatabaseCommand<E extends IBusinessObject<I>, I>
{

    private final Class<E> entityType;
    private EntityManagerCommandContext context;


    protected AbstractDatabaseCommand(Class<E> typeParameterClass) {
        this.entityType = typeParameterClass;
    }

    /**
     * @return the entityType
     */
    public Class<E> getEntityType() {
        return entityType;
    }

    final void setContext(EntityManagerCommandContext context)
    {
        this.context = context;
    }

    protected final EntityManager getEntityManager()
    {
        return context.getEntityManager();
    }


    protected void transactionCommit()
    {
        context.commit();
    }

    protected void transactionBegin()
    {
        context.begin();
    }

    protected void transactionRollback() {
        context.rollBack();
    }

    protected E getReference(Object identity)
    {
        return getEntityManager().find(entityType, identity);
    }

    protected E find(Object identity)
    {
        return getEntityManager().find(entityType, identity);
    }
}
