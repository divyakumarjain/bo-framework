package org.divy.common.bo.database.jpa.context;

import org.divy.common.bo.database.context.CommandContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DatabaseContext extends AbstractEntityManagerContext {

    private String persistenceUnitName;


    public DatabaseContext(String persistenceUnitName)
    {
        this.persistenceUnitName = persistenceUnitName;
    }

    protected String getPersistenceUnitName()
    {
        return persistenceUnitName;
    }

    protected void setPersistenceUnitName(String persistenceUnitName)
    {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    public EntityManager getEntityManager()
    {
        if(entityManager==null)
        {
            if (getParentContext() instanceof AbstractEntityManagerContext)
            {
                AbstractEntityManagerContext emContext = (AbstractEntityManagerContext) getParentContext();
                entityManager = emContext.getEntityManager();

                if (entityManager != null) {
                    return entityManager;
                }
            }

            entityManager = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        }
        return entityManager;
    }


    @Override
    public CommandContext createChildContext()
    {
        DatabaseContext childContext = new DatabaseContext(getPersistenceUnitName());

        childContext.parentContext = this;

        return childContext;
    }

}
