package org.divy.common.bo.mapper.defaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.divy.common.bo.mapper.AbstractBOMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

public class AdvanceBOMapper<B, O> extends AbstractBOMapper<B, O> {

    public AdvanceBOMapper(Class<B> businessObjectType,
            Class<O> otherObjectType) {
        super(businessObjectType, otherObjectType);

        this.mapper = createMapper();
    }

    public List<String> getExcludedList() {
        return new ArrayList<>();
    }

    protected void configureMapping(TypeMappingBuilder mapper) {
        getExcludedList().stream().forEach(mapper::exclude);
    }

    protected Mapper createMapper() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {

            @Override
            protected void configure() {
                TypeMappingBuilder mapper = mapping(businessObjectType, otherObjectType);
                configureMapping(mapper);
                mapping(UUID.class, UUID.class, TypeMappingOptions.beanFactory(UuidBeanFactory.class.getName()));
                mapping(UUID.class, UUID.class, TypeMappingOptions.beanFactory(UuidBeanFactory.class.getName()));
            }
        };

        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(builder);
        return mapper;
    }

}
