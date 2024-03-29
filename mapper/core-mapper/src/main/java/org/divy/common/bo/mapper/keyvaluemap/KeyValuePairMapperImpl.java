package org.divy.common.bo.mapper.keyvaluemap;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.builder.options.type.MapperBuilderOptions;
import org.divy.common.bo.metadata.FieldMetaData;
import org.divy.common.bo.metadata.MetaDataProvider;

import java.time.OffsetDateTime;
import java.util.*;

public class KeyValuePairMapperImpl<E extends BusinessObject> implements KeyValuePairMapper<E> {

    private BOMapper<E, Map<String, Object>> mapper;

    public KeyValuePairMapperImpl(Class<E> businessObjectType
            , MapperBuilder mapperBuilder
            , MetaDataProvider metaDataProvider) {

        buildTypeMapperBuilderContext(businessObjectType, mapperBuilder, metaDataProvider);
    }

    private void buildTypeMapperBuilderContext(Class<E> businessObjectType
            , MapperBuilder mapperBuilder
            , MetaDataProvider metaDataProvider) {

        final TypeMapperBuilderContext<E, Map<String, Object>> typeMapperBuilderContext
                = populateTypeMapperBuilderContext(businessObjectType
                , mapperBuilder
                , metaDataProvider);

        mapper = typeMapperBuilderContext
                .buildMapper();
    }

    private TypeMapperBuilderContext<E, Map<String, Object>> populateTypeMapperBuilderContext(Class<E> businessObjectType
            , MapperBuilder mapperBuilder
            , MetaDataProvider metaDataProvider) {

        final TypeMapperBuilderContext<E, Map<String, Object>> typeMapperBuilderContext
                = mapperBuilder.mapping(businessObjectType
                                , (Class<Map<String, Object>>) (Class) Map.class);

        typeMapperBuilderContext
                .field("lastUpdateTimestamp"
                        , FieldMapperOptions.hintB(String.class)
                        , FieldMapperOptions.hintA(OffsetDateTime.class)
                , FieldMapperOptions.converter( "timeConverter" ))
                .and()
                .field("createTimestamp"
                        , MapperBuilderOptions.oneWay()
                        , FieldMapperOptions.hintB(String.class)
                        , FieldMapperOptions.hintA(OffsetDateTime.class)
                , FieldMapperOptions.converter( "timeConverter" ))
                .and()
                .field("uuid"
                        , FieldMapperOptions.hintB(String.class)
                        , FieldMapperOptions.hintA(UUID.class)
                        , FieldMapperOptions.converter( "uuidConverter" ));

        final Map<String, FieldMetaData> childEntities = metaDataProvider.getChildEntities(businessObjectType);

        populateChildEntitiesMappingContext(mapperBuilder, metaDataProvider, typeMapperBuilderContext, childEntities);

        final Map<String, FieldMetaData> embeddedEntities = metaDataProvider.getEmbeddedEntities(businessObjectType);

        populateChildEntitiesMappingContext(mapperBuilder, metaDataProvider, typeMapperBuilderContext, embeddedEntities);

        return typeMapperBuilderContext;
    }

    private void populateChildEntitiesMappingContext(MapperBuilder mapperBuilder, MetaDataProvider metaDataProvider, TypeMapperBuilderContext<E, Map<String, Object>> typeMapperBuilderContext, Map<String, FieldMetaData> childEntities) {
        childEntities.forEach((key, value) -> typeMapperBuilderContext.field(key, createFieldMappingOptions(value, typeMapperBuilderContext, mapperBuilder, metaDataProvider)));
    }

    private List<MapperBuilderOption> createFieldMappingOptions(FieldMetaData metaData, TypeMapperBuilderContext<E, Map<String, Object>> typeMapperBuilderContext, MapperBuilder mapperBuilder, MetaDataProvider metaDataProvider) {
        List<MapperBuilderOption> mapperBuilderOptions = new ArrayList<>();
        final Class<?> type = metaData.getType();
        if(metaData.isCollection()) {
            mapperBuilderOptions.add(FieldMapperOptions.hintB(List.class));
            mapperBuilderOptions.add(FieldMapperOptions.nestedHintB(Map.class));
            mapperBuilderOptions.add(FieldMapperOptions.hintA(List.class));
            mapperBuilderOptions.add(FieldMapperOptions.nestedHintA(metaData.getType()));
        } else {
            mapperBuilderOptions.add(FieldMapperOptions.hintA(type));
            mapperBuilderOptions.add(FieldMapperOptions.hintB(Map.class));
        }

        if(BusinessObject.class.isAssignableFrom(type)) {
            final TypeMapperBuilderContext<E, Map<String, Object>> childTypeMapperBuilderContext = populateTypeMapperBuilderContext((Class<E>) type, mapperBuilder, metaDataProvider);
            typeMapperBuilderContext.addTypeMappingOption(MapperBuilderOptions.childTypeMapping(childTypeMapperBuilderContext));
        }
        return mapperBuilderOptions;
    }

    @Override
    public E createBO(Map<String, Object> sourceData) {
        return mapper.createBO(sourceData);
    }

    @Override
    public E mapToBO(Map<String, Object> sourceData, E businessObject) {
        return mapper.mapToBO(sourceData, businessObject);
    }

    @Override
    public Map<String, Object> createFromBO(E businessObject) {
        return mapper.createFromBO(businessObject);
    }

    @Override
    public Map<String, Object> mapFromBO(E businessObject, Map<String, Object> targetData) {
        return mapper.mapFromBO(businessObject, targetData);
    }

    @Override
    public Collection<E> createBO(Collection<Map<String, Object>> sourceData) {
        return mapper.createBO(sourceData);
    }

    @Override
    public Collection<Map<String, Object>> createFromBO(Collection<E> businessObject) {
        return mapper.createFromBO(businessObject);
    }
}
