package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.*;
import org.divy.common.bo.database.proxy.javax.JavaXMapAttributeProxy;
import org.divy.common.bo.database.proxy.javax.JavaXPluralAttributeProxy;
import org.divy.common.bo.database.proxy.javax.JavaXSingularAttributeProxy;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JakartaRootProxy<T> implements Root<T> {
    private final javax.persistence.criteria.Root<T> actual;

    public JakartaRootProxy( javax.persistence.criteria.Root<T> actual )
    {
        this.actual = actual;
    }

    @Override public EntityType<T> getModel()
    {
        return null;
    }

    @Override public Path<?> getParentPath()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( SingularAttribute<? super T, Y> attribute )
    {
        return null;
    }

    @Override public <E, C extends Collection<E>> Expression<C> get( PluralAttribute<T, C, E> collection )
    {
        return null;
    }

    @Override public <K, V, M extends Map<K, V>> Expression<M> get( MapAttribute<T, K, V> map )
    {
        return null;
    }

    @Override public Expression<Class<? extends T>> type()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( String attributeName )
    {
        return null;
    }

    @Override public Set<Join<T, ?>> getJoins()
    {
        return null;
    }

    @Override public boolean isCorrelated()
    {
        return false;
    }

    @Override public From<T, T> getCorrelationParent()
    {
        return null;
    }

    @Override public <Y> Join<T, Y> join( SingularAttribute<? super T, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Join<T, Y> join( SingularAttribute<? super T, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<T, Y> join( CollectionAttribute<? super T, Y> collection )
    {
        return null;
    }

    @Override public <Y> SetJoin<T, Y> join( SetAttribute<? super T, Y> set )
    {
        return null;
    }

    @Override public <Y> ListJoin<T, Y> join( ListAttribute<? super T, Y> list )
    {
        return null;
    }

    @Override public <K, V> MapJoin<T, K, V> join( MapAttribute<? super T, K, V> map )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<T, Y> join( CollectionAttribute<? super T, Y> collection, JoinType jt )
    {
        return null;
    }

    @Override public <Y> SetJoin<T, Y> join( SetAttribute<? super T, Y> set, JoinType jt )
    {
        return null;
    }

    @Override public <Y> ListJoin<T, Y> join( ListAttribute<? super T, Y> list, JoinType jt )
    {
        return null;
    }

    @Override public <K, V> MapJoin<T, K, V> join( MapAttribute<? super T, K, V> map, JoinType jt )
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

    @Override public <X, K, V> MapJoin<X, K, V> joinMap( String attributeName )
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

    @Override public <X, K, V> MapJoin<X, K, V> joinMap( String attributeName, JoinType jt )
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

    @Override public Set<Fetch<T, ?>> getFetches()
    {
        return null;
    }

    @Override public <Y> Fetch<T, Y> fetch( SingularAttribute<? super T, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Fetch<T, Y> fetch( SingularAttribute<? super T, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <Y> Fetch<T, Y> fetch( PluralAttribute<? super T, ?, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Fetch<T, Y> fetch( PluralAttribute<? super T, ?, Y> attribute, JoinType jt )
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

    @Override public Selection<T> alias( String name )
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

    @Override public Class<? extends T> getJavaType()
    {
        return null;
    }

    @Override public String getAlias()
    {
        return null;
    }
}
