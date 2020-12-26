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

public class JakartaSetJoinProxy<X, E> implements SetJoin<X, E> {
    private final Object actual;

    public JakartaSetJoinProxy( Object actual )
    {
        this.actual = actual;
    }

    @Override public SetJoin<X, E> on( Expression<Boolean> restriction )
    {
        return null;
    }

    @Override public SetJoin<X, E> on( Predicate... restrictions )
    {
        return null;
    }

    @Override public Predicate getOn()
    {
        return null;
    }

    @Override public Attribute<? super X, ?> getAttribute()
    {
        return null;
    }

    @Override public From<?, X> getParent()
    {
        return null;
    }

    @Override public JoinType getJoinType()
    {
        return null;
    }

    @Override public SetAttribute<? super X, E> getModel()
    {
        return null;
    }

    @Override public Path<?> getParentPath()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( SingularAttribute<? super E, Y> attribute )
    {
        return null;
    }

    @Override public <K, V, M extends Map<K, V>> Expression<M> get( MapAttribute<E, K, V> map )
    {
        return null;
    }

    @Override public Expression<Class<? extends E>> type()
    {
        return null;
    }

    @Override public <Y> Path<Y> get( String attributeName )
    {
        return null;
    }

    @Override public <E1, C extends Collection<E1>> Expression<C> get( PluralAttribute<E, C, E1> collection )
    {
        return null;
    }

    @Override public Set<Join<E, ?>> getJoins()
    {
        return null;
    }

    @Override public boolean isCorrelated()
    {
        return false;
    }

    @Override public From<X, E> getCorrelationParent()
    {
        return null;
    }

    @Override public <Y> Join<E, Y> join( SingularAttribute<? super E, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Join<E, Y> join( SingularAttribute<? super E, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<E, Y> join( CollectionAttribute<? super E, Y> collection )
    {
        return null;
    }

    @Override public <Y> SetJoin<E, Y> join( SetAttribute<? super E, Y> set )
    {
        return null;
    }

    @Override public <Y> ListJoin<E, Y> join( ListAttribute<? super E, Y> list )
    {
        return null;
    }

    @Override public <K, V> MapJoin<E, K, V> join( MapAttribute<? super E, K, V> map )
    {
        return null;
    }

    @Override public <Y> CollectionJoin<E, Y> join( CollectionAttribute<? super E, Y> collection, JoinType jt )
    {
        return null;
    }

    @Override public <Y> SetJoin<E, Y> join( SetAttribute<? super E, Y> set, JoinType jt )
    {
        return null;
    }

    @Override public <Y> ListJoin<E, Y> join( ListAttribute<? super E, Y> list, JoinType jt )
    {
        return null;
    }

    @Override public <K, V> MapJoin<E, K, V> join( MapAttribute<? super E, K, V> map, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y> Join<X1, Y> join( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y> CollectionJoin<X1, Y> joinCollection( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y> SetJoin<X1, Y> joinSet( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y> ListJoin<X1, Y> joinList( String attributeName )
    {
        return null;
    }

    @Override public <X1, K, V> MapJoin<X1, K, V> joinMap( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y> Join<X1, Y> join( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y> CollectionJoin<X1, Y> joinCollection( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y> SetJoin<X1, Y> joinSet( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y> ListJoin<X1, Y> joinList( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public <X1, K, V> MapJoin<X1, K, V> joinMap( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public Set<Fetch<E, ?>> getFetches()
    {
        return null;
    }

    @Override public <Y> Fetch<E, Y> fetch( SingularAttribute<? super E, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Fetch<E, Y> fetch( SingularAttribute<? super E, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <Y> Fetch<E, Y> fetch( PluralAttribute<? super E, ?, Y> attribute )
    {
        return null;
    }

    @Override public <Y> Fetch<E, Y> fetch( PluralAttribute<? super E, ?, Y> attribute, JoinType jt )
    {
        return null;
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String attributeName, JoinType jt )
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

    @Override public Selection<E> alias( String name )
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

    @Override public Class<? extends E> getJavaType()
    {
        return null;
    }

    @Override public String getAlias()
    {
        return null;
    }
}
