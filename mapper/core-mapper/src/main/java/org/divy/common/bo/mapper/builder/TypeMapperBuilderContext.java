package org.divy.common.bo.mapper.builder;

import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

import java.util.List;

public interface TypeMapperBuilderContext<S, T> {
    FieldMapperBuilderContext<S, T> field(String fieldName, MapperBuilderOption... mapperBuilderOptions);
    FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName, MapperBuilderOption... mapperBuilderOptions);
    FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName, List<MapperBuilderOption> mapperBuilderOptions);
    FieldMapperBuilderContext<S, T> field(String fieldName, List<MapperBuilderOption> mapperBuilderOptions);
    FieldMapperBuilderContext<S, T> field(String fieldName, String targetFieldName);
    FieldMapperBuilderContext<S, T> field(String fieldName);

    TypeMapperBuilderContext<S, T> autoFieldMapping();

    TypeMapperBuilderContext<S, T> addTypeMappingOption(MapperBuilderOption... mapperBuilderOptions);

    <CS,CT> TypeMapperBuilderContext<CS, CT> childTypeMapperBuilder(Class<CS> source
            , Class<CT> target
            , MapperBuilderOption... mapperBuilderOptions);

    IBOMapper<S, T> buildMapper();
}
