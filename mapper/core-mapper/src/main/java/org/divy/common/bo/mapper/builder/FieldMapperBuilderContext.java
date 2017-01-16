package org.divy.common.bo.mapper.builder;

import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.field.TargetFieldName;

import java.util.List;

public interface FieldMapperBuilderContext <S, T> {
    TypeMapperBuilderContext<S, T> getTypeMapperBuilderContext();
    List<MapperBuilderOption> getMapperBuilderOptions();

    void setFieldMapperBuilderOptions(MapperBuilderOption[] mapperBuilderOptions);

    default TypeMapperBuilderContext<S, T> and() {
        return this.getTypeMapperBuilderContext();
    }

    default FieldMapperBuilderContext<S, T> option(MapperBuilderOption mapperBuilderOption) {
        getMapperBuilderOptions().add(mapperBuilderOption);
        return this;
    }

    default FieldMapperBuilderContext<S, T> targetFieldName(String targetFieldName) {
        option(new TargetFieldName(targetFieldName));
        return this;
    }

    default IBOMapper<S, T> build() {
        return this.getTypeMapperBuilderContext().build();
    }
}
