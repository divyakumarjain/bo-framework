package org.divy.common.bo.database.proxy.javax;

import jakarta.persistence.metamodel.SingularAttribute;

import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Type;
import java.lang.reflect.Member;

public class JavaXSingularAttributeProxy<E,Y> implements javax.persistence.metamodel.SingularAttribute<E,Y>{
    public JavaXSingularAttributeProxy( SingularAttribute<? super E, Y> attribute )
    {
    }

    @Override public boolean isId()
    {
        return false;
    }

    @Override public boolean isVersion()
    {
        return false;
    }

    @Override public boolean isOptional()
    {
        return false;
    }

    @Override public Type<Y> getType()
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

    @Override public Class<Y> getJavaType()
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

    @Override public Class<Y> getBindableJavaType()
    {
        return null;
    }
}
