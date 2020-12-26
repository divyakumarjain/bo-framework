package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.*;
import org.divy.common.bo.database.proxy.javax.JavaXExpressionProxy;
import org.divy.common.bo.database.proxy.javax.JavaXMapAttributeProxy;
import org.divy.common.bo.database.proxy.javax.JavaXPluralAttributeProxy;
import org.divy.common.bo.database.proxy.javax.JavaXSingularAttributeProxy;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JakartaJoinProxy<X, V> implements Join<X, V> {
    private final javax.persistence.criteria.Join<X, V> actual;

    public JakartaJoinProxy( javax.persistence.criteria.Join<X, V> actual )
    {
        this.actual = actual;
    }

    @Override public Join<X, V> on( Expression<Boolean> restriction )
    {
        return new JakartaJoinProxy<>( actual.on( new JavaXExpressionProxy<>( restriction ) ) );
    }

    @Override public Join<X, V> on( Predicate... restrictions )
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

    @Override public Set<Join<V, ?>> getJoins()
    {
        return null;
    }

    @Override public boolean isCorrelated()
    {
        return false;
    }

    @Override public From<X, V> getCorrelationParent()
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

    @Override public <X1, K, V1> MapJoin<X1, K, V1> joinMap( String attributeName )
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

    @Override public <X1, K, V1> MapJoin<X1, K, V1> joinMap( String attributeName, JoinType jt )
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

    @Override public <X1, Y> Fetch<X1, Y> fetch( String attributeName )
    {
        return null;
    }

    @Override public <X1, Y> Fetch<X1, Y> fetch( String attributeName, JoinType jt )
    {
        return null;
    }

    @Override public Bindable<V> getModel()
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

    @Override public <K, V1, M extends Map<K, V1>> Expression<M> get( MapAttribute<V, K, V1> map )
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
