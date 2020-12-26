package org.divy.common.bo.database.proxy.javax;

import jakarta.persistence.metamodel.MapAttribute;

import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Type;
import java.lang.reflect.Member;
import java.util.Map;

public class JavaXMapAttributeProxy<E,K,V> implements javax.persistence.metamodel.MapAttribute<E, K, V> {
    public JavaXMapAttributeProxy( MapAttribute<E, K, V> map )
    {
    }

    @Override public Class<K> getKeyJavaType()
    {
        return null;
    }

    @Override public Type<K> getKeyType()
    {
        return null;
    }

    @Override public CollectionType getCollectionType()
    {
        return null;
    }

    @Override public Type<V> getElementType()
    {
        return null;
    }

    @Override public String getName()
    {
        return null;
    }

    @Override public PersistentAttributeType getPersistentAttributeType()
    {
        return null;
    }

    @Override public ManagedType<E> getDeclaringType()
    {
        return null;
    }

    @Override public Class<Map<K, V>> getJavaType()
    {
        return null;
    }

    @Override public Member getJavaMember()
    {
        return null;
    }

    @Override public boolean isAssociation()
    {
        return false;
    }

    @Override public boolean isCollection()
    {
        return false;
    }

    @Override public BindableType getBindableType()
    {
        return null;
    }

    @Override public Class<V> getBindableJavaType()
    {
        return null;
    }
}
