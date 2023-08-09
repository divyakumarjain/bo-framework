package org.divy.common.bo.mapper.builder.options.field;

public sealed class FieldHint implements FieldMapperBuilderOption permits FieldHintA, FieldHintB, FieldNestedHintA, FieldNestedHintB {
    protected final Class<?> hintClass;

    public FieldHint(Class<?> hintClass) {
        this.hintClass = hintClass;
    }

    public Class<?> getHintClass() {
        return hintClass;
    }
}
