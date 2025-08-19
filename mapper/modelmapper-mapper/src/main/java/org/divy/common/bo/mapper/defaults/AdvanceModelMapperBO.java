package org.divy.common.bo.mapper.defaults;

import org.divy.common.bo.mapper.modelmapper.AbstractModelMapperBOMapper;
import org.modelmapper.ModelMapper;

public class AdvanceModelMapperBO<B, O> extends AbstractModelMapperBOMapper<B, O> {

    public AdvanceModelMapperBO(Class<B> businessObjectType,
                               Class<O> otherObjectType,
                               ModelMapper modelMapper) {
        super(businessObjectType, otherObjectType);
        this.modelMapper = modelMapper;
    }

    @Override
    protected ModelMapper createMapper() {
        throw new IllegalStateException("ModelMapper should be created as part of constructor");
    }
}
