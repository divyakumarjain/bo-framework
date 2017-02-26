package org.divy.common.bo.search;

import org.divy.common.bo.context.CommandContext;
import org.divy.common.bo.database.context.AbstractEntityManagerContext;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.hibernate.search.jpa.FullTextEntityManager;

import javax.persistence.EntityManager;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;


public class HibernateSearchCommandContext extends AbstractEntityManagerContext implements SearchCommandContext {

    FullTextEntityManager fullTextEntityManager;

    @Override
    public EntityManager getEntityManager() {
        if(fullTextEntityManager ==null){
            fullTextEntityManager = getFullTextEntityManager(((EntityManagerCommandContext) getParentContext()).getEntityManager());
        }

        return fullTextEntityManager;
    }

    @Override
    public CommandContext createChildContext() {
        HibernateSearchCommandContext hibernateSearchCommandContext = new HibernateSearchCommandContext();
        hibernateSearchCommandContext.parentContext = this;
        return hibernateSearchCommandContext;
    }
}
