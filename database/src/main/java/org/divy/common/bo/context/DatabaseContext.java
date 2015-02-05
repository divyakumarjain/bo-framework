package org.divy.common.bo.context;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.divy.common.bo.IDBCommandContext;

public class DatabaseContext implements IDBCommandContext
{

    private String persistenceUnitName;

    private ICommandContext parentContext;

    private EntityTransaction transaction;

    private EntityManager entityManager;

    protected String getPersistenceUnitName()
    {
        return persistenceUnitName;
    }

    protected void setPersistenceUnitName(String persistenceUnitName)
    {
        this.persistenceUnitName = persistenceUnitName;
    }

    public DatabaseContext(String persistenceUnitName)
    {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    public EntityManager getEntityManager()
    {
        if(entityManager==null)
        {
            if (parentContext instanceof DatabaseContext)
            {
                DatabaseContext dbParentContext = (DatabaseContext) parentContext;
                entityManager = dbParentContext.getEntityManager();

                if (entityManager != null) {
                    return entityManager;
                }
            }

            entityManager = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        }
        return entityManager;
    }

    @Override
    public ICommandContext getParentContext()
    {
        return parentContext;
    }


    @Override
    public ICommandContext createChildContext()
    {
        DatabaseContext childContext = new DatabaseContext(getPersistenceUnitName());

        childContext.parentContext = this;

        return childContext;
    }

    @Override
    public void commit()
    {
            transaction.commit();
    }

    @Override
    public void begin()
    {
        transaction = getEntityManager().getTransaction();
        transaction.begin();
    }
}
