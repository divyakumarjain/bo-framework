package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class JakartaSubqueryProxy <U> implements Subquery<U> {
    private javax.persistence.criteria.Subquery<U> actual;

    public JakartaSubqueryProxy( javax.persistence.criteria.Subquery<U> actual )
    {
        this.actual = actual;
    }

    @Override public Subquery<U> select( Expression<U> expression )
    {
        return null;
    }

    @Override public <X> Root<X> from( Class<X> entityClass )
    {
        return null;
    }

    @Override public <X> Root<X> from( EntityType<X> entity )
    {
        return null;
    }

    @Override public Subquery<U> where( Expression<Boolean> restriction )
    {
        return null;
    }

    @Override public Subquery<U> where( Predicate... restrictions )
    {
        return null;
    }

    @Override public Subquery<U> groupBy( Expression<?>... grouping )
    {
        return null;
    }

    @Override public Subquery<U> groupBy( List<Expression<?>> grouping )
    {
        return null;
    }

    @Override public Subquery<U> having( Expression<Boolean> restriction )
    {
        return null;
    }

    @Override public Subquery<U> having( Predicate... restrictions )
    {
        return null;
    }

    @Override public Subquery<U> distinct( boolean distinct )
    {
        return null;
    }

    @Override public Set<Root<?>> getRoots()
    {
        return null;
    }

    @Override public <Y> Root<Y> correlate( Root<Y> parentRoot )
    {
        return null;
    }

    @Override public <X, Y> Join<X, Y> correlate( Join<X, Y> parentJoin )
    {
        return null;
    }

    @Override public <X, Y> CollectionJoin<X, Y> correlate( CollectionJoin<X, Y> parentCollection )
    {
        return null;
    }

    @Override public <X, Y> SetJoin<X, Y> correlate( SetJoin<X, Y> parentSet )
    {
        return null;
    }

    @Override public <X, Y> ListJoin<X, Y> correlate( ListJoin<X, Y> parentList )
    {
        return null;
    }

    @Override public <X, K, V> MapJoin<X, K, V> correlate( MapJoin<X, K, V> parentMap )
    {
        return null;
    }

    @Override public AbstractQuery<?> getParent()
    {
        return null;
    }

    @Override public CommonAbstractCriteria getContainingQuery()
    {
        return null;
    }

    @Override public Expression<U> getSelection()
    {
        return null;
    }

    @Override public List<Expression<?>> getGroupList()
    {
        return null;
    }

    @Override public Predicate getGroupRestriction()
    {
        return null;
    }

    @Override public boolean isDistinct()
    {
        return false;
    }

    @Override public Class<U> getResultType()
    {
        return null;
    }

    @Override public Set<Join<?, ?>> getCorrelatedJoins()
    {
        return null;
    }

    @Override public Class<? extends U> getJavaType()
    {
        return null;
    }

    @Override public String getAlias()
    {
        return null;
    }

    @Override public <U> Subquery<U> subquery( Class<U> type )
    {
        return null;
    }

    @Override public Predicate getRestriction()
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

    @Override public Selection<U> alias( String name )
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
}
