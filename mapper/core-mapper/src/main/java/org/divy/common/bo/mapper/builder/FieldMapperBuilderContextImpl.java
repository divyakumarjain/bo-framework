package org.divy.common.bo.mapper.builder;

import org.divy.common.bo.mapper.FieldMapperContextImpl;

public class FieldMapperBuilderContextImpl<S, T> extends FieldMapperContextImpl implements FieldMapperBuilderContext <S, T> {
    private final TypeMapperBuilderContext<S,T> typeMapperBuilderContext;

    public FieldMapperBuilderContextImpl(TypeMapperBuilderContext<S,T> typeMapperBuilderContext) {
        super();
        this.typeMapperBuilderContext = typeMapperBuilderContext;
    }

    @Override
    public TypeMapperBuilderContext<S,T> getTypeMapperBuilderContext() {
        return this.typeMapperBuilderContext;
    }

}
