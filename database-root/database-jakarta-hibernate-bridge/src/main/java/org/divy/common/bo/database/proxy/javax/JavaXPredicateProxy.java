package org.divy.common.bo.database.proxy.javax;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import java.util.Collection;
import java.util.List;

public class JavaXPredicateProxy implements Predicate {
    private final jakarta.persistence.criteria.Predicate actual;

    public JavaXPredicateProxy( jakarta.persistence.criteria.Predicate actual )
    {
        this.actual = actual;
    }

    @Override public BooleanOperator getOperator()
    {
        return null;
    }

    @Override public boolean isNegated()
    {
        return false;
    }

    @Override public List<Expression<Boolean>> getExpressions()
    {
        return null;
    }

    @Override public Predicate not()
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

    @Override public Predicate in( Object... objects )
    {
        return null;
    }

    @Override public Predicate in( Expression<?>... expressions )
    {
        return null;
    }

    @Override public Predicate in( Collection<?> collection )
    {
        return null;
    }

    @Override public Predicate in( Expression<Collection<?>> expression )
    {
        return null;
    }

    @Override public <X> Expression<X> as( Class<X> aClass )
    {
        return null;
    }

    @Override public Selection<Boolean> alias( String s )
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

    @Override public Class<? extends Boolean> getJavaType()
    {
        return null;
    }

    @Override public String getAlias()
    {
        return null;
    }
}
