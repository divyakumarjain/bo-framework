package org.divy.common.bo.database.proxy.javax;

import jakarta.persistence.criteria.ListJoin;
import org.divy.common.bo.database.proxy.ProxyUtil;
import org.divy.common.bo.database.proxy.jakarta.JakartaCollectionAttributeProxy;
import org.divy.common.bo.database.proxy.jakarta.JakartaExpressionProxy;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaXListJoinProxy<X, T> implements javax.persistence.criteria.ListJoin<X, T> {
    private final ListJoin<X, T> actual;

    public JavaXListJoinProxy( ListJoin<X, T> actual )
    {
        this.actual = actual;
    }

    @Override public javax.persistence.criteria.ListJoin<X, T> on( Expression<Boolean> expression )
    {
        return new JavaXListJoinProxy<>( actual.on( new JakartaExpressionProxy<>( expression ) ) );
    }

    @Override public javax.persistence.criteria.ListJoin<X, T> on( Predicate... predicates )
    {
        return new JavaXListJoinProxy<>( actual.on( ProxyUtil.convertArrayProxy ( predicates,org.divy.common.bo.database.proxy.jakarta.JakartaPredicateProxy::new) ) );
    }

    @Override public ListAttribute<? super X, T> getModel()
    {
        return null;
    }

    @Override public Path<?> getParentPath()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( SingularAttribute<? super T, Y> singularAttribute )
    {
        return null;
    }

    @Override public <E, C extends Collection<E>> Expression<C> get( PluralAttribute<T, C, E> pluralAttribute )
    {
        return null;
    }

    @Override public <K, V, M extends Map<K, V>> Expression<M> get( MapAttribute<T, K, V> mapAttribute )
    {
        return null;
    }

    @Override public Expression<Class<? extends T>> type()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( String s )
    {
        return null;
    }

    @Override public Expression<Integer> index()
    {
        return null;
    }

    @Override public Predicate getOn()
    {
        return new org.divy.common.bo.database.proxy.javax.JavaXPredicateProxy( actual.getOn() );
    }

    @Override public Attribute<? super X, ?> getAttribute()
    {
        return new org.divy.common.bo.database.proxy.javax.JavaXAttributeProxy<>( actual.getAttribute() );
    }

    @Override public From<?, X> getParent()
    {
        return new org.divy.common.bo.database.proxy.javax.JavaXFromProxy<>( actual.getParent() );
    }

    @Override public JoinType getJoinType()
    {
        return new ProxyUtil.convertJoinType( actual.getJoinType() );
    }

    @Override public Set<Join<T, ?>> getJoins()
    {
        return null;
    }

    @Override public boolean isCorrelated()
    {
        return false;
    }

    @Override public From<X, T> getCorrelationParent()
    {
        return null;
    }

    @Override public <Y> Join<T, Y> join( SingularAttribute<? super T, Y> singularAttribute )
    {
        return null;
    }

    @Override public <Y> Join<T, Y> join( SingularAttribute<? super T, Y> singularAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<T, Y> join( CollectionAttribute<? super T, Y> collectionAttribute )
    {
        return null;
    }

    @Override public <Y> SetJoin<T, Y> join( SetAttribute<? super T, Y> setAttribute )
    {
        return null;
    }

    @Override public <Y> javax.persistence.criteria.ListJoin<T, Y> join( ListAttribute<? super T, Y> listAttribute )
    {
        return null;
    }

    @Override public <K, V> MapJoin<T, K, V> join( MapAttribute<? super T, K, V> mapAttribute )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<T, Y> join( CollectionAttribute<? super T, Y> collectionAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <Y> SetJoin<T, Y> join( SetAttribute<? super T, Y> setAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <Y> javax.persistence.criteria.ListJoin<T, Y> join( ListAttribute<? super T, Y> listAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <K, V> MapJoin<T, K, V> join( MapAttribute<? super T, K, V> mapAttribute, JoinType joinType )
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

    @Override public <X1, Y> javax.persistence.criteria.ListJoin<X1, Y> joinList( String s )
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

    @Override public <X1, Y> javax.persistence.criteria.ListJoin<X1, Y> joinList( String s, JoinType joinType )
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

    @Override public <X> Expression<X> as( Class<X> aClass )
    {
        return null;
    }

    @Override public Set<Fetch<T, ?>> getFetches()
    {
        return null;
    }

    @Override public <Y> Fetch<T, Y> fetch( SingularAttribute<? super T, Y> singularAttribute )
    {
        return null;
    }

    @Override public <Y> Fetch<T, Y> fetch( SingularAttribute<? super T, Y> singularAttribute, JoinType joinType )
    {
        return null;
    }

    @Override public <Y> Fetch<T, Y> fetch( PluralAttribute<? super T, ?, Y> pluralAttribute )
    {
        return null;
    }

    @Override public <Y> Fetch<T, Y> fetch( PluralAttribute<? super T, ?, Y> pluralAttribute, JoinType joinType )
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

    @Override public Selection<T> alias( String s )
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
