package org.divy.common.bo.database.proxy.javax;

import jakarta.persistence.criteria.Path;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JavaXPathProxy<X> implements javax.persistence.criteria.Path<X> {
    private final Path<X> actual;

    public JavaXPathProxy( Path<X> actual )
    {
        this.actual = actual;
    }

    @Override public Bindable<X> getModel()
    {
        return null;
    }

    @Override public javax.persistence.criteria.Path<?> getParentPath()
    {
        return null;
    }

    @Override public <Y> javax.persistence.criteria.Path<Y> get( SingularAttribute<? super X, Y> singularAttribute )
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

    @Override public <Y> javax.persistence.criteria.Path<Y> get( String s )
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
