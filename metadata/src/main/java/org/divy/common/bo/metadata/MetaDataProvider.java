package org.divy.common.bo.metadata;

import org.divy.common.bo.BusinessObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MetaDataProvider {
    List<Class<? extends BusinessObject>> getEntityTypes();
    Map<String, FieldMetaData> getChildEntity(Class<? extends BusinessObject> businessObjectType);

    Optional<Class<?>> getEndpointClass(MetaDataProvider metaDataProvider);
}
