package org.divy.common.bo.mapper.builder.options.field;

public final class TargetFieldName implements FieldMapperBuilderOption {
    private final String fieldName;

    public TargetFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
