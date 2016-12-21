package org.divy.common.bo.mapper.defaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.divy.common.bo.mapper.AbstractBOMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import static org.dozer.loader.api.FieldsMappingOptions.*;
import static org.dozer.loader.api.TypeMappingOptions.*;

public class AdvanceBOMapper<B, O> extends AbstractBOMapper<B, O> {

    private List<String> excludedLists;

    public AdvanceBOMapper(Class<B> businessObjectType,
            Class<O> otherObjectType) {
        super(businessObjectType, otherObjectType);

        this.mapper = createMapper();
    }

    public void setExcludedLists(List<String> excludedLists) {
        this.excludedLists = excludedLists;
    }


    public List<String> getExcludedList() {
        return new ArrayList<String>();
    }

    protected void configureMapping(TypeMappingBuilder mapper) {
        getExcludedList().stream().forEach(excludedField -> mapper.exclude(excludedField));
    }

    protected Mapper createMapper() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {

            protected void configure() {
                TypeMappingBuilder mapper = mapping(businessObjectType, otherObjectType);
                mapping(UUID.class, UUID.class, TypeMappingOptions.beanFactory(UuidBeanFactory.class.getName()));
                configureMapping(mapper);
            }
        };

        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(builder);
        return mapper;
    }

}
