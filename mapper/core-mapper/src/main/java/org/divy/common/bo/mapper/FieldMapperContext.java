package org.divy.common.bo.mapper;

import org.divy.common.bo.mapper.builder.FieldMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.field.TargetFieldName;

import java.util.List;

public interface FieldMapperContext {
    List<MapperBuilderOption> getFieldMapperBuilderOptions();

    void setFieldMapperBuilderOptions(MapperBuilderOption[] mapperBuilderOptions);

    default void addOption(MapperBuilderOption mapperBuilderOption) {
        getFieldMapperBuilderOptions().add(mapperBuilderOption);
    }

    default void setTargetFieldName(String targetFieldName) {
        addOption(new TargetFieldName(targetFieldName));
    }
}
