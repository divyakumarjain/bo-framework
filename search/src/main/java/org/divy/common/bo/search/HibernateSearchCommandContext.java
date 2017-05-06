package org.divy.common.bo.search;

import org.divy.common.bo.context.CommandContext;
import org.hibernate.search.jpa.FullTextEntityManager;

public class HibernateSearchCommandContext implements SearchCommandContext {

    FullTextEntityManager fullTextEntityManager;

    CommandContext parentContext;

    @Override
    public CommandContext getParentContext() {
        return parentContext;
    }

    @Override
    public CommandContext createChildContext() {
        HibernateSearchCommandContext hibernateSearchCommandContext = new HibernateSearchCommandContext();
        hibernateSearchCommandContext.parentContext = this;
        return hibernateSearchCommandContext;
    }

    @Override
    public void commit() {
        throw new UnsupportedOperationException("Commit Not Implemented");
    }

    @Override
    public void begin() {
        throw new UnsupportedOperationException("Begin Not Implemented");
    }

    @Override
    public void rollBack() {
        throw new UnsupportedOperationException("RollBack Not Implemented");
    }
}
