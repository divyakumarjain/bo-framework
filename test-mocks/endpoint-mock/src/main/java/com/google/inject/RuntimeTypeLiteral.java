package com.google.inject;

import com.google.inject.internal.MoreTypes;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;

/**
 * Created by divyjain on 11/30/2014.
 */
public class RuntimeTypeLiteral extends TypeLiteral{
    public static <ENTITY extends IBusinessObject<ID>,ID> TypeLiteral<IBOManager<ENTITY, ID>> boManager(TypeLiteral<ENTITY> entityType, TypeLiteral<ID> entityKeyType) {
        return new TypeLiteral<IBOManager<ENTITY, ID>>(new MoreTypes.ParameterizedTypeImpl(null,IBOManager.class,entityType.getType(),entityKeyType.getType()));
    }
}
