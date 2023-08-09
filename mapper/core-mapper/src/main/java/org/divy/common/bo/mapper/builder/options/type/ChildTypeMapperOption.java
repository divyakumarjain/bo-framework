package org.divy.common.bo.mapper.builder.options.type;

import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperBuilderOption;

public final class ChildTypeMapperOption<S, T> implements FieldMapperBuilderOption {
    private final TypeMapperBuilderContext<S, T> childMapping;

    public ChildTypeMapperOption(TypeMapperBuilderContext<S, T> childMapping) {
        this.childMapping = childMapping;
    }

    public TypeMapperBuilderContext<S, T> getChildMapping() {
        return childMapping;
    }
}
