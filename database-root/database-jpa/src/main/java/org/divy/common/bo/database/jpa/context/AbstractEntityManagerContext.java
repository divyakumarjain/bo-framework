package org.divy.common.bo.database.jpa.context;

import org.divy.common.bo.context.CommandContext;
import org.divy.common.bo.context.HierarchicalCommandContext;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public abstract class AbstractEntityManagerContext implements EntityManagerCommandContext, HierarchicalCommandContext {

    protected EntityManager entityManager;
    protected CommandContext parentContext;
    private EntityTransaction transaction;

    @Override
    public void commit()
    {
        if(transaction.isActive()){
            transaction.commit();
        }
    }

    @Override
    public void begin()
    {
        transaction = getEntityManager().getTransaction();
        transaction.begin();
    }

    @Override
    public void rollBack() {
        transaction.rollback();
    }

    @Override
    public CommandContext getParentContext()
    {
        return parentContext;
    }
}
