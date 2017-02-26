package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class FieldHint implements MapperBuilderOption {
    protected final Class<?> hintClass;

    public FieldHint(Class<?> hintClass) {
        this.hintClass = hintClass;
    }

    public Class<?> getHintClass() {
        return hintClass;
    }
}
