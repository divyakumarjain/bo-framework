package org.divy.common.bo.database.proxy.javax;

import jakarta.persistence.metamodel.PluralAttribute;

import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Type;
import java.lang.reflect.Member;

public class JavaXPluralAttributeProxy<E, C, E1> implements  javax.persistence.metamodel.PluralAttribute<E, C, E1>{
    public JavaXPluralAttributeProxy( PluralAttribute<E, C, E1> collection )
    {
    }

    @Override public CollectionType getCollectionType()
    {
        return null;
    }

    @Override public Type getElementType()
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

    @Override public ManagedType getDeclaringType()
    {
        return null;
    }

    @Override public Class getJavaType()
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

    @Override public Class getBindableJavaType()
    {
        return null;
    }
}
