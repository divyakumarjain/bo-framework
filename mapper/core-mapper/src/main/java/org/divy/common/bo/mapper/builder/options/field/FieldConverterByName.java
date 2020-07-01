package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class FieldConverterByName implements MapperBuilderOption
{
    private final String converterName;

    public FieldConverterByName(String name) {
        this.converterName = name;
    }

    public String getConverterName() {
        return converterName;
    }
}
