package com.google.inject;

import com.google.inject.internal.MoreTypes;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;

public class RuntimeTypeLiteral<ENTITY extends IBusinessObject<ID>, ID> extends TypeLiteral<IBOManager<ENTITY, ID>>{
    public static <ENTITY extends IBusinessObject<ID>,ID> TypeLiteral<IBOManager<ENTITY, ID>> boManager(TypeLiteral<ENTITY> entityType, TypeLiteral<ID> entityKeyType) {
        return new TypeLiteral<IBOManager<ENTITY, ID>>(new MoreTypes.ParameterizedTypeImpl(null,IBOManager.class,entityType.getType(),entityKeyType.getType()));
    }
}
