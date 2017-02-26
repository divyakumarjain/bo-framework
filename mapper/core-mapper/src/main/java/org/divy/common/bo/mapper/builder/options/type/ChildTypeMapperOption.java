package org.divy.common.bo.mapper.builder.options.type;

import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class ChildTypeMapperOption<S, T> implements MapperBuilderOption {
    private final TypeMapperBuilderContext<S, T> childMapping;

    public ChildTypeMapperOption(TypeMapperBuilderContext<S, T> childMapping) {
        this.childMapping = childMapping;
    }

    public TypeMapperBuilderContext<S, T> getChildMapping() {
        return childMapping;
    }
}
