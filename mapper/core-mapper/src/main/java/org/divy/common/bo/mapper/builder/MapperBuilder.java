package org.divy.common.bo.mapper.builder;

import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

import java.util.List;

public interface MapperBuilder {
    <S, T> TypeMapperBuilderContext<S, T> mapping(Class<S> businessObjectType
            , Class<T> mapClass
            , MapperBuilderOption... mapperBuilderOptions);

    <S, T> TypeMapperBuilderContext<S, T> mapping(Class<S> businessObjectType
            , Class<T> mapClass);

    <S, T> TypeMapperBuilderContext<S, T> mapping(Class<S> businessObjectType
            , Class<T> mapClass
            , List<MapperBuilderOption> mapperBuilderOptions);
}
