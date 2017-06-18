package org.divy.common.bo.metadata;

import org.divy.common.bo.IBusinessObject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MetaDataProvider {
    List<Class<? extends IBusinessObject>> getEntityTypes();
    Map<String, FieldMetaData> getChildEntities(Class<? extends IBusinessObject> businessObjectType);
    Map<String, FieldMetaData> getEmbeddedEntities(Class<? extends IBusinessObject> businessObjectType);
    Optional<Class<?>> getEndpointClass(MetaDataProvider metaDataProvider);
}
