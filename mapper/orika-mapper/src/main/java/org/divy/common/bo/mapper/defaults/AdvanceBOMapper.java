package org.divy.common.bo.mapper.defaults;

import ma.glasnost.orika.MapperFacade;
import org.divy.common.bo.mapper.orika.AbstractBOMapper;

public class AdvanceBOMapper<B, O> extends AbstractBOMapper<B, O> {

    public AdvanceBOMapper(Class<B> businessObjectType
            , Class<O> otherObjectType
            , MapperFacade mapperFacade) {

        super(businessObjectType, otherObjectType);
        this.mapperFacade = mapperFacade;
    }

    @Override
    protected MapperFacade createMapper() {
        throw new IllegalStateException("Mapper Facade should be created as part of constructor");
    }
}
