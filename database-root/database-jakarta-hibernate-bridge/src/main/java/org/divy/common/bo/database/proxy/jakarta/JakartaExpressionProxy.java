package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Selection;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.javax.JavaXExpressionProxy;

import java.util.Collection;
import java.util.List;

public class JakartaExpressionProxy<T> implements Expression<T> {
    private final javax.persistence.criteria.Expression<T> actual;

    public JakartaExpressionProxy( javax.persistence.criteria.Expression<T> actual )
    {
        this.actual = actual;
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
        return new JakartaPredicateProxy( actual.in( ProxyUtil.convertArrayProxy( values, JavaXExpressionProxy::new ) ) );
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

    @Override public Selection<T> alias( String name )
    {
        return new JakartaSelectionProxy<>( actual.alias( name ) );
    }

    @Override public boolean isCompoundSelection()
    {
        return false;
    }

    @Override public List<Selection<?>> getCompoundSelectionItems()
    {
        return ProxyUtil.convertListProxy( actual.getCompoundSelectionItems(), JakartaSetJoinProxy::new );
    }

    @Override public Class<? extends T> getJavaType()
    {
        return actual.getJavaType();
    }

    @Override public String getAlias()
    {
        return actual.getAlias();
    }
}
