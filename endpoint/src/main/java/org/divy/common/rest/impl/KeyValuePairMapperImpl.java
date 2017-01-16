package org.divy.common.rest.impl;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.FieldMetaData;
import org.divy.common.bo.database.MetaDataProvider;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.type.MapperBuilderOptions;
import org.divy.common.bo.mapper.defaults.UuidBeanFactory;

import java.util.Collection;
import java.util.Map;

public class KeyValuePairMapperImpl<E extends IBusinessObject> implements KeyValuePairMapper<E> {

    private IBOMapper<E, Map<String, Object>> mapper;

    public KeyValuePairMapperImpl(Class<E> businessObjectType
            , MapperBuilder mapperBuilder
            , MetaDataProvider metaDataProvider) {

        final TypeMapperBuilderContext<E, Map<String, Object>> typeMapperBuilderContext
                = mapperBuilder.mapping(businessObjectType, (Class<Map<String, Object>>) (Class) Map.class
                , MapperBuilderOptions.oneWay())
                    .field("lastUpdateTimestamp", MapperBuilderOptions.oneWay())
                        .and()
                    .field("createTimestamp", MapperBuilderOptions.oneWay())
                        .and()
                    .field("uuid", MapperBuilderOptions.oneWay())
                    .and();

        final Map<String, FieldMetaData> childEntities = metaDataProvider.getChildEntity(businessObjectType);

        childEntities.entrySet().forEach(childEntity-> typeMapperBuilderContext.field(childEntity.getKey()));

        mapper = typeMapperBuilderContext
                .build();
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
