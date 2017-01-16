package org.divy.common.bo.mapper.defaults;

import org.divy.common.bo.mapper.AbstractBOMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DefaultBOMapper<B, O> extends AbstractBOMapper<B, O>{

    public DefaultBOMapper(Class<B> businessObjectType, Class<O> otherObjectType, Mapper mapper){
        super(businessObjectType,otherObjectType);

        this.mapper = mapper;
    }

    public DefaultBOMapper(Class<B> businessObjectType, Class<O> otherObjectType){
        super(businessObjectType,otherObjectType);

        this.mapper = createMapper();
    }

    @Override
    protected Mapper createMapper() {
        return new DozerBeanMapper();
    }
}
