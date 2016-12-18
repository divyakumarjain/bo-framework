package org.divy.common.bo.database.context;

import org.divy.common.bo.context.CommandContext;

import javax.persistence.EntityManager;

public interface EntityManagerCommandContext extends CommandContext
{
    EntityManager getEntityManager();
}
