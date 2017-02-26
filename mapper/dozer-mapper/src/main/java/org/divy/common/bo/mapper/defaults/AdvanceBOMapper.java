package org.divy.common.bo.mapper.defaults;

import org.divy.common.bo.mapper.AbstractBOMapper;
import org.divy.common.bo.mapper.DozerFieldMappingOptionMapping;
import org.divy.common.bo.mapper.DozerTypeMappingOptionMapping;
import org.divy.common.bo.mapper.FieldMapperContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.*;

import java.util.*;
import java.util.stream.Stream;

public class AdvanceBOMapper<B, O> extends AbstractBOMapper<B, O> {

    private final Map<String, FieldMapperContext> fields;
    private final List<MapperBuilderOption> mapperBuilderOptions;

    public AdvanceBOMapper(Class<B> businessObjectType
            , Class<O> otherObjectType
            , List<MapperBuilderOption> mapperBuilderOptions
            , Map<String, FieldMapperContext> fields) {

        super(businessObjectType, otherObjectType);
        this.mapperBuilderOptions = mapperBuilderOptions;
        this.fields = fields;
    }

    public AdvanceBOMapper(Class<B> businessObjectType,
                           Class<O> otherObjectType) {
        this(businessObjectType, otherObjectType, new ArrayList<>(0), new HashMap<>(0));
    }


    protected void configureFieldMapping(TypeMappingBuilder mapper) {
        for( Map.Entry<String, FieldMapperContext> entry : fields.entrySet())
            configFieldMapping(entry.getKey(), entry.getValue(), mapper);
    }

    private void configFieldMapping(String fieldName, FieldMapperContext context, TypeMappingBuilder mapper) {
        mapper.fields(fieldName, fieldName, covertFieldMappingOptions(context, mapper));
    }

    private FieldsMappingOption[] covertFieldMappingOptions(FieldMapperContext context, TypeMappingBuilder mapper) {
        if(context!=null && context.getFieldMapperBuilderOptions()!=null) {
            final Stream<MapperBuilderOption> stream = context.getFieldMapperBuilderOptions().stream();
            return stream
                    .map(DozerFieldMappingOptionMapping::optionFor)
                    .toArray(FieldsMappingOption[]::new);
        } else {
            return new FieldsMappingOption[0];
        }

    }

    private TypeMappingOption[] configureMapping() {
        if(mapperBuilderOptions!=null) {
            return mapperBuilderOptions.stream()
                    .map(DozerTypeMappingOptionMapping::optionFor)
                    .toArray(TypeMappingOption[]::new);
        } else {
            return new TypeMappingOption[0];
        }

    }

    @Override
    protected Mapper createMapper() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                TypeMappingBuilder mapper = mapping(businessObjectType, otherObjectType, configureMapping());
                configureFieldMapping(mapper);
                mapping(UUID.class, UUID.class, TypeMappingOptions.beanFactory(UuidBeanFactory.class.getName()));
            }
        };

        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(builder);
        return mapper;
    }

}
