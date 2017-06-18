package com.google.inject;

import com.google.inject.internal.MoreTypes;
import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.BOManager;

public class RuntimeTypeLiteral<E extends BusinessObject<I>, I> extends TypeLiteral<BOManager<E, I>> {
    public static <E extends BusinessObject<I>, I> TypeLiteral<BOManager<E, I>> boManager(TypeLiteral<E> entityType, TypeLiteral<I> entityKeyType) {
        return new TypeLiteral<>(new MoreTypes.ParameterizedTypeImpl(null, BOManager.class, entityType.getType(), entityKeyType.getType()));
    }
}
