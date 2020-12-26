package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.metamodel.*;

import java.util.Set;

public class JakartaManagedTypeProxy <X> implements ManagedType<X> {
    private final javax.persistence.metamodel.ManagedType<X> actual;

    public JakartaManagedTypeProxy( javax.persistence.metamodel.ManagedType<X> actual )
    {
        this.actual = actual;
    }

    @Override public Set<Attribute<? super X, ?>> getAttributes()
    {
        return null;
    }

    @Override public Set<Attribute<X, ?>> getDeclaredAttributes()
    {
        return null;
    }

    @Override public <Y> SingularAttribute<? super X, Y> getSingularAttribute( String name, Class<Y> type )
    {
        return null;
    }

    @Override public <Y> SingularAttribute<X, Y> getDeclaredSingularAttribute( String name, Class<Y> type )
    {
        return null;
    }

    @Override public Set<SingularAttribute<? super X, ?>> getSingularAttributes()
    {
        return null;
    }

    @Override public Set<SingularAttribute<X, ?>> getDeclaredSingularAttributes()
    {
        return null;
    }

    @Override public <E> CollectionAttribute<? super X, E> getCollection( String name, Class<E> elementType )
    {
        return null;
    }

    @Override public <E> CollectionAttribute<X, E> getDeclaredCollection( String name, Class<E> elementType )
    {
        return null;
    }

    @Override public <E> SetAttribute<? super X, E> getSet( String name, Class<E> elementType )
    {
        return null;
    }

    @Override public <E> SetAttribute<X, E> getDeclaredSet( String name, Class<E> elementType )
    {
        return null;
    }

    @Override public <E> ListAttribute<? super X, E> getList( String name, Class<E> elementType )
    {
        return null;
    }

    @Override public <E> ListAttribute<X, E> getDeclaredList( String name, Class<E> elementType )
    {
        return null;
    }

    @Override public <K, V> MapAttribute<? super X, K, V> getMap( String name, Class<K> keyType, Class<V> valueType )
    {
        return null;
    }

    @Override public <K, V> MapAttribute<X, K, V> getDeclaredMap( String name, Class<K> keyType, Class<V> valueType )
    {
        return null;
    }

    @Override public Set<PluralAttribute<? super X, ?, ?>> getPluralAttributes()
    {
        return null;
    }

    @Override public Set<PluralAttribute<X, ?, ?>> getDeclaredPluralAttributes()
    {
        return null;
    }

    @Override public Attribute<? super X, ?> getAttribute( String name )
    {
        return null;
    }

    @Override public Attribute<X, ?> getDeclaredAttribute( String name )
    {
        return null;
    }

    @Override public SingularAttribute<? super X, ?> getSingularAttribute( String name )
    {
        return null;
    }

    @Override public SingularAttribute<X, ?> getDeclaredSingularAttribute( String name )
    {
        return null;
    }

    @Override public CollectionAttribute<? super X, ?> getCollection( String name )
    {
        return null;
    }

    @Override public CollectionAttribute<X, ?> getDeclaredCollection( String name )
    {
        return null;
    }

    @Override public SetAttribute<? super X, ?> getSet( String name )
    {
        return null;
    }

    @Override public SetAttribute<X, ?> getDeclaredSet( String name )
    {
        return null;
    }

    @Override public ListAttribute<? super X, ?> getList( String name )
    {
        return null;
    }

    @Override public ListAttribute<X, ?> getDeclaredList( String name )
    {
        return null;
    }

    @Override public MapAttribute<? super X, ?, ?> getMap( String name )
    {
        return null;
    }

    @Override public MapAttribute<X, ?, ?> getDeclaredMap( String name )
    {
        return null;
    }

    @Override public PersistenceType getPersistenceType()
    {
        return null;
    }

    @Override public Class<X> getJavaType()
    {
        return null;
    }
}
