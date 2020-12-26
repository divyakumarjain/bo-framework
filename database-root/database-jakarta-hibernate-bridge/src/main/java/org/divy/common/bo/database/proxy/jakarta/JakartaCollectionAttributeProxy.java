package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.ManagedType;
import jakarta.persistence.metamodel.Type;
import org.divy.common.bo.database.proxy.ProxyUtil;

import java.lang.reflect.Member;
import java.util.Collection;

public class JakartaCollectionAttributeProxy<X, E> implements CollectionAttribute<X, E> {
    private final javax.persistence.metamodel.CollectionAttribute<X, E> actual;

    public JakartaCollectionAttributeProxy( javax.persistence.metamodel.CollectionAttribute<X, E> actual )
    {
        this.actual = actual;
    }

    @Override public CollectionType getCollectionType()
    {
        return ProxyUtil.convertCollectionTypeEnum(actual.getCollectionType());
    }

    @Override public Type<E> getElementType()
    {
        return new JakartaType<>(actual.getElementType());
    }

    @Override public String getName()
    {
        return actual.getName();
    }

    @Override public PersistentAttributeType getPersistentAttributeType()
    {
        return ProxyUtil.convertPersistentAttributeTypeEnum(actual.getPersistentAttributeType());
    }

    @Override public ManagedType<X> getDeclaringType()
    {
        return new JakartaManagedTypeProxy<>( actual.getDeclaringType() );
    }

    @Override public Class<Collection<E>> getJavaType()
    {
        return actual.getJavaType();
    }

    @Override public Member getJavaMember()
    {
        return actual.getJavaMember();
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
        return ProxyUtil.convertBindableTypeEnum( actual.getBindableType() );
    }

    @Override public Class<E> getBindableJavaType()
    {
        return actual.getBindableJavaType();
    }
}
