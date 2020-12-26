package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Selection;
import jakarta.persistence.metamodel.Bindable;
import jakarta.persistence.metamodel.MapAttribute;
import jakarta.persistence.metamodel.PluralAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import org.divy.common.bo.database.proxy.javax.JavaXMapAttributeProxy;
import org.divy.common.bo.database.proxy.javax.JavaXPluralAttributeProxy;
import org.divy.common.bo.database.proxy.javax.JavaXSingularAttributeProxy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JakartaPathProxy<T> implements Path<T> {
    private final javax.persistence.criteria.Path<T> actual;

    public JakartaPathProxy( javax.persistence.criteria.Path<T> actual )
    {
        this.actual = actual;
    }

    @Override public Bindable<T> getModel()
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
