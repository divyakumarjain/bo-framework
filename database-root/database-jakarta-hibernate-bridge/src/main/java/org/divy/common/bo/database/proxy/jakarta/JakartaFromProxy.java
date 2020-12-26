package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.*;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.javax.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JakartaFromProxy<X, Y> implements From<X, Y> {
    private final javax.persistence.criteria.From<X, Y> actual;

    public JakartaFromProxy( javax.persistence.criteria.From<X, Y> actual )
    {
        this.actual = actual;
    }

    @Override public Set<Join<Y, ?>> getJoins()
    {
        return null;
    }

    @Override public boolean isCorrelated()
    {
        return false;
    }

    @Override public From<X, Y> getCorrelationParent()
    {
        return null;
    }

    @Override public <Y1> Join<Y, Y1> join( SingularAttribute<? super Y, Y1> attribute )
    {
        return new JakartaJoinProxy<>( actual.join( new JavaXSingularAttributeProxy<>( attribute ) ) );
    }

    @Override public <Y1> Join<Y, Y1> join( SingularAttribute<? super Y, Y1> attribute, JoinType jt )
    {
        return new JakartaJoinProxy<>( actual.join( new JavaXSingularAttributeProxy<>( attribute ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <Y1> CollectionJoin<Y, Y1> join( CollectionAttribute<? super Y, Y1> collection )
    {
        return new JakartaCollectionJoinProxy<>( actual.join( new JavaXCollectionAttributeProxy<>( collection ) ) );
    }

    @Override public <Y1> SetJoin<Y, Y1> join( SetAttribute<? super Y, Y1> set )
    {
        return new JakartaSetJoinProxy<>( actual.join( new JavaXSetAttributeProxy<>( set ) ) );
    }

    @Override public <Y1> ListJoin<Y, Y1> join( ListAttribute<? super Y, Y1> list )
    {
        return new JakartaListJoinProxy<>( actual.join( new JavaXListAttributeProxy<>( list ) ) );
    }

    @Override public <K, V> MapJoin<Y, K, V> join( MapAttribute<? super Y, K, V> map )
    {
        return null;
    }

    @Override public <Y1> CollectionJoin<Y, Y1> join( CollectionAttribute<? super Y, Y1> collection, JoinType jt )
    {
        return new JakartaCollectionJoinProxy<>( actual.join( new JavaXCollectionAttributeProxy<>( collection ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <Y1> SetJoin<Y, Y1> join( SetAttribute<? super Y, Y1> set, JoinType jt )
    {
        return new JakartaSetJoinProxy<>( actual.join( new JavaXSetAttributeProxy<>( set ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <Y1> ListJoin<Y, Y1> join( ListAttribute<? super Y, Y1> list, JoinType jt )
    {
        return new JakartaListJoinProxy<>( actual.join( new JavaXListAttributeProxy<>( list ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <K, V> MapJoin<Y, K, V> join( MapAttribute<? super Y, K, V> map, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y1> Join<X1, Y1> join( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y1> CollectionJoin<X1, Y1> joinCollection( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y1> SetJoin<X1, Y1> joinSet( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y1> ListJoin<X1, Y1> joinList( String attributeName )
    {
        return null;
    }

    @Override public <X1, K, V> MapJoin<X1, K, V> joinMap( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y1> Join<X1, Y1> join( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y1> CollectionJoin<X1, Y1> joinCollection( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y1> SetJoin<X1, Y1> joinSet( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y1> ListJoin<X1, Y1> joinList( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X1, K, V> MapJoin<X1, K, V> joinMap( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public Set<Fetch<Y, ?>> getFetches()
    {
        return null;
    }

    @Override public <Y1> Fetch<Y, Y1> fetch( SingularAttribute<? super Y, Y1> attribute )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXSingularAttributeProxy<>( attribute ) ) );
    }

    @Override public <Y1> Fetch<Y, Y1> fetch( SingularAttribute<? super Y, Y1> attribute, JoinType jt )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXSingularAttributeProxy<>( attribute ), ProxyUtil.convertJoinType( jt ) ) );
    }

    @Override public <Y1> Fetch<Y, Y1> fetch( PluralAttribute<? super Y, ?, Y1> attribute )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXPluralAttributeProxy<>( attribute ) ) );
    }

    @Override public <Y1> Fetch<Y, Y1> fetch( PluralAttribute<? super Y, ?, Y1> attribute, JoinType jt )
    {
        return new JakartaFetchProxy<>( actual.fetch( new JavaXPluralAttributeProxy<>( attribute ), ProxyUtil.convertJoinType( jt )) );
    }

    @Override public <X1, Y1> Fetch<X1, Y1> fetch( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y1> Fetch<X1, Y1> fetch( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public Bindable<Y> getModel()
    {
        return null;
    }

    @Override public Path<?> getParentPath()
    {
        return null;
    }

    @Override public <Y1> Path<Y1> get( SingularAttribute<? super Y, Y1> attribute )
    {
        return new JakartaPathProxy<>( actual.get( new JavaXSingularAttributeProxy<>( attribute ) ) );
    }

    @Override public <E, C extends Collection<E>> Expression<C> get( PluralAttribute<Y, C, E> collection )
    {
        return null;
    }

    @Override public <K, V, M extends Map<K, V>> Expression<M> get( MapAttribute<Y, K, V> map )
    {
        return null;
    }

    @Override public Expression<Class<? extends Y>> type()
    {
        return null;
    }

    @Override public <Y1> Path<Y1> get( String attributeName )
    {
        return new JakartaPathProxy<>( actual.get( attributeName ) );
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
        return new JakartaExpressionProxy<>( actual.as( type ) );
    }

    @Override public Selection<Y> alias( String name )
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

    @Override public Class<? extends Y> getJavaType()
    {
        return null;
    }

    @Override public String getAlias()
    {
        return null;
    }
}
