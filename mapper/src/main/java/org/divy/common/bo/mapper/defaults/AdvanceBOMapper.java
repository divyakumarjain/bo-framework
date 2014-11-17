package org.divy.common.bo.mapper.defaults;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.divy.common.bo.mapper.AbstractBOMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;

public class AdvanceBOMapper<BO, OTHER> extends AbstractBOMapper<BO, OTHER> {

    private List<String> excludedLists;

    public AdvanceBOMapper(Class<? extends BO> businessObjectType,
            Class<OTHER> otherObjectType) {
        super(businessObjectType, otherObjectType);

        this.mapper = createMapper();
    }

    public void setExcludedLists(List<String> excludedLists) {
        this.excludedLists = excludedLists;
    }


    private List<String> getExcludedList() {
        return new ArrayList<String>();
    }

    protected void configureMapping(TypeMappingBuilder mapper) {

        excludedLists = getExcludedList();

        for (Iterator<String> iterator = excludedLists.iterator(); iterator
                .hasNext();) {
            String excludedField = iterator.next();
            mapper.exclude(excludedField);
        }
    }

    protected Mapper createMapper() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                TypeMappingBuilder mapper = mapping(businessObjectType, otherObjectType);

                configureMapping(mapper);
            }
        };

        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(builder);
        return mapper;
    }
}
