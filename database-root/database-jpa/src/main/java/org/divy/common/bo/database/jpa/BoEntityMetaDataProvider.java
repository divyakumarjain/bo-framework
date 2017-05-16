package org.divy.common.bo.database.jpa;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.metadata.FieldMetaData;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BoEntityMetaDataProvider implements MetaDataProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoEntityMetaDataProvider.class);

    private final List<Class<? extends BusinessObject>> entityTypes;

    public BoEntityMetaDataProvider(List<Class<? extends BusinessObject>> typeList) {
        entityTypes = typeList;
    }

    @Override
    public List<Class<? extends BusinessObject>> getEntityTypes() {
        return entityTypes;
    }

    @Override
    public Map<String, FieldMetaData> getChildEntity(Class<? extends BusinessObject> businessObjectType) {
        HashMap<String, FieldMetaData> result = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(businessObjectType);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (int i = 0, pdsLength = pds.length; i < pdsLength; i++) {
                PropertyDescriptor pd = pds[i];
                if (isAssociation(pd)) {
                    result.put(pd.getName(), new FieldMetaData(pd));
                }
            }
        } catch (IntrospectionException e) {
            LOGGER.error("Could not get Child Entities", e);
        }
        return result;
    }

    @Override
    public Optional<Class<?>> getEndpointClass(MetaDataProvider metaDataProvider) {
       return Optional.empty();
    }

    private boolean isAssociation(PropertyDescriptor pd) {
        return hasAnnotation(pd, OneToMany.class)
                || hasAnnotation(pd, OneToOne.class)
                || hasAnnotation(pd, ManyToMany.class)
                || hasAnnotation(pd, ManyToOne.class);
    }

    private boolean hasAnnotation(PropertyDescriptor pd, Class annotation) {
        Method readMethod = pd.getReadMethod();
        return readMethod!=null && readMethod.getAnnotation(annotation) !=null;
    }
}
