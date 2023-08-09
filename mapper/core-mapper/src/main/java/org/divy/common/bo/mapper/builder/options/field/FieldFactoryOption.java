package org.divy.common.bo.mapper.builder.options.field;

public final class FieldFactoryOption implements FieldMapperBuilderOption {
    final Class<?> factoryClass;
    public FieldFactoryOption(Class<?> factoryClass) {
        this.factoryClass = factoryClass;
    }
}
