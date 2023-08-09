package org.divy.common.bo.mapper.builder.options.field;

public final class FieldConverter implements FieldMapperBuilderOption {
    private final Class<?> converterClass;

    public FieldConverter(Class<?> converterClass) {
        this.converterClass = converterClass;
    }

    public Class<?> getConverterClass() {
        return converterClass;
    }
}
