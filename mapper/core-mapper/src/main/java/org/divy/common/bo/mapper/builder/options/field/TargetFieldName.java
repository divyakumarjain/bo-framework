package org.divy.common.bo.mapper.builder.options.field;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public class TargetFieldName implements MapperBuilderOption{
    private final String targetFieldName;

    public TargetFieldName(String targetFieldName) {
        this.targetFieldName = targetFieldName;
    }

    public String getTargetFieldName() {
        return targetFieldName;
    }
}
