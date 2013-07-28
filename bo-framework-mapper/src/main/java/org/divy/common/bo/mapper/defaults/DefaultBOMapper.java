package org.divy.common.bo.mapper.defaults;

import org.divy.common.bo.mapper.AbstractBOMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DefaultBOMapper<BO, OTHER> extends AbstractBOMapper<BO, OTHER>{
	
	public DefaultBOMapper(Class<? extends BO> businessObjectType, Class<OTHER> otherObjectType,Mapper mapper){
		super(businessObjectType,otherObjectType);
		
		this.mapper = mapper;
	}
	
	public DefaultBOMapper(Class<? extends BO> businessObjectType, Class<OTHER> otherObjectType){
		super(businessObjectType,otherObjectType);
		
		this.mapper = new DozerBeanMapper();
	}
}
