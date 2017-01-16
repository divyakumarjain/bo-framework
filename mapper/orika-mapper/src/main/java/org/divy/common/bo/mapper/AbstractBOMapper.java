package org.divy.common.bo.mapper;

import ma.glasnost.orika.MapperFacade;
import org.divy.common.bo.mapper.IBOMapper;

import java.util.*;

public abstract class AbstractBOMapper<S, T> implements IBOMapper<S, T>{

    protected Class<S> businessObjectType;
    protected Class<T> otherObjectType;
    protected MapperFacade mapper;

    public AbstractBOMapper(Class<S> businessObjectType,
                            Class<T> otherObjectType) {
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
        return getMapper().map(sourceData, businessObjectType);
    }

    @Override
    public final S mapToBO(T sourceData, S businessObject) {

        return doMapToBO(sourceData, businessObject);
    }

    protected S doMapToBO(T sourceData, S businessObject) {
        
        if(sourceData != null) {
            getMapper().map(sourceData, businessObject);
        }

        return businessObject;
    }

    protected Object createOtherTargetTypeInstance() throws IllegalAccessException, InstantiationException {
        return this.otherObjectType.newInstance();
    }

    @Override
    public final T createFromBO(S businessObject) {
        return doCreateFromBO(businessObject);
    }

    protected T doCreateFromBO(S businessObject) {
        if(businessObject==null) {
            return null;
        }

        return getMapper().map(businessObject,otherObjectType);
    }

    @Override
    public final T mapFromBO(S businessObject, T targetData) {

        return doMapFromBO(businessObject, targetData);
    }

    protected T doMapFromBO(S businessObject, T targetData) {

        if(businessObject!=null) {
            getMapper().map(businessObject, targetData);
        }

        return targetData;
    }

    @Override
    public final Collection<S> createBO(Collection<T> sourceData) {

        Collection<S> businessObjectList = sourceData instanceof List? new ArrayList<>(sourceData.size()): new HashSet<>(sourceData.size());

        sourceData.forEach(other-> businessObjectList.add(createBO(other)));

        return businessObjectList;
    }

    @Override
    public final Collection<T> createFromBO(Collection<S> businessObjectList) {

        Collection<T> targetList = businessObjectList instanceof List? new ArrayList<T>(businessObjectList.size()): new HashSet<T>(businessObjectList.size());

        for (Iterator<S> iterator = businessObjectList.iterator(); iterator.hasNext();) {

            S businessObject = iterator.next();
            targetList.add(createFromBO(businessObject));
        }

        return targetList;
    }

    public MapperFacade getMapper() {
        if(mapper==null) {
            this.mapper = createMapper();
        }
        return mapper;
    }

    protected abstract MapperFacade createMapper();
}