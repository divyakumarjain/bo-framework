package org.divy.common.bo.database.proxy.jakarta;

import jakarta.persistence.metamodel.Type;
import org.divy.common.bo.database.proxy.ProxyUtil;

public class JakartaType <E> implements Type<E> {
    private javax.persistence.metamodel.Type<E> actual;

    public JakartaType( javax.persistence.metamodel.Type<E> actual )
    {
        this.actual = actual;
    }

    @Override public PersistenceType getPersistenceType()
    {
        return ProxyUtil.convertPersistenceTypeEnum(actual.getPersistenceType());
    }

    @Override public Class<E> getJavaType()
    {
        return actual.getJavaType();
    }
}
