package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.javax.JavaXEntityTypeProxy;
import org.divy.common.bo.database.proxy.javax.JavaXExpressionProxy;
import org.divy.common.bo.database.proxy.javax.JavaXPredicateProxy;
import org.divy.common.bo.database.proxy.javax.JavaXSelectionProxy;

import java.util.List;
import java.util.Set;

class JakartaCriteriaQueryProxy<T> implements CriteriaQuery<T> {
    javax.persistence.criteria.CriteriaQuery<T> actual;

    public JakartaCriteriaQueryProxy( javax.persistence.criteria.CriteriaQuery<T> actual )
    {
        this.actual = actual;
    }

    @Override public CriteriaQuery<T> select( Selection<? extends T> selection )
    {
        return new JakartaCriteriaQueryProxy<>( actual.select( new JavaXSelectionProxy<>( selection ) ) );
    }

    @Override public CriteriaQuery<T> multiselect( Selection<?>... selections )
    {
        return new JakartaCriteriaQueryProxy<>( actual.multiselect( ProxyUtil.convertArrayProxy( selections, JavaXSelectionProxy::new ) ) );
    }

    @Override public CriteriaQuery<T> multiselect( List<Selection<?>> selectionList )
    {
        return new JakartaCriteriaQueryProxy<>( actual.multiselect( ProxyUtil.convertListProxy( selectionList, JavaXSelectionProxy::new ) ) );
    }

    @Override public <X> Root<X> from( Class<X> entityClass )
    {
        return new JakartaRootProxy<>( actual.from( entityClass ) );
    }

    @Override public <X> Root<X> from( EntityType<X> entity )
    {
        return new JakartaRootProxy<>( actual.from( new JavaXEntityTypeProxy<>( entity ) ) );
    }

    @Override public CriteriaQuery<T> where( Expression<Boolean> restriction )
    {
        return new JakartaCriteriaQueryProxy<>( actual.where( new JavaXExpressionProxy<>( restriction ) ) );
    }

    @Override public CriteriaQuery<T> where( Predicate... restrictions )
    {
        return new JakartaCriteriaQueryProxy<>( actual.where( ProxyUtil.convertArrayProxy(  restrictions, JavaXPredicateProxy::new) ) );
    }

    @Override public CriteriaQuery<T> groupBy( Expression<?>... grouping )
    {
        return new JakartaCriteriaQueryProxy<>( actual.groupBy( ProxyUtil.convertArrayProxy(grouping, JavaXExpressionProxy::new) ) );
    }

    @Override public CriteriaQuery<T> groupBy( List<Expression<?>> grouping )
    {
        return new JakartaCriteriaQueryProxy<>( actual.groupBy( ProxyUtil.convertListProxy( grouping, JavaXExpressionProxy::new ) ) );
    }

    @Override public CriteriaQuery<T> having( Expression<Boolean> restriction )
    {
        return new JakartaCriteriaQueryProxy<>( actual.having( new JavaXExpressionProxy<>( restriction ) ) );
    }

    @Override public CriteriaQuery<T> having( Predicate... restrictions )
    {
        return new JakartaCriteriaQueryProxy<>( actual.having( ProxyUtil.convertArrayProxy(restrictions, JavaXPredicateProxy::new ) ));
    }

    @Override public CriteriaQuery<T> orderBy( Order... o )
    {
        return new JakartaCriteriaQueryProxy<>( actual.orderBy( ProxyUtil.convertArrayProxy(o, JavaXOrder::new) ) );
    }

    @Override public CriteriaQuery<T> orderBy( List<Order> o )
    {
        return new JakartaCriteriaQueryProxy<>( actual.orderBy( ProxyUtil.convertListProxy( o, JavaXOrder::new ) ) );
    }

    @Override public CriteriaQuery<T> distinct( boolean distinct )
    {
        return new JakartaCriteriaQueryProxy<>( actual.distinct( distinct ) );
    }

    @Override public Set<Root<?>> getRoots()
    {
        return ProxyUtil.convertSetProxy( actual.getRoots(), JakartaRootProxy::new );
    }

    @Override public Selection<T> getSelection()
    {
        return new JakartaSelectionProxy<>( actual.getSelection() );
    }

    @Override public List<Expression<?>> getGroupList()
    {
        return ProxyUtil.convertListProxy( actual.getGroupList(), JakartaExpressionProxy::new );
    }

    @Override public Predicate getGroupRestriction()
    {
        return new JakartaPredicateProxy( actual.getGroupRestriction() );
    }

    @Override public boolean isDistinct()
    {
        return false;
    }

    @Override public Class<T> getResultType()
    {
        return actual.getResultType();
    }

    @Override public List<Order> getOrderList()
    {
        return ProxyUtil.convertListProxy( actual.getOrderList(), JakartaOrderProxy::new);
    }

    @Override public Set<ParameterExpression<?>> getParameters()
    {
        return ProxyUtil.convertSetProxy( actual.getParameters(), JakartaParameterExpressionProxy::new );
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
