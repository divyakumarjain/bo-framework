package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.ManagedType;
import org.divy.common.bo.database.proxy.ProxyUtil;

import java.lang.reflect.Member;

public class JakartaAttributeProxy<X, Y> implements Attribute<X, Y> {
    private final javax.persistence.metamodel.Attribute<X, Y> actual;

    public JakartaAttributeProxy( javax.persistence.metamodel.Attribute<X, Y> actual )
    {
        this.actual = actual;
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

    @Override public Class<Y> getJavaType()
    {
        return actual.getJavaType();
    }

    @Override public Member getJavaMember()
    {
        return actual.getJavaMember();
    }

    @Override public boolean isAssociation()
    {
        return actual.isAssociation();
    }

    @Override public boolean isCollection()
    {
        return actual.isCollection();
    }
}
