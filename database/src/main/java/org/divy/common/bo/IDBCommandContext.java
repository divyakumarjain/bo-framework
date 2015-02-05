package org.divy.common.bo;

import javax.persistence.EntityManager;

import org.divy.common.bo.context.ICommandContext;

public interface IDBCommandContext extends ICommandContext
{
    EntityManager getEntityManager();
}
