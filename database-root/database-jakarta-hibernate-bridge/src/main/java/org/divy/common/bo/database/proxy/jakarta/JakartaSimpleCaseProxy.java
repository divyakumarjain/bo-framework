package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Selection;

import java.util.Collection;
import java.util.List;

public class JakartaSimpleCaseProxy<C, R> implements CriteriaBuilder.SimpleCase<C, R> {
    private final javax.persistence.criteria.CriteriaBuilder.SimpleCase<C, R> actual;

    public JakartaSimpleCaseProxy( javax.persistence.criteria.CriteriaBuilder.SimpleCase<C, R> actual )
    {
        this.actual = actual;
    }

    @Override public Expression<C> getExpression()
    {
        return null;
    }

    @Override public CriteriaBuilder.SimpleCase<C, R> when( C condition, R result )
    {
        return null;
    }

    @Override public CriteriaBuilder.SimpleCase<C, R> when( C condition, Expression<? extends R> result )
    {
        return null;
    }

    @Override public Expression<R> otherwise( R result )
    {
        return null;
    }

    @Override public Expression<R> otherwise( Expression<? extends R> result )
    {
        return null;
    }

    @Override public Predicate isNull()
    {
        return null;
    }

    @Override public Predicate isNotNull()
    {
        return null;
    }

    @Override public Predicate in( Object... values )
    {
        return null;
    }

    @Override public Predicate in( Expression<?>... values )
    {
        return null;
    }

    @Override public Predicate in( Collection<?> values )
    {
        return null;
    }

    @Override public Predicate in( Expression<Collection<?>> values )
    {
        return null;
    }

    @Override public <X> Expression<X> as( Class<X> type )
    {
        return null;
    }

    @Override public Selection<R> alias( String name )
    {
        return null;
    }

    @Override public boolean isCompoundSelection()
    {
        return false;
    }

    @Override public List<Selection<?>> getCompoundSelectionItems()
    {
        return null;
    }

    @Override public Class<? extends R> getJavaType()
    {
        return null;
    }

    @Override public String getAlias()
    {
        return null;
    }
}
