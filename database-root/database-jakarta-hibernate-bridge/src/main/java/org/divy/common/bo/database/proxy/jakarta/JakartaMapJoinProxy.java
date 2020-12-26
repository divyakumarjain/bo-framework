package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JakartaMapJoinProxy <ELEMENTYPE, K, V> implements MapJoin<ELEMENTYPE, K, V> {
    private final javax.persistence.criteria.MapJoin<ELEMENTYPE, K, V> actual;

    public  JakartaMapJoinProxy( javax.persistence.criteria.MapJoin<ELEMENTYPE, K, V> actual )
    {
        this.actual = actual;
    }

    @Override public MapJoin<ELEMENTYPE, K, V> on( Expression<Boolean> restriction )
    {
        return null;
    }

    @Override public MapJoin<ELEMENTYPE, K, V> on( Predicate... restrictions )
    {
        return null;
    }

    @Override public Predicate getOn()
    {
        return null;
    }

    @Override public Attribute<? super ELEMENTYPE, ?> getAttribute()
    {
        return null;
    }

    @Override public From<?, ELEMENTYPE> getParent()
    {
        return null;
    }

    @Override public JoinType getJoinType()
    {
        return null;
    }

    @Override public MapAttribute<? super ELEMENTYPE, K, V> getModel()
    {
        return null;
    }

    @Override public Path<?> getParentPath()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( SingularAttribute<? super V, Y> attribute )
    {
        return null;
    }

    @Override public <E, C extends Collection<E>> Expression<C> get( PluralAttribute<V, C, E> collection )
    {
        return null;
    }

    @Override public Expression<Class<? extends V>> type()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( String attributeName )
    {
        return null;
    }

    @Override public <K1, V1, M extends Map<K1, V1>> Expression<M> get( MapAttribute<V, K1, V1> map )
    {
        return null;
    }

    @Override public Path<K> key()
    {
        return null;
    }

    @Override public Path<V> value()
    {
        return null;
    }

    @Override public Expression<Map.Entry<K, V>> entry()
    {
        return null;
    }

    @Override public Set<Join<V, ?>> getJoins()
    {
        return null;
    }

    @Override public boolean isCorrelated()
    {
        return false;
    }

    @Override public From<ELEMENTYPE, V> getCorrelationParent()
    {
        return null;
    }

    @Override public <Y> Join<V, Y> join( SingularAttribute<? super V, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Join<V, Y> join( SingularAttribute<? super V, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<V, Y> join( CollectionAttribute<? super V, Y> collection )
    {
        return null;
    }

    @Override public <Y> SetJoin<V, Y> join( SetAttribute<? super V, Y> set )
    {
        return null;
    }

    @Override public <Y> ListJoin<V, Y> join( ListAttribute<? super V, Y> list )
    {
        return null;
    }

    @Override public <K, V1> MapJoin<V, K, V1> join( MapAttribute<? super V, K, V1> map )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<V, Y> join( CollectionAttribute<? super V, Y> collection, JoinType jt )
    {
        return null;
    }

    @Override public <Y> SetJoin<V, Y> join( SetAttribute<? super V, Y> set, JoinType jt )
    {
        return null;
    }

    @Override public <Y> ListJoin<V, Y> join( ListAttribute<? super V, Y> list, JoinType jt )
    {
        return null;
    }

    @Override public <K, V1> MapJoin<V, K, V1> join( MapAttribute<? super V, K, V1> map, JoinType jt )
    {
        return null;
    }

    @Override public <X, Y> Join<X, Y> join( String attributeName )
    {
        return null;
    }

    @Override public <X, Y> CollectionJoin<X, Y> joinCollection( String attributeName )
    {
        return null;
    }

    @Override public <X, Y> SetJoin<X, Y> joinSet( String attributeName )
    {
        return null;
    }

    @Override public <X, Y> ListJoin<X, Y> joinList( String attributeName )
    {
        return null;
    }

    @Override public <X, K, V1> MapJoin<X, K, V1> joinMap( String attributeName )
    {
        return null;
    }

    @Override public <X, Y> Join<X, Y> join( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X, Y> CollectionJoin<X, Y> joinCollection( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X, Y> SetJoin<X, Y> joinSet( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X, Y> ListJoin<X, Y> joinList( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X, K, V1> MapJoin<X, K, V1> joinMap( String attributeName, JoinType jt )
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

    @Override public Set<Fetch<V, ?>> getFetches()
    {
        return null;
    }

    @Override public <Y> Fetch<V, Y> fetch( SingularAttribute<? super V, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Fetch<V, Y> fetch( SingularAttribute<? super V, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <Y> Fetch<V, Y> fetch( PluralAttribute<? super V, ?, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Fetch<V, Y> fetch( PluralAttribute<? super V, ?, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <X, Y> Fetch<X, Y> fetch( String attributeName )
    {
        return null;
    }

    @Override public <X, Y> Fetch<X, Y> fetch( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public Selection<V> alias( String name )
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

    @Override public Class<? extends V> getJavaType()
    {
        return null;
    }

    @Override public String getAlias()
    {
        return null;
    }
}
