package org.divy.common.bo.mapper.modelmapper.builder;

import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ModelMapperBuilder implements MapperBuilder {

    private final ModelMapper modelMapper;

    public ModelMapperBuilder() {
        this.modelMapper = new ModelMapper();
        configureDefaults();
    }

    public ModelMapperBuilder(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureDefaults();
    }

    private void configureDefaults() {
        // Configure ModelMapper for better field matching similar to Orika
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMethodAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PUBLIC);
    }

    @Override
    public <S, T> TypeMapperBuilderContext<S, T> mapping(Class<S> sourceType, Class<T> destinationType) {
        return new ModelMapperTypeMapperBuilderContext<>(this, sourceType, destinationType);
    }

    @Override
    public <S, T> TypeMapperBuilderContext<S, T> mapping(Class<S> sourceType, Class<T> destinationType, MapperBuilderOption... options) {
        return new ModelMapperTypeMapperBuilderContext<>(this, sourceType, destinationType, options);
    }

    @Override
    public <S, T> TypeMapperBuilderContext<S, T> mapping(Class<S> sourceType, Class<T> destinationType, List<MapperBuilderOption> options) {
        return new ModelMapperTypeMapperBuilderContext<>(this, sourceType, destinationType, options);
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
