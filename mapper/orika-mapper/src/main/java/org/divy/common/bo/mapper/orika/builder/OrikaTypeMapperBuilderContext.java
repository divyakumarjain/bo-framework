package org.divy.common.bo.mapper.orika.builder;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.UtilityResolver;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.FieldMapBuilder;
import ma.glasnost.orika.metadata.Property;
import ma.glasnost.orika.metadata.TypeFactory;
import ma.glasnost.orika.property.PropertyResolverStrategy;
import org.divy.common.bo.mapper.FieldMapperContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.AbstractTypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.OneWayMappingOption;
import org.divy.common.bo.mapper.builder.options.field.*;
import org.divy.common.bo.mapper.builder.options.type.ChildTypeMapperOption;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;

import java.time.OffsetDateTime ;
import java.util.*;
import java.util.function.Function;

public class OrikaTypeMapperBuilderContext<S, T> extends AbstractTypeMapperBuilderContext<S, T> {
    private final PropertyResolverStrategy defaultPropertyResolverStrategy = UtilityResolver.getDefaultPropertyResolverStrategy();

    public OrikaTypeMapperBuilderContext(OrikaMapperBuilder builder, Class<S> source, Class<T> target, List<MapperBuilderOption> mapperBuilderOptions) {
        super(builder, source, target);
        this.mapperBuilderOptions.addAll(mapperBuilderOptions);
    }

    public OrikaTypeMapperBuilderContext(OrikaMapperBuilder builder, Class<S> source, Class<T> target) {
        this(builder, source, target, new ArrayList<>(0));
    }

    public OrikaTypeMapperBuilderContext(OrikaMapperBuilder builder, Class<S> source, Class<T> target, MapperBuilderOption... mapperBuilderOptions) {
        this(builder, source, target, Arrays.asList(mapperBuilderOptions));
    }

    @Override
    public IBOMapper<S, T> buildMapper() {
        return new AdvanceBOMapper<>(source, target, buildMapperFacade());
    }

