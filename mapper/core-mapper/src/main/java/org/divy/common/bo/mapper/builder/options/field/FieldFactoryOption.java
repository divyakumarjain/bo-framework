package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class FieldFactoryOption implements MapperBuilderOption {
    final Class<?> factoryClass;
    public FieldFactoryOption(Class<?> factoryClass) {
        this.factoryClass = factoryClass;
    }
}
