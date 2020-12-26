package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.javax.JavaXEntityTypeProxy;
import org.divy.common.bo.database.proxy.javax.JavaXExpressionProxy;
import org.divy.common.bo.database.proxy.javax.JavaXPredicateProxy;

public class JakartaCriteriaDeleteProxy<T> implements CriteriaDelete<T> {
    private final javax.persistence.criteria.CriteriaDelete<T> actual;

    public JakartaCriteriaDeleteProxy( javax.persistence.criteria.CriteriaDelete<T> actual )
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

    @Override public CriteriaDelete<T> where( Expression<Boolean> restriction )
    {
        return new JakartaCriteriaDeleteProxy<>( actual.where( new JavaXExpressionProxy<>( restriction ) ) );
    }

    @Override public CriteriaDelete<T> where( Predicate... restrictions )
    {
        return  new JakartaCriteriaDeleteProxy<> (actual.where( ProxyUtil.convertArrayProxy(restrictions, JavaXPredicateProxy::new )));
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
