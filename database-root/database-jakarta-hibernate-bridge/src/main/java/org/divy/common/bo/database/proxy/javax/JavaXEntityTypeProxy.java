package org.divy.common.bo.database.proxy.javax;

import jakarta.persistence.metamodel.EntityType;

import javax.persistence.metamodel.*;
import java.util.Set;

public class JavaXEntityTypeProxy <T> implements javax.persistence.metamodel.EntityType<T> {
    private EntityType<T> entity;

    public JavaXEntityTypeProxy( EntityType<T> entity )
    {
        this.entity = entity;
    }

    @Override public String getName()
    {
        return null;
    }

    @Override public BindableType getBindableType()
    {
        return null;
    }

    @Override public Class<T> getBindableJavaType()
    {
        return null;
    }

    @Override public <Y> SingularAttribute<? super T, Y> getId( Class<Y> aClass )
    {
        return null;
    }

    @Override public <Y> SingularAttribute<T, Y> getDeclaredId( Class<Y> aClass )
    {
        return null;
    }

    @Override public <Y> SingularAttribute<? super T, Y> getVersion( Class<Y> aClass )
    {
        return null;
    }

    @Override public <Y> SingularAttribute<T, Y> getDeclaredVersion( Class<Y> aClass )
    {
        return null;
    }

    @Override public IdentifiableType<? super T> getSupertype()
    {
        return null;
    }

    @Override public boolean hasSingleIdAttribute()
    {
        return false;
    }

    @Override public boolean hasVersionAttribute()
    {
        return false;
    }

    @Override public Set<SingularAttribute<? super T, ?>> getIdClassAttributes()
    {
        return null;
    }

    @Override public Type<?> getIdType()
    {
        return null;
    }

    @Override public Set<Attribute<? super T, ?>> getAttributes()
    {
        return null;
    }

    @Override public Set<Attribute<T, ?>> getDeclaredAttributes()
    {
        return null;
    }

    @Override public <Y> SingularAttribute<? super T, Y> getSingularAttribute( String s, Class<Y> aClass )
    {
        return null;
    }

    @Override public <Y> SingularAttribute<T, Y> getDeclaredSingularAttribute( String s, Class<Y> aClass )
    {
        return null;
    }

    @Override public Set<SingularAttribute<? super T, ?>> getSingularAttributes()
    {
        return null;
    }

    @Override public Set<SingularAttribute<T, ?>> getDeclaredSingularAttributes()
    {
        return null;
    }

    @Override public <E> CollectionAttribute<? super T, E> getCollection( String s, Class<E> aClass )
    {
        return null;
    }

    @Override public <E> CollectionAttribute<T, E> getDeclaredCollection( String s, Class<E> aClass )
    {
        return null;
    }

    @Override public <E> SetAttribute<? super T, E> getSet( String s, Class<E> aClass )
    {
        return null;
    }

    @Override public <E> SetAttribute<T, E> getDeclaredSet( String s, Class<E> aClass )
    {
        return null;
    }

    @Override public <E> ListAttribute<? super T, E> getList( String s, Class<E> aClass )
    {
        return null;
    }

    @Override public <E> ListAttribute<T, E> getDeclaredList( String s, Class<E> aClass )
    {
        return null;
    }

    @Override public <K, V> MapAttribute<? super T, K, V> getMap( String s, Class<K> aClass, Class<V> aClass1 )
    {
        return null;
    }

    @Override public <K, V> MapAttribute<T, K, V> getDeclaredMap( String s, Class<K> aClass, Class<V> aClass1 )
    {
        return null;
    }

    @Override public Set<PluralAttribute<? super T, ?, ?>> getPluralAttributes()
    {
        return null;
    }

    @Override public Set<PluralAttribute<T, ?, ?>> getDeclaredPluralAttributes()
    {
        return null;
    }

    @Override public Attribute<? super T, ?> getAttribute( String s )
    {
        return null;
    }

    @Override public Attribute<T, ?> getDeclaredAttribute( String s )
    {
        return null;
    }

    @Override public SingularAttribute<? super T, ?> getSingularAttribute( String s )
    {
        return null;
    }

    @Override public SingularAttribute<T, ?> getDeclaredSingularAttribute( String s )
    {
        return null;
    }

    @Override public CollectionAttribute<? super T, ?> getCollection( String s )
    {
        return null;
    }

    @Override public CollectionAttribute<T, ?> getDeclaredCollection( String s )
    {
        return null;
    }

    @Override public SetAttribute<? super T, ?> getSet( String s )
    {
        return null;
    }

    @Override public SetAttribute<T, ?> getDeclaredSet( String s )
    {
        return null;
    }

    @Override public ListAttribute<? super T, ?> getList( String s )
    {
        return null;
    }

    @Override public ListAttribute<T, ?> getDeclaredList( String s )
    {
        return null;
    }

    @Override public MapAttribute<? super T, ?, ?> getMap( String s )
    {
        return null;
    }

    @Override public MapAttribute<T, ?, ?> getDeclaredMap( String s )
    {
        return null;
    }

    @Override public PersistenceType getPersistenceType()
    {
        return null;
    }

    @Override public Class<T> getJavaType()
    {
        return null;
    }
}
