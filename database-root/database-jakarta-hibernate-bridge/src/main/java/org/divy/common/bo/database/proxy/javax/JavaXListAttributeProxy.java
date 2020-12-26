package org.divy.common.bo.database.proxy.javax;

import jakarta.persistence.metamodel.ListAttribute;

import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Type;
import java.lang.reflect.Member;
import java.util.List;

public class JavaXListAttributeProxy <X, E> implements javax.persistence.metamodel.ListAttribute<X, E> {
    private ListAttribute<X, E> actual;

    public JavaXListAttributeProxy( ListAttribute<X, E> actual )
    {
        this.actual = actual;
    }

    @Override public CollectionType getCollectionType()
    {
        return null;
    }

    @Override public Type<E> getElementType()
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

    @Override public ManagedType<X> getDeclaringType()
    {
        return null;
    }

    @Override public Class<List<E>> getJavaType()
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

    @Override public Class<E> getBindableJavaType()
    {
        return null;
    }
}
