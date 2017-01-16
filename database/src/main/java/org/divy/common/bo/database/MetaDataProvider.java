package org.divy.common.bo.database;

import org.divy.common.bo.IBusinessObject;

import java.util.List;
import java.util.Map;

public interface MetaDataProvider {
    List<Class<? extends IBusinessObject>> getEntityTypes();
    Map<String, FieldMetaData> getChildEntity(Class<? extends IBusinessObject> businessObjectType);

    Class<?> getEndpointClass(MetaDataProvider metaDataProvider);
}
