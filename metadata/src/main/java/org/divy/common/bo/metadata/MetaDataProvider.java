package org.divy.common.bo.metadata;

import org.divy.common.bo.BusinessObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MetaDataProvider {
    List<Class<? extends BusinessObject>> getEntityTypes();
    Map<String, FieldMetaData> getChildEntities(Class<? extends BusinessObject> businessObjectType);
    Map<String, FieldMetaData> getEmbeddedEntities(Class<? extends IBusinessObject> businessObjectType);
    Optional<Class<?>> getEndpointClass(MetaDataProvider metaDataProvider);
}
