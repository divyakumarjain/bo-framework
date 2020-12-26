package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.javax.*;

public class JakartaCriteriaUpdateProxy<T> implements CriteriaUpdate<T> {
    private final javax.persistence.criteria.CriteriaUpdate<T> actual;

    public JakartaCriteriaUpdateProxy( javax.persistence.criteria.CriteriaUpdate<T> actual )
    {
        this.actual = actual;
    }

    @Override public Root<T> from( Class<T> entityClass )
    {
        return new JakartaRootProxy<>( actual.from( entityClass ) );
    }

    @Override public Root<T> from( EntityType<T> entity )
    {
        return new JakartaRootProxy<>( actual.from( new JavaXEntityTypeProxy<>( entity ) ) );
    }

    @Override public Root<T> getRoot()
    {
        return new JakartaRootProxy<>( actual.getRoot() );
    }

    @Override public <Y, X extends Y> CriteriaUpdate<T> set( SingularAttribute<? super T, Y> attribute, X value )
    {
        return new JakartaCriteriaUpdateProxy<>( actual.set( new JavaXSingularAttributeProxy<>( attribute ), value ) );
    }

    @Override public <Y> CriteriaUpdate<T> set( SingularAttribute<? super T, Y> attribute, Expression<? extends Y> value )
    {
        return new JakartaCriteriaUpdateProxy<>( actual.set( new JavaXSingularAttributeProxy<>( attribute ), new JavaXExpressionProxy<>( value ) ) );
    }

    @Override public <Y, X extends Y> CriteriaUpdate<T> set( Path<Y> attribute, X value )
    {
        return new JakartaCriteriaUpdateProxy<>( actual.set( new JavaXPathProxy<>( attribute ), value ) );
    }

    @Override public <Y> CriteriaUpdate<T> set( Path<Y> attribute, Expression<? extends Y> value )
    {
        return new JakartaCriteriaUpdateProxy<>( actual.set( new JavaXPathProxy<>( attribute ), new JavaXExpressionProxy<>( value ) ) );
    }

    @Override public CriteriaUpdate<T> set( String attributeName, Object value )
    {
        return new JakartaCriteriaUpdateProxy<>( actual.set( attributeName, value ) );
    }

    @Override public CriteriaUpdate<T> where( Expression<Boolean> restriction )
    {
        return new JakartaCriteriaUpdateProxy<>( actual.where( new JavaXExpressionProxy<>( restriction ) ) );
    }

    @Override public CriteriaUpdate<T> where( Predicate... restrictions )
    {
        return new JakartaCriteriaUpdateProxy<>( actual.where( ProxyUtil.convertArrayProxy(  restrictions, JavaXPredicateProxy::new )) );
    }

    @Override public <U> Subquery<U> subquery( Class<U> type )
    {
        return new JakartaSubqueryProxy<>( actual.subquery( type ) );
    }

    @Override public Predicate getRestriction()
    {
        return new JakartaPredicateProxy( actual.getRestriction() );
    }
}
