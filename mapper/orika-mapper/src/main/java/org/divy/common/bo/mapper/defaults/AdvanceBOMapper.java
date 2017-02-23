package org.divy.common.bo.mapper.defaults;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.OrikaSystemProperties;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.UtilityResolver;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;
import ma.glasnost.orika.metadata.*;
import ma.glasnost.orika.property.PropertyResolverStrategy;
import org.divy.common.bo.mapper.AbstractBOMapper;
import org.divy.common.bo.mapper.FieldMapperContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.OneWayMappingOption;
import org.divy.common.bo.mapper.builder.options.field.*;

public class AdvanceBOMapper<B, O> extends AbstractBOMapper<B, O> {

    private Map<String, FieldMapperContext> fields;
    private List<MapperBuilderOption> mapperBuilderOptions;
    private final PropertyResolverStrategy defaultPropertyResolverStrategy = UtilityResolver.getDefaultPropertyResolverStrategy();

    public AdvanceBOMapper(Class<B> businessObjectType
            , Class<O> otherObjectType
            , List<MapperBuilderOption> mapperBuilderOptions
            , Map<String, FieldMapperContext> fields) {

        super(businessObjectType, otherObjectType);
        this.mapperBuilderOptions = mapperBuilderOptions;
        this.fields = fields;
    }

    private void configureMapping(ClassMapBuilder<B, O> boClassMapBuilder) {
        mapperBuilderOptions
                .forEach( option -> configureMapping(boClassMapBuilder, option));

        fields.forEach((key, value) -> value.getFieldMapperBuilderOptions()
                .forEach(fieldOption -> configFieldMapping(key, value, boClassMapBuilder)));
    }

    private void configFieldMapping(String fieldName, FieldMapperContext context, ClassMapBuilder<B, O> boClassMapBuilder) {

        final Property fieldB = resolveFieldBProperty(context.getFieldMapperBuilderOptions(), fieldName);
        final Property fieldA = resolveFieldAProperty(context.getFieldMapperBuilderOptions(), fieldName);
        final FieldMapBuilder<B, O> boFieldMapBuilder = boClassMapBuilder.fieldMap(fieldA, fieldB, false);
        configFieldMapping(context.getFieldMapperBuilderOptions(), boFieldMapBuilder);
        boFieldMapBuilder.mapNulls(false);
        boFieldMapBuilder.mapNullsInReverse(false);
        boFieldMapBuilder.add();

    }

    private void configFieldMapping(List<MapperBuilderOption> fieldMapperBuilderOptions, FieldMapBuilder<B, O> boFieldMapBuilder) {

        resolveOption(fieldMapperBuilderOptions, OneWayMappingOption.class)
                .ifPresent((option) -> boFieldMapBuilder.aToB());

        resolveOption(fieldMapperBuilderOptions, FieldExclude.class)
                .ifPresent((option) -> boFieldMapBuilder.exclude());
    }

    private Property resolveFieldAProperty(List<MapperBuilderOption> fieldMapperBuilderOptions, String fieldName) {
        return resolveFieldProperty(businessObjectType, fieldName, fieldMapperBuilderOptions, this::resolveFieldAType
                , this::resolveFieldANestedType);
    }

    private Property resolveFieldBProperty(List<MapperBuilderOption> fieldMapperBuilderOptions, String fieldNameA) {
        final String fieldNameB = getTargetFieldName(fieldMapperBuilderOptions).orElse(fieldNameA);
        return resolveFieldProperty(otherObjectType, fieldNameB, fieldMapperBuilderOptions, this::resolveFieldBType
                , this::resolveFieldBNestedType);
    }

    private Property resolveFieldProperty(Class<?> owner, String fieldName
            , List<MapperBuilderOption> fieldMapperBuilderOptions
            , Function<List<MapperBuilderOption> , Optional<Class>> firstLevelTypeFunction
            , Function<List<MapperBuilderOption> , Optional<Class>> secondLevelTypeFunction) {

        final Property.Builder builder = Property.Builder
                .propertyFor(owner, fieldName);

        if(Map.class.isAssignableFrom(owner)) {
            builder.getter("get(\""+fieldName+"\")")
                    .setter("put(\""+fieldName+"\", %s)");
        } else {
            final Property property = defaultPropertyResolverStrategy.getProperty(owner, fieldName);
            builder.merge(property);
        }

        firstLevelTypeFunction.apply(fieldMapperBuilderOptions)
                .ifPresent(firstLevelClazz-> {
                    Optional<Class> secondLevel = secondLevelTypeFunction.apply(fieldMapperBuilderOptions);
                    if(secondLevel.isPresent()){
                        builder.type(TypeFactory.valueOf(firstLevelClazz,secondLevel.get()));
                        builder.elementType(TypeFactory.valueOf(secondLevel.get()));
                    } else {
                        builder.type(TypeFactory.valueOf(firstLevelClazz));
                    }
                });

        secondLevelTypeFunction.apply(fieldMapperBuilderOptions)
                .map(TypeFactory::valueOf)
                .ifPresent(type-> builder.elementType((Type<?>) type));

        return builder.build();
    }

    private Optional<Class> resolveFieldBType(List<MapperBuilderOption> fieldMapperBuilderOptions) {
        return resolveOption(fieldMapperBuilderOptions, FieldHintB.class)
                .map(FieldHintB::getHintClass);
    }

    private Optional<Class> resolveFieldBNestedType(List<MapperBuilderOption> fieldMapperBuilderOptions) {
        return resolveOption(fieldMapperBuilderOptions, FieldNestedHintB.class)
                .map(FieldNestedHintB::getHintClass);
    }

    private Optional<Class> resolveFieldANestedType(List<MapperBuilderOption> fieldMapperBuilderOptions) {
        return resolveOption(fieldMapperBuilderOptions, FieldNestedHintA.class)
                .map(FieldNestedHintA::getHintClass);
    }

    private Optional<Class> resolveFieldAType(List<MapperBuilderOption> fieldMapperBuilderOptions) {
        return resolveOption(fieldMapperBuilderOptions, FieldHintA.class)
                .map(FieldHintA::getHintClass);
    }

    private Optional<String> getTargetFieldName(List<MapperBuilderOption> fieldMapperBuilderOptions) {
        return resolveOption(fieldMapperBuilderOptions, TargetFieldName.class)
                .map(TargetFieldName::getTargetFieldName);
    }

    private <M extends MapperBuilderOption> Optional<M> resolveOption(List<MapperBuilderOption> fieldMapperBuilderOptions, Class<M> optionClass) {
        return (Optional<M>) fieldMapperBuilderOptions.stream()
                .filter(optionClass::isInstance)
                .findFirst();
    }

    private void configureMapping(ClassMapBuilder<B, O> boClassMapBuilder, MapperBuilderOption option) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    protected MapperFacade createMapper() {

//        // Write out source files to (classpath:)/ma/glasnost/orika/generated/
//        System.setProperty(OrikaSystemProperties.WRITE_SOURCE_FILES,"true");
//
//        // Write out class files to (classpath:)/ma/glasnost/orika/generated/
//        System.setProperty(OrikaSystemProperties.WRITE_CLASS_FILES,"true");

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
//                .compilerStrategy(new EclipseJdtCompilerStrategy())
                .build();

        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(UUID.class));

        final ClassMapBuilder<B, O> boClassMapBuilder = mapperFactory.classMap(businessObjectType, otherObjectType);

        configureMapping(boClassMapBuilder);

        boClassMapBuilder
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }

}
