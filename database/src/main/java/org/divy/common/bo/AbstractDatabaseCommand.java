package org.divy.common.bo;

import javax.persistence.EntityManager;

public abstract class AbstractDatabaseCommand<E extends IBusinessObject<I>, I>
{

    private final Class<? extends E> entityType;
    private IDBCommandContext context;


    protected AbstractDatabaseCommand(Class<? extends E> typeParameterClass) {
        this.entityType = typeParameterClass;
    }

    /**
     * @return the entityType
     */
    public Class<? extends E> getEntityType() {
        return entityType;
    }

    protected void setContext(IDBCommandContext context)
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

    protected E getReference(Object identity)
    {

        E entity = getEntityManager().find(entityType, identity);

        return entity;
    }

    protected E find(Object identity)
    {
        E entity = getEntityManager().find(entityType, identity);

        return entity;
    }
}
