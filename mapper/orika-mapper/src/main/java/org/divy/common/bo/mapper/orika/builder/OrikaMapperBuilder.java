package org.divy.common.bo.mapper.orika.builder;

import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;

import java.util.List;

public class OrikaMapperBuilder implements MapperBuilder {
    @Override
    public <S, T> TypeMapperBuilderContext<S, T> mapping(Class<S> businessObjectType, Class<T> mapClass, MapperBuilderOption... mapperBuilderOptions) {
        return new OrikaTypeMapperBuilderContext(businessObjectType, mapClass, mapperBuilderOptions);
    }

    @Override
    public <S, T> TypeMapperBuilderContext<S, T> mapping(Class<S> businessObjectType, Class<T> mapClass) {
        return new OrikaTypeMapperBuilderContext(businessObjectType, mapClass);
    }

    @Override
    public <S, T> TypeMapperBuilderContext<S, T> mapping(Class<S> businessObjectType, Class<T> mapClass, List<MapperBuilderOption> mapperBuilderOptions) {
        return new OrikaTypeMapperBuilderContext(businessObjectType, mapClass);
    }
}
