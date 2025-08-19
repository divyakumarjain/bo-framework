package org.divy.common.bo.mapper.modelmapper;

import org.divy.common.bo.mapper.BOMapper;
import org.modelmapper.ModelMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class AbstractModelMapperBOMapper<S, T> implements BOMapper<S, T> {

    protected Class<S> businessObjectType;
    protected Class<T> otherObjectType;
    protected ModelMapper modelMapper;

    protected AbstractModelMapperBOMapper(Class<S> businessObjectType, Class<T> otherObjectType) {
        this.businessObjectType = businessObjectType;
        this.otherObjectType = otherObjectType;
    }

    @Override
    public final S createBO(T sourceData) {
        return doCreateBO(sourceData);
    }

    protected S doCreateBO(T sourceData) {
        if(sourceData == null) {
            return null;
        }
        return getModelMapper().map(sourceData, businessObjectType);
    }

    @Override
    public final S mapToBO(T sourceData, S businessObject) {
        return doMapToBO(sourceData, businessObject);
    }

    protected S doMapToBO(T sourceData, S businessObject) {
        if(sourceData != null) {
            getModelMapper().map(sourceData, businessObject);
        }
        return businessObject;
    }

    protected Object createOtherTargetTypeInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return this.otherObjectType.getDeclaredConstructor().newInstance();
    }

    @Override
    public final T createFromBO(S businessObject) {
        return doCreateFromBO(businessObject);
    }

    protected T doCreateFromBO(S businessObject) {
        if(businessObject == null) {
            return null;
        }
        
        // Special handling for Map target type
        if (Map.class.isAssignableFrom(otherObjectType)) {
            return (T) org.divy.common.bo.mapper.modelmapper.builder.ModelMapperTypeMapperBuilderContext.convertObjectToMap(businessObject);
        }
        
        return getModelMapper().map(businessObject, otherObjectType);
    }

    @Override
    public final T mapFromBO(S businessObject, T targetData) {
        return doMapFromBO(businessObject, targetData);
    }

    protected T doMapFromBO(S businessObject, T targetData) {
        if(businessObject != null) {
            // Special handling for Map target type
            if (Map.class.isAssignableFrom(otherObjectType)) {
                Map<String, Object> convertedMap = org.divy.common.bo.mapper.modelmapper.builder.ModelMapperTypeMapperBuilderContext.convertObjectToMap(businessObject);
                if (targetData instanceof Map) {
                    ((Map<String, Object>) targetData).clear();
                    ((Map<String, Object>) targetData).putAll(convertedMap);
                }
            } else {
                getModelMapper().map(businessObject, targetData);
            }
        }
        return targetData;
    }

    @Override
    public final Collection<S> createBO(Collection<T> sourceData) {
        if(sourceData == null) {
            return null;
        }
        
        Collection<S> businessObjectList = sourceData instanceof List ? 
            new ArrayList<>(sourceData.size()) : new HashSet<>(sourceData.size());

        sourceData.forEach(other -> businessObjectList.add(createBO(other)));

        return businessObjectList;
    }

    @Override
    public final Collection<T> createFromBO(Collection<S> businessObjectList) {
        if(businessObjectList == null) {
            return null;
        }
        
        Collection<T> targetList = businessObjectList instanceof List ? 
            new ArrayList<>(businessObjectList.size()) : new HashSet<>(businessObjectList.size());

        for (S businessObject : businessObjectList) {
            targetList.add(createFromBO(businessObject));
        }

        return targetList;
    }

    public ModelMapper getModelMapper() {
        if(modelMapper == null) {
            this.modelMapper = createMapper();
        }
        return modelMapper;
    }

    protected abstract ModelMapper createMapper();
}
