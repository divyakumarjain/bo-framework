package org.divy.common.bo.mapper.builder.options.type;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class TypeFactoryOption implements MapperBuilderOption {
    final Class<?> factoryClass;
    public TypeFactoryOption(Class<?> factoryClass) {
        this.factoryClass = factoryClass;
    }
}
