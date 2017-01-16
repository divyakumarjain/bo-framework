package org.divy.common.bo.mapper.builder;

import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

public interface TypeMapperBuilderContext<S, T> {
    FieldMapperBuilderContext<S, T> field(String fieldName, MapperBuilderOption... mapperBuilderOptions);
    FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName, MapperBuilderOption... mapperBuilderOptions);
    FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName);
    FieldMapperBuilderContext<S, T> field(String fieldName);

    TypeMapperBuilderContext<S, T> autoFieldMapping();

    IBOMapper<S, T> build();
}
