package org.divy.common.bo.mapper.keyvaluemap;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.builder.options.type.MapperBuilderOptions;
import org.divy.common.bo.metadata.FieldMetaData;
import org.divy.common.bo.metadata.MetaDataProvider;

import java.time.LocalDateTime;
import java.util.*;

public class KeyValuePairMapperImpl<E extends IBusinessObject> implements KeyValuePairMapper<E> {

    private IBOMapper<E, Map<String, Object>> mapper;

    public KeyValuePairMapperImpl(Class<E> businessObjectType
            , MapperBuilder mapperBuilder
            , MetaDataProvider metaDataProvider) {

        buildTypeMapperBuilderContext(businessObjectType, mapperBuilder, metaDataProvider);
    }

    private void buildTypeMapperBuilderContext(Class<E> businessObjectType
            , MapperBuilder mapperBuilder
            , MetaDataProvider metaDataProvider) {

        final TypeMapperBuilderContext<E, Map<String, Object>> typeMapperBuilderContext = populateTypeMapperBuilderContext(businessObjectType, mapperBuilder, metaDataProvider);

        mapper = typeMapperBuilderContext
                .buildMapper();
    }

    private TypeMapperBuilderContext<E, Map<String, Object>> populateTypeMapperBuilderContext(Class<E> businessObjectType
            , MapperBuilder mapperBuilder
            , MetaDataProvider metaDataProvider) {

        final TypeMapperBuilderContext<E, Map<String, Object>> typeMapperBuilderContext
                = mapperBuilder.mapping(businessObjectType, (Class<Map<String, Object>>) (Class) Map.class);

        typeMapperBuilderContext
                .field("lastUpdateTimestamp"
                        , MapperBuilderOptions.oneWay()
                        , FieldMapperOptions.hintB(LocalDateTime.class)
                        , FieldMapperOptions.hintA(LocalDateTime.class))
                .and()
                .field("createTimestamp"
                        , MapperBuilderOptions.oneWay()
                        , FieldMapperOptions.hintB(LocalDateTime.class)
                        , FieldMapperOptions.hintA(LocalDateTime.class))
                .and()
                .field("uuid"
                        , FieldMapperOptions.hintB(UUID.class)
                        , FieldMapperOptions.hintA(UUID.class));

        final Map<String, FieldMetaData> childEntities = metaDataProvider.getChildEntity(businessObjectType);

        childEntities.forEach((key, value) -> typeMapperBuilderContext.field(key, createFieldMappingOptions(value, typeMapperBuilderContext, mapperBuilder, metaDataProvider)));
        return typeMapperBuilderContext;
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

        if(IBusinessObject.class.isAssignableFrom(type)) {
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
