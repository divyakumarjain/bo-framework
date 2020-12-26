package org.divy.common.bo.database.proxy.javax;

import jakarta.persistence.criteria.Root;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaXRootProxy<X> implements javax.persistence.criteria.Root<X> {
    private final Root<X> actual;

    public JavaXRootProxy( Root<X> actual )
    {
        this.actual = actual;
    }

    @Override public EntityType<X> getModel()
    {
        return null;
    }

    @Override public Path<?> getParentPath()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( SingularAttribute<? super X, Y> singularAttribute )
    {
        return null;
    }

    @Override public <E, C extends Collection<E>> Expression<C> get( PluralAttribute<X, C, E> pluralAttribute )
    {
        return null;
    }

    @Override public <K, V, M extends Map<K, V>> Expression<M> get( MapAttribute<X, K, V> mapAttribute )
    {
        return null;
    }

    @Override public Expression<Class<? extends X>> type()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( String s )
    {
        return null;
    }

    @Override public Set<Join<X, ?>> getJoins()
    {
        return null;
    }

    @Override public boolean isCorrelated()
    {
        return false;
    }

    @Override public From<X, X> getCorrelationParent()
    {
        return null;
    }

    @Override public <Y> Join<X, Y> join( SingularAttribute<? super X, Y> singularAttribute )
    {
        return null;
    }

    @Override public <Y> Join<X, Y> join( SingularAttribute<? super X, Y> singularAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<X, Y> join( CollectionAttribute<? super X, Y> collectionAttribute )
    {
        return null;
    }

    @Override public <Y> SetJoin<X, Y> join( SetAttribute<? super X, Y> setAttribute )
    {
        return null;
    }

    @Override public <Y> ListJoin<X, Y> join( ListAttribute<? super X, Y> listAttribute )
    {
        return null;
    }

    @Override public <K, V> MapJoin<X, K, V> join( MapAttribute<? super X, K, V> mapAttribute )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<X, Y> join( CollectionAttribute<? super X, Y> collectionAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <Y> SetJoin<X, Y> join( SetAttribute<? super X, Y> setAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <Y> ListJoin<X, Y> join( ListAttribute<? super X, Y> listAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <K, V> MapJoin<X, K, V> join( MapAttribute<? super X, K, V> mapAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <X1, Y> Join<X1, Y> join( String s )
    {
        return null;
    }

    @Override public <X1, Y> CollectionJoin<X1, Y> joinCollection( String s )
    {
        return null;
    }

    @Override public <X1, Y> SetJoin<X1, Y> joinSet( String s )
    {
        return null;
    }

    @Override public <X1, Y> ListJoin<X1, Y> joinList( String s )
    {
        return null;
    }

    @Override public <X1, K, V> MapJoin<X1, K, V> joinMap( String s )
    {
        return null;
    }

    @Override public <X1, Y> Join<X1, Y> join( String s, JoinType joinType )
    {
        return null;
    }

    @Override public <X1, Y> CollectionJoin<X1, Y> joinCollection( String s, JoinType joinType )
    {
        return null;
    }

    @Override public <X1, Y> SetJoin<X1, Y> joinSet( String s, JoinType joinType )
    {
        return null;
    }

    @Override public <X1, Y> ListJoin<X1, Y> joinList( String s, JoinType joinType )
    {
        return null;
    }

    @Override public <X1, K, V> MapJoin<X1, K, V> joinMap( String s, JoinType joinType )
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

    @Override public <X1> Expression<X1> as( Class<X1> aClass )
    {
        return null;
    }

    @Override public Set<Fetch<X, ?>> getFetches()
    {
        return null;
    }

    @Override public <Y> Fetch<X, Y> fetch( SingularAttribute<? super X, Y> singularAttribute )
    {
        return null;
    }

    @Override public <Y> Fetch<X, Y> fetch( SingularAttribute<? super X, Y> singularAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <Y> Fetch<X, Y> fetch( PluralAttribute<? super X, ?, Y> pluralAttribute )
    {
        return null;
    }

    @Override public <Y> Fetch<X, Y> fetch( PluralAttribute<? super X, ?, Y> pluralAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String s )
    {
        return null;
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String s, JoinType joinType )
    {
        return null;
    }

    @Override public Selection<X> alias( String s )
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

    @Override public Class<? extends X> getJavaType()
    {
        return null;
    }

    @Override public String getAlias()
    {
        return null;
    }
}
