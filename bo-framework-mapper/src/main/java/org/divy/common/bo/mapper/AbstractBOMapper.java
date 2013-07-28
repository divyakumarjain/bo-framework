package org.divy.common.bo.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;

public class AbstractBOMapper<BO, OTHER> implements IBOMapper<BO, OTHER>{

	protected Class<BO> businessObjectType;
	protected Class<OTHER> otherObjectType;
	protected Mapper mapper;

	public AbstractBOMapper() {
		super();
	}

	public AbstractBOMapper(Class<? extends BO> businessObjectType,
			Class<OTHER> otherObjectType) {
		this.businessObjectType = (Class<BO>) businessObjectType;
		this.otherObjectType = otherObjectType;
	}

	@Override
	public final BO createBO(OTHER sourceData) {
		return doCreateBO(sourceData);
	}

	protected BO doCreateBO(OTHER sourceData) {
		if(sourceData == null)
			return null;
		return mapper.map(sourceData, businessObjectType);
	}

	@Override
	public final BO mapToBO(OTHER sourceData, BO businessObject) {
		
		return doMapToBO(sourceData, businessObject);
	}

	protected BO doMapToBO(OTHER sourceData, BO businessObject) {
		
		if(sourceData != null)
			mapper.map(sourceData, businessObject);
		
		return businessObject;
	}

	@Override
	public final OTHER createFromBO(BO businessObject) {
		return doCreateFromBO(businessObject);
	}

	protected OTHER doCreateFromBO(BO businessObject) {
		if(businessObject==null)
			return null;
		
		return mapper.map(businessObject,otherObjectType);
	}

	@Override
	public final OTHER mapFromBO(BO businessObject, OTHER targetData) {
		
		return doMapFromBO(businessObject, targetData);
	}

	protected OTHER doMapFromBO(BO businessObject, OTHER targetData) {
		
		if(businessObject!=null)
			mapper.map(businessObject,targetData);
		
		return targetData;
	}

	@Override
	public final List<BO> createBO(List<OTHER> sourceData) {
		
		List<BO> businessObjectList = new ArrayList<>(sourceData.size());
		
		for (Iterator<OTHER> iterator = sourceData.iterator(); iterator.hasNext();) {
			
			OTHER other = (OTHER) iterator.next();
			businessObjectList.add(createBO(other));
		}
		
		return businessObjectList;
	}

	@Override
	public final List<OTHER> createFromBO(List<BO> businessObjectList) {
		
		List<OTHER> targetList = new ArrayList<>(businessObjectList.size());
		
		for (Iterator<BO> iterator = businessObjectList.iterator(); iterator.hasNext();) {
			
			BO businessObject = (BO) iterator.next();
			targetList.add(createFromBO(businessObject));
		}
		
		return targetList;
	}

}