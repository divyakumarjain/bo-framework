package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.*;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.javax.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JakartaCollectionJoinProxy<Z, ELEMENTYPE> implements CollectionJoin<Z, ELEMENTYPE> {
    private final javax.persistence.criteria.CollectionJoin<Z, ELEMENTYPE> actual;

    public JakartaCollectionJoinProxy( javax.persistence.criteria.CollectionJoin<Z, ELEMENTYPE> actual )
    {
        this.actual = actual;
    }

    @Override public CollectionJoin<Z, ELEMENTYPE> on( Expression<Boolean> restriction )
    {
        return new JakartaCollectionJoinProxy<>( actual.on( new JavaXExpressionProxy<>( restriction ) ) );
    }

    @Override public CollectionJoin<Z, ELEMENTYPE> on( Predicate... restrictions )
    {
        return new JakartaCollectionJoinProxy<>( actual.on( ProxyUtil.convertArrayProxy(  restrictions, JavaXPredicateProxy::new ) ) );
    }

    @Override public Predicate getOn()
    {
        return new JakartaPredicateProxy( actual.getOn() );
    }

    @Override public Attribute<? super Z, ?> getAttribute()
    {
        return new JakartaAttributeProxy<>( actual.getAttribute() );
    }

    @Override public From<?, Z> getParent()
    {
        return new JakartaFromProxy<>( actual.getParent() );
    }

    @Override public JoinType getJoinType()
    {
        return ProxyUtil.convertJoinType( actual.getJoinType() );
    }

    @Override public CollectionAttribute<? super Z, ELEMENTYPE> getModel()
    {
        return new JakartaCollectionAttributeProxy<>( actual.getModel() );
    }

    @Override public Path<?> getParentPath()
    {
        return new JakartaPathProxy<>( actual.getParentPath() );
    }

    @Override public <Y> Path<Y> get( SingularAttribute<? super ELEMENTYPE, Y> attribute )
    {
        return new JakartaPathProxy<>( actual.get( new JavaXSingularAttributeProxy<>( attribute ) ) );
    }

    @Override public <E, C extends Collection<E>> Expression<C> get( PluralAttribute<ELEMENTYPE, C, E> collection ) {
        return new JakartaExpressionProxy<>( actual.get( new JavaXPluralAttributeProxy<>( collection ) ) );
    }

    @Override public <K, V, M extends Map<K, V>> Expression<M> get( MapAttribute<ELEMENTYPE, K, V> map )
    {
        return new JakartaExpressionProxy<>( actual.get( new JavaXMapAttributeProxy<>( map ) ) );
    }

    @Override public Expression<Class<? extends ELEMENTYPE>> type()
    {
        return new JakartaExpressionProxy<>( actual.type() );
    }

    @Override public <Y> Path<Y> get( String attributeName )
    {
        return new JakartaPathProxy<>( actual.get( attributeName ) );
    }

    @Override public Set<Join<ELEMENTYPE, ?>> getJoins()
    {
        return ProxyUtil.convertSetProxy(actual.getJoins(), JakartaJoinProxy::new );
    }

    @Override public boolean isCorrelated()
    {
        return false;
    }

    @Override public From<Z, ELEMENTYPE> getCorrelationParent()
    {
        return new JakartaFromProxy<>( actual.getCorrelationParent() );
    }

    @Override public <Y> Join<ELEMENTYPE, Y> join( SingularAttribute<? super ELEMENTYPE, Y> attribute )
    {
        return new JakartaJoinProxy<>( actual.join( new JavaXSingularAttributeProxy<>( attribute ) ) );
    }

    @Override public <Y>Join<ELEMENTYPE, Y> join( SingularAttribute<? super ELEMENTYPE, Y> attribute, JoinType jt )
    {
        return new JakartaJoinProxy<>( actual.join( new JavaXSingularAttributeProxy<>( attribute ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <Y> CollectionJoin<ELEMENTYPE, Y> join( CollectionAttribute<? super ELEMENTYPE, Y> collection )
    {
        return new JakartaCollectionJoinProxy<>( actual.join( new JavaXCollectionAttributeProxy<>( collection ) ) );
    }

    @Override public <Y> SetJoin<ELEMENTYPE, Y> join( SetAttribute<? super ELEMENTYPE, Y> set )
    {
        return new JakartaSetJoinProxy<>( actual.join( new JavaXSetAttributeProxy<>( set ) ) );
    }

    @Override public <Y> ListJoin<ELEMENTYPE, Y> join( ListAttribute<? super ELEMENTYPE, Y> list )
    {
        return new JakartaListJoinProxy<>( actual.join( new JavaXListAttributeProxy<>( list ) ) );
    }

    @Override public  <K,V> MapJoin<ELEMENTYPE, K, V> join( MapAttribute<? super ELEMENTYPE, K, V> map )
    {
        return new JakartaMapJoinProxy<>( actual.join( new JavaXMapAttributeProxy<>( map ) ) );
    }

    @Override public <Y> CollectionJoin<ELEMENTYPE, Y> join( CollectionAttribute<? super ELEMENTYPE, Y> collection, JoinType jt )
    {
        return new JakartaCollectionJoinProxy<>( actual.join( new JavaXCollectionAttributeProxy<>( collection ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <Y> SetJoin<ELEMENTYPE, Y> join( SetAttribute<? super ELEMENTYPE, Y> set, JoinType jt )
    {
        return new JakartaSetJoinProxy<>( actual.join( new JavaXSetAttributeProxy<>( set ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <Y> ListJoin<ELEMENTYPE, Y> join( ListAttribute<? super ELEMENTYPE, Y> list, JoinType jt )
    {
        return new JakartaListJoinProxy<>( actual.join( new JavaXListAttributeProxy<>( list ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public < K,V> MapJoin<ELEMENTYPE, K, V> join( MapAttribute<? super ELEMENTYPE, K, V> map, JoinType jt )
    {
        return new JakartaMapJoinProxy<>( actual.join( new JavaXMapAttributeProxy<>( map ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <X1, Y> Join<X1, Y> join( String attributeName )
    {
        return new JakartaJoinProxy<>( actual.join( attributeName ) );
    }

    @Override public <X1, Y> CollectionJoin<X1, Y> joinCollection( String attributeName )
    {
        return new JakartaCollectionJoinProxy<>( actual.joinCollection( attributeName ) );
    }

    @Override public <X1, Y> SetJoin<X1, Y> joinSet( String attributeName )
    {
        return new JakartaSetJoinProxy<>( actual.joinSet( attributeName ) );
    }

    @Override public <X1, Y> ListJoin<X1, Y> joinList( String attributeName )
    {
        return new JakartaListJoinProxy<>( actual.joinList( attributeName ) );
    }

    @Override public <X1, K, V> MapJoin<X1, K, V> joinMap( String attributeName )
    {
        return new JakartaMapJoinProxy<>( actual.joinMap( attributeName ) );
    }

    @Override public <X1, Y> Join<X1, Y> join( String attributeName, JoinType jt )
    {
        return new JakartaJoinProxy<>( actual.join( attributeName, ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <X1, Y> CollectionJoin<X1, Y> joinCollection( String attributeName, JoinType jt )
    {
        return new JakartaCollectionJoinProxy<>( actual.joinCollection( attributeName, ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <X1, Y> SetJoin<X1, Y> joinSet( String attributeName, JoinType jt )
    {
        return new JakartaSetJoinProxy<>( actual.joinSet( attributeName, ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <X1, Y> ListJoin<X1, Y> joinList( String attributeName, JoinType jt )
    {
        return new JakartaListJoinProxy<>( actual.joinList( attributeName, ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <X1, K, V> MapJoin<X1, K, V> joinMap( String attributeName, JoinType jt )
    {
        return new JakartaMapJoinProxy<>( actual.joinMap( attributeName, ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public Set<Fetch<ELEMENTYPE, ?>> getFetches()
    {
        return ProxyUtil.convertSetProxy(actual.getFetches(), JakartaFetchProxy::new);
    }

    @Override public <Y> Fetch<ELEMENTYPE, Y> fetch( SingularAttribute<? super ELEMENTYPE, Y> attribute )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXSingularAttributeProxy<>( attribute ) ) );
    }

    @Override public <Y> Fetch<ELEMENTYPE, Y> fetch( SingularAttribute<? super ELEMENTYPE, Y> attribute, JoinType jt )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXSingularAttributeProxy<>( attribute ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <Y> Fetch<ELEMENTYPE, Y> fetch( PluralAttribute<? super ELEMENTYPE, ?, Y> attribute )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXPluralAttributeProxy<>( attribute ) ) );
    }

    @Override public <Y> Fetch<ELEMENTYPE, Y> fetch( PluralAttribute<? super ELEMENTYPE, ?, Y> attribute, JoinType jt )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXPluralAttributeProxy<>( attribute ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String attributeName )
    {
        return new JakartaFetchProxy<>( actual.fetch( attributeName ) );
    }

    @Override public <X1,Y> Fetch<X1, Y> fetch( String attributeName, JoinType jt )
    {
        return new JakartaFetchProxy<>( actual.fetch( attributeName, ProxyUtil.convertJoinType( jt ) ) );
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

    @Override public Selection<ELEMENTYPE> alias( String name )
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

    @Override public Class<? extends ELEMENTYPE> getJavaType()
    {
        return actual.getJavaType();
    }

    @Override public String getAlias()
    {
        return actual.getAlias();
    }
}
