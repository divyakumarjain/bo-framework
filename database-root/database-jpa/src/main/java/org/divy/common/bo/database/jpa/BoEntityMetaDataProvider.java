package org.divy.common.bo.database.jpa;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.metadata.FieldMetaData;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.beans.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    public Map<String, FieldMetaData> getChildEntities(Class<? extends BusinessObject> businessObjectType) {
        return resolveMatchingChildEntities(businessObjectType, this::isAssociation);
    }

    @Override
    public Map<String, FieldMetaData> getEmbeddedEntities(Class<? extends BusinessObject> businessObjectType) {
        return resolveMatchingChildEntities(businessObjectType, this::isEmbedded);
    }

    private Map<String, FieldMetaData> resolveMatchingChildEntities(Class<? extends BusinessObject> businessObjectType, Predicate<PropertyDescriptor> predicate) {
        HashMap<String, FieldMetaData> result = new HashMap<>();
        try {
            var beanInfo = Introspector.getBeanInfo(businessObjectType);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            result = Arrays.stream(pds)
                    .filter(predicate)
                    .collect(Collectors.toMap(FeatureDescriptor::getName, FieldMetaData::new, (a, b) -> b, HashMap::new));
        } catch (IntrospectionException e) {
            LOGGER.debug("Could not get Child Entities", e);
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

    private boolean isEmbedded(PropertyDescriptor pd) {
        return hasAnnotation(pd, Embedded.class);
    }

    private boolean hasAnnotation(PropertyDescriptor pd, Class annotation) {
        return isReadMethodAnnotated(pd, annotation) || isFieldAnnotated(pd, annotation);
    }

    private boolean isFieldAnnotated(PropertyDescriptor pd, Class annotation) {
        Class<?> declaringClass = pd.getReadMethod().getDeclaringClass();
        try {
            var field = declaringClass.getField(pd.getName());
            return field.getAnnotation(annotation) != null;
        } catch (NoSuchFieldException e) {
            LOGGER.debug("Could not find field with name {} in {}", pd.getName(), declaringClass);
        }
        return false;
    }

    private boolean isReadMethodAnnotated(PropertyDescriptor pd, Class annotation) {
        var readMethod = pd.getReadMethod();
        return readMethod!=null && readMethod.getAnnotation(annotation) !=null;
    }
}