    public MapperFacade buildMapperFacade() {
        final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder() .build();
        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(OffsetDateTime .class));
        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(UUID.class));

        new MapperFacadeBuilder(mapperFactory)
                .registerMapping();

        return mapperFactory.getMapperFacade();
    }

    public void registerMapping(DefaultMapperFactory mapperFactory) {
        new MapperFacadeBuilder(mapperFactory)
                .registerMapping();
    }

    private class MapperFacadeBuilder {

        private final DefaultMapperFactory mapperFactory;

        MapperFacadeBuilder(DefaultMapperFactory mapperFactory) {
            this.mapperFactory = mapperFactory;
        }

        private void configureTypeMapping(ClassMapBuilder<S, T> boClassMapBuilder) {
            mapperBuilderOptions
                    .forEach( option -> configureTypeMapping(boClassMapBuilder, option));

            fields.forEach((key, value) -> value.getFieldMapperBuilderOptions()
                    .forEach(fieldOption -> configFieldMapping(key, value, boClassMapBuilder)));
        }

        private void configFieldMapping(String fieldName, FieldMapperContext context, ClassMapBuilder<S, T> boClassMapBuilder) {

            final Property fieldB = resolveFieldBProperty(context.getFieldMapperBuilderOptions(), fieldName);
            final Property fieldA = resolveFieldAProperty(context.getFieldMapperBuilderOptions(), fieldName);
            final FieldMapBuilder<S, T> boFieldMapBuilder = boClassMapBuilder.fieldMap(fieldA, fieldB, false);
            configFieldMapping(context.getFieldMapperBuilderOptions(), boFieldMapBuilder);
            boFieldMapBuilder.mapNulls(false);
            boFieldMapBuilder.mapNullsInReverse(false);
            boFieldMapBuilder.add();

        }

        private void configFieldMapping(List<MapperBuilderOption> fieldMapperBuilderOptions, FieldMapBuilder<S, T> boFieldMapBuilder) {

            resolveOption(fieldMapperBuilderOptions, OneWayMappingOption.class)
                    .ifPresent(option -> boFieldMapBuilder.aToB());

            resolveOption(fieldMapperBuilderOptions, FieldExclude.class)
                    .ifPresent(option -> boFieldMapBuilder.exclude());
        }

        private Property resolveFieldAProperty(List<MapperBuilderOption> fieldMapperBuilderOptions, String fieldName) {
            return resolveFieldProperty(source, fieldName, fieldMapperBuilderOptions, this::resolveFieldAType
                    , this::resolveFieldANestedType);
        }

        private Property resolveFieldBProperty(List<MapperBuilderOption> fieldMapperBuilderOptions, String fieldNameA) {
            final String fieldNameB = getTargetFieldName(fieldMapperBuilderOptions).orElse(fieldNameA);
            return resolveFieldProperty(target, fieldNameB, fieldMapperBuilderOptions, this::resolveFieldBType
                    , this::resolveFieldBNestedType);
        }

        private Property resolveFieldProperty(Class<?> owner, String fieldName
                , List<MapperBuilderOption> fieldMapperBuilderOptions
                , Function<List<MapperBuilderOption> , Optional<Class<?>>> firstLevelTypeFunction
                , Function<List<MapperBuilderOption> , Optional<Class<?>>> secondLevelTypeFunction) {

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
                        Optional<Class<?>> secondLevel = secondLevelTypeFunction.apply(fieldMapperBuilderOptions);
                        if(secondLevel.isPresent()){
                            builder.type(TypeFactory.valueOf(firstLevelClazz,secondLevel.get()));
                            builder.elementType(TypeFactory.valueOf(secondLevel.get()));
                        } else {
                            builder.type(TypeFactory.valueOf(firstLevelClazz));
                        }
                    });

            secondLevelTypeFunction.apply(fieldMapperBuilderOptions)
                    .map(TypeFactory::valueOf)
                    .ifPresent(builder::elementType);

            return builder.build();
        }

        private Optional<Class<?>> resolveFieldBType(List<MapperBuilderOption> fieldMapperBuilderOptions) {
            return resolveOption(fieldMapperBuilderOptions, FieldHintB.class)
                    .map(FieldHintB::getHintClass);
        }

        private Optional<Class<?>> resolveFieldBNestedType(List<MapperBuilderOption> fieldMapperBuilderOptions) {
            return resolveOption(fieldMapperBuilderOptions, FieldNestedHintB.class)
                    .map(FieldNestedHintB::getHintClass);
        }

        private Optional<Class<?>> resolveFieldANestedType(List<MapperBuilderOption> fieldMapperBuilderOptions) {
            return resolveOption(fieldMapperBuilderOptions, FieldNestedHintA.class)
                    .map(FieldNestedHintA::getHintClass);
        }

        private Optional<Class<?>> resolveFieldAType(List<MapperBuilderOption> fieldMapperBuilderOptions) {
            return resolveOption(fieldMapperBuilderOptions, FieldHintA.class)
                    .map(FieldHintA::getHintClass);
        }

        private Optional<String> getTargetFieldName(List<MapperBuilderOption> fieldMapperBuilderOptions) {
            return resolveOption(fieldMapperBuilderOptions, TargetFieldName.class)
                    .map(TargetFieldName::getTargetFieldName);
        }

        private <M> Optional<M> resolveOption(List<MapperBuilderOption> fieldMapperBuilderOptions, Class<M> optionClass) {
            return fieldMapperBuilderOptions.stream()
                    .filter(optionClass::isInstance)
                    .map(optionClass::cast)
                    .findFirst();
        }

        private void configureTypeMapping(ClassMapBuilder<S, T> boClassMapBuilder, MapperBuilderOption option) {
            if(option instanceof ChildTypeMapperOption) {
                final OrikaTypeMapperBuilderContext childMapping = (OrikaTypeMapperBuilderContext) ((ChildTypeMapperOption) option)
                        .getChildMapping();
                childMapping.registerMapping(mapperFactory);
            }
        }

        void registerMapping() {

            final ClassMapBuilder<S, T> boClassMapBuilder = mapperFactory.classMap(source, target);

            configureTypeMapping(boClassMapBuilder);

            boClassMapBuilder
                    .byDefault()
                    .register();
        }
    }
}
