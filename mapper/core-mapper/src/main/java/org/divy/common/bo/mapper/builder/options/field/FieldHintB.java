package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class FieldHintB implements MapperBuilderOption {
    private final Class<?> hintClass;

    public FieldHintB(Class<?> hintClass) {
        this.hintClass = hintClass;
    }

    public Class<?> getHintClass() {
        return hintClass;
    }
}
