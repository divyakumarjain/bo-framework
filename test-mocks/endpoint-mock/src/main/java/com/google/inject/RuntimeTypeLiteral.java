package com.google.inject;

import com.google.inject.internal.MoreTypes;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;

public class RuntimeTypeLiteral<E extends IBusinessObject<I>, I> extends TypeLiteral<IBOManager<E, I>> {
    public static <E extends IBusinessObject<I>, I> TypeLiteral<IBOManager<E, I>> boManager(TypeLiteral<E> entityType, TypeLiteral<I> entityKeyType) {
        return new TypeLiteral<>(new MoreTypes.ParameterizedTypeImpl(null, IBOManager.class, entityType.getType(), entityKeyType.getType()));
    }
}
