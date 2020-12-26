package org.divy.common.bo.database.proxy.javax;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class JavaxSubqueryProxy<T> implements Subquery<T> {
    private final jakarta.persistence.criteria.Subquery<T> actual;

    public JavaxSubqueryProxy( jakarta.persistence.criteria.Subquery<T> actual )
    {
        this.actual = actual;
    }

    @Override public Subquery<T> select( Expression<T> expression )
    {
        return null;
    }

    @Override public <X> Root<X> from( Class<X> aClass )
    {
        return null;
    }

    @Override public <X> Root<X> from( EntityType<X> entityType )
    {
        return null;
    }

    @Override public Subquery<T> where( Expression<Boolean> expression )
    {
        return null;
    }

    @Override public Subquery<T> where( Predicate... predicates )
    {
        return null;
    }

    @Override public Subquery<T> groupBy( Expression<?>... expressions )
    {
        return null;
    }

    @Override public Subquery<T> groupBy( List<Expression<?>> list )
    {
        return null;
    }

    @Override public Subquery<T> having( Expression<Boolean> expression )
    {
        return null;
    }

    @Override public Subquery<T> having( Predicate... predicates )
    {
        return null;
    }

    @Override public Subquery<T> distinct( boolean b )
    {
        return null;
    }

    @Override public Set<Root<?>> getRoots()
    {
        return null;
    }

    @Override public <Y> Root<Y> correlate( Root<Y> root )
    {
        return null;
    }

    @Override public <X, Y> Join<X, Y> correlate( Join<X, Y> join )
    {
        return null;
    }

    @Override public <X, Y> CollectionJoin<X, Y> correlate( CollectionJoin<X, Y> collectionJoin )
    {
        return null;
    }

    @Override public <X, Y> SetJoin<X, Y> correlate( SetJoin<X, Y> setJoin )
    {
        return null;
    }

    @Override public <X, Y> ListJoin<X, Y> correlate( ListJoin<X, Y> listJoin )
    {
        return null;
    }

    @Override public <X, K, V> MapJoin<X, K, V> correlate( MapJoin<X, K, V> mapJoin )
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

    @Override public Expression<T> getSelection()
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

    @Override public Class<T> getResultType()
    {
        return null;
    }

    @Override public Set<Join<?, ?>> getCorrelatedJoins()
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

    @Override public <U> Subquery<U> subquery( Class<U> aClass )
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
}
