package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Selection;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.javax.JavaXExpressionProxy;

import java.util.Collection;
import java.util.List;

public class JakartaPredicateProxy implements Predicate {
    private final javax.persistence.criteria.Predicate actual;

    public JakartaPredicateProxy( javax.persistence.criteria.Predicate actual )
    {
        this.actual = actual;
    }

    @Override public BooleanOperator getOperator()
    {
        return ProxyUtil.convertBooleanOperatorEnum( actual.getOperator() );
    }

    @Override public boolean isNegated()
    {
        return false;
    }

    @Override public List<Expression<Boolean>> getExpressions()
    {
        return ProxyUtil.convertListProxy( actual.getExpressions(), JakartaExpressionProxy::new );
    }

    @Override public Predicate not()
    {
        return new JakartaPredicateProxy( actual.not() );
    }

    @Override public Predicate isNull()
    {
        return new JakartaPredicateProxy( actual.isNull() );
    }

    @Override public Predicate isNotNull()
    {
        return new JakartaPredicateProxy( actual.isNotNull() );
    }

    @Override public Predicate in( Object... values )
    {
        return new JakartaPredicateProxy( actual.in( values ) );
    }

    @Override public Predicate in( Expression<?>... values )
    {
        return new JakartaPredicateProxy( actual.in( values ) );
    }

    @Override public Predicate in( Collection<?> values )
    {
        return new JakartaPredicateProxy( actual.in( values ) );
    }

    @Override public Predicate in( Expression<Collection<?>> values )
    {
        return new JakartaPredicateProxy( actual.in( new JavaXExpressionProxy<>( values ) ) );
    }

    @Override public <X> Expression<X> as( Class<X> type )
    {
        return new JakartaExpressionProxy<>( actual.as( type ) );
    }

    @Override public Selection<Boolean> alias( String name )
    {
        return new JakartaSelectionProxy<>( actual.alias( name ) );
    }

    @Override public boolean isCompoundSelection()
    {
        return false;
    }

    @Override public List<Selection<?>> getCompoundSelectionItems()
    {
        return ProxyUtil.convertListProxy( actual.getCompoundSelectionItems(), JakartaSelectionProxy::new );
    }

    @Override public Class<? extends Boolean> getJavaType()
    {
        return actual.getJavaType();
    }

    @Override public String getAlias()
    {
        return actual.getAlias();
    }
}
