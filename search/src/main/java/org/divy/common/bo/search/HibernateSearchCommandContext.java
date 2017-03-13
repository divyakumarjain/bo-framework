package org.divy.common.bo.search;

import org.divy.common.bo.context.CommandContext;
import org.hibernate.search.jpa.FullTextEntityManager;

//TODO
public class HibernateSearchCommandContext implements SearchCommandContext {

    FullTextEntityManager fullTextEntityManager;

    @Override
    public CommandContext getParentContext() {
        return null;
    }

    @Override
    public CommandContext createChildContext() {
//        HibernateSearchCommandContext hibernateSearchCommandContext = new HibernateSearchCommandContext();
//        hibernateSearchCommandContext.parentContext = this;
        return null;
    }

    @Override
    public void commit() {

    }

    @Override
    public void begin() {

    }

    @Override
    public void rollBack() {

    }
}
