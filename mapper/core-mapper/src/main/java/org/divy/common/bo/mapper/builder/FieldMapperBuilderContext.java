package org.divy.common.bo.mapper.builder;

import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.field.TargetFieldName;

public interface FieldMapperBuilderContext <S, T> extends org.divy.common.bo.mapper.FieldMapperContext {
    TypeMapperBuilderContext<S, T> getTypeMapperBuilderContext();

    default TypeMapperBuilderContext<S, T> and() {
        return this.getTypeMapperBuilderContext();
    }

    default FieldMapperBuilderContext<S, T> option(MapperBuilderOption mapperBuilderOption) {
        getFieldMapperBuilderOptions().add(mapperBuilderOption);
        return this;
    }

    default FieldMapperBuilderContext<S, T> targetFieldName(String targetFieldName) {
        option(new TargetFieldName(targetFieldName));
        return this;
    }

    default BOMapper<S, T> build() {
        return this.getTypeMapperBuilderContext().buildMapper();
    }
}
