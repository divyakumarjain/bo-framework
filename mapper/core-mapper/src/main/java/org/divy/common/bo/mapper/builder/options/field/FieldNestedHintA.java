package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class FieldNestedHintA implements MapperBuilderOption {
    private final Class<?> hintClass;

    public FieldNestedHintA(Class<?> nestedHintBClass) {
        this.hintClass = nestedHintBClass;
    }

    public Class<?> getHintClass() {
        return hintClass;
    }
}
