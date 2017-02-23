package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class FieldNestedHintB implements MapperBuilderOption {
    private final Class<?> hintClass;

    public FieldNestedHintB(Class<?> nestedHintBClass) {
        this.hintClass = nestedHintBClass;
    }

    public Class<?> getHintClass() {
        return hintClass;
    }
}
