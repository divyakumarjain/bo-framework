package org.divy.common.bo.database.jpa;

import org.divy.common.bo.database.context.CommandContext;

import javax.persistence.EntityManager;

public interface EntityManagerCommandContext extends CommandContext
{
    EntityManager getEntityManager();
}
