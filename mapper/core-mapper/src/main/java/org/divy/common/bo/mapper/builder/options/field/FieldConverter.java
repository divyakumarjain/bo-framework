package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class FieldConverter implements MapperBuilderOption {
    private final Class<?> converterClass;

    public FieldConverter(Class<?> converterClass) {
        this.converterClass = converterClass;
    }

    public Class<?> getConverterClass() {
        return converterClass;
    }
}
