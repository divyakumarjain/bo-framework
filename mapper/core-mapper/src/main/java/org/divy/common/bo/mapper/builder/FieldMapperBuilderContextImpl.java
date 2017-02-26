package org.divy.common.bo.mapper.builder;

import org.divy.common.bo.mapper.FieldMapperContextImpl;

public class FieldMapperBuilderContextImpl<S, T> extends FieldMapperContextImpl implements FieldMapperBuilderContext <S, T> {
    private final TypeMapperBuilderContext typeMapperBuilderContext;

    public FieldMapperBuilderContextImpl(TypeMapperBuilderContext typeMapperBuilderContext) {
        super();
        this.typeMapperBuilderContext = typeMapperBuilderContext;
    }

    @Override
    public TypeMapperBuilderContext getTypeMapperBuilderContext() {
        return this.typeMapperBuilderContext;
    }

}
