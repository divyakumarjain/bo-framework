package org.divy.common.bo.database.context;

import org.divy.common.bo.context.CommandContext;
import org.divy.common.bo.context.HierarchicalCommandContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by divyjain on 12/6/2016.
 */
public abstract class AbstractEntityManagerContext implements EntityManagerCommandContext, HierarchicalCommandContext {

    protected EntityManager entityManager;
    protected CommandContext parentContext;
    private EntityTransaction transaction;

    public void commit()
    {
        if(transaction.isActive()){
            transaction.commit();
        }
    }

    public void begin()
    {
        transaction = getEntityManager().getTransaction();
        transaction.begin();
    }

    public void rollBack() {
        transaction.rollback();
    }

    public CommandContext getParentContext()
    {
        return parentContext;
    }
}
