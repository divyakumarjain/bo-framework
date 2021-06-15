package org.divy.common.bo.mapper.orika.builder;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.UtilityResolver;
import ma.glasnost.orika.metadata.*;
import ma.glasnost.orika.property.PropertyResolverStrategy;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.mapper.FieldMapperContext;
import org.divy.common.bo.mapper.builder.AbstractTypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.OneWayMappingOption;
import org.divy.common.bo.mapper.builder.options.field.*;
import org.divy.common.bo.mapper.builder.options.type.ChildTypeMapperOption;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.function.Function;

public class OrikaTypeMapperBuilderContext<S, T> extends AbstractTypeMapperBuilderContext<S, T> {
    private final PropertyResolverStrategy defaultPropertyResolverStrategy = UtilityResolver.getDefaultPropertyResolverStrategy();

    private OrikaTypeMapperBuilderContext(OrikaMapperBuilder builder, Class<S> source, Class<T> target, List<MapperBuilderOption> mapperBuilderOptions) {
        super(builder, source, target);
        this.mapperBuilderOptions.addAll(mapperBuilderOptions);
    }

    OrikaTypeMapperBuilderContext(OrikaMapperBuilder builder, Class<S> source, Class<T> target) {
        this(builder, source, target, new ArrayList<>(0));
    }

    OrikaTypeMapperBuilderContext(OrikaMapperBuilder builder, Class<S> source, Class<T> target, MapperBuilderOption... mapperBuilderOptions) {
        this(builder, source, target, Arrays.asList(mapperBuilderOptions));
    }

    @Override
    public BOMapper<S, T> buildMapper() {
        return new AdvanceBOMapper<>(source, target, buildMapperFacade());
    }

    public MapperFacade buildMapperFacade() {
        final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter("uuidConverter",new BidirectionalConverter<String, UUID>() {
            @Override public UUID convertTo( String s, Type<UUID> type, MappingContext mappingContext )
            {
                return UUID.fromString( s );
            }

            @Override public String convertFrom( UUID uuid, Type<String> type, MappingContext mappingContext )
            {
                return uuid.toString();
            }
        });
        mapperFactory.getConverterFactory().registerConverter("timeConverter",new BidirectionalConverter<String, OffsetDateTime>() {
            @Override public OffsetDateTime convertTo( String s, Type<OffsetDateTime> type, MappingContext mappingContext )
            {
                return OffsetDateTime.parse( s );
            }

            @Override public String convertFrom( OffsetDateTime offsetDateTime, Type<String> type, MappingContext mappingContext )
            {
                return offsetDateTime.toString();
            }
        });

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
            processTypeOptions(boClassMapBuilder);
            configFieldMapping(boClassMapBuilder);
        }

        private void processTypeOptions(ClassMapBuilder<S, T> boClassMapBuilder) {
            mapperBuilderOptions
                    .forEach( option -> configureTypeMapping( option));
        }

        private void configFieldMapping(ClassMapBuilder<S, T> boClassMapBuilder) {
            fields.forEach((key, value) -> value.getFieldMapperBuilderOptions()
                    .forEach(fieldOption -> configFieldMapping(key, value, boClassMapBuilder)));
        }

        private void configFieldMapping(String fieldName, FieldMapperContext context, ClassMapBuilder<S, T> boClassMapBuilder) {

            final var fieldB = resolveFieldBProperty(context.getFieldMapperBuilderOptions(), fieldName);
            final var fieldA = resolveFieldAProperty(context.getFieldMapperBuilderOptions(), fieldName);
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

            resolveOption( fieldMapperBuilderOptions, FieldConverterByName.class ).ifPresent( option-> boFieldMapBuilder.converter( option.getConverterName() ) );
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

            final var builder = Property.Builder
                    .propertyFor(owner, fieldName);

            if(Map.class.isAssignableFrom(owner)) {
                builder.getter("get(\""+fieldName+"\")")
                        .setter("put(\""+fieldName+"\", %s)");
            } else {
                final var property = defaultPropertyResolverStrategy.getProperty(owner, fieldName);
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
                    .map(TargetFieldName::getFieldName);
        }

        private <M> Optional<M> resolveOption(List<MapperBuilderOption> fieldMapperBuilderOptions, Class<M> optionClass) {
            return fieldMapperBuilderOptions.stream()
                    .filter(optionClass::isInstance)
                    .map(optionClass::cast)
                    .findFirst();
        }

        private void configureTypeMapping( MapperBuilderOption option ) {
            if(option instanceof ChildTypeMapperOption) {
                TypeMapperBuilderContext<?, ?> childMapping = ((ChildTypeMapperOption<?, ?>) option).getChildMapping();
                if( childMapping instanceof OrikaTypeMapperBuilderContext) {
                    ((OrikaTypeMapperBuilderContext<?, ?>) childMapping).registerMapping(mapperFactory);
                }
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
