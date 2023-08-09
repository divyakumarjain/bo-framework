package org.divy.common.bo.mapper.builder.options.field;

public final class FieldConverterByName implements FieldMapperBuilderOption
{
    private final String converterName;

    public FieldConverterByName(String name) {
        this.converterName = name;
    }

    public String getConverterName() {
        return converterName;
    }
}
