package org.divy.common.bo.mapper.defaults;

import java.util.*;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.FieldMapBuilder;
import org.divy.common.bo.mapper.AbstractBOMapper;
import org.divy.common.bo.mapper.builder.FieldMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.MapperBuilderOption;
import org.divy.common.bo.mapper.builder.options.field.FieldHintA;
import org.divy.common.bo.mapper.builder.options.field.FieldHintB;

public class AdvanceBOMapper<B, O> extends AbstractBOMapper<B, O> {

    private Map<String, FieldMapperBuilderContext<B, O>> fields;
    private List<MapperBuilderOption> mapperBuilderOptions;

    public AdvanceBOMapper(Class<B> businessObjectType
            , Class<O> otherObjectType
            , List<MapperBuilderOption> mapperBuilderOptions
            , Map<String, FieldMapperBuilderContext<B, O>> fields) {

        super(businessObjectType, otherObjectType);
        this.mapperBuilderOptions = mapperBuilderOptions;
        this.fields = fields;
    }

    public AdvanceBOMapper(Class<B> businessObjectType,
                           Class<O> otherObjectType) {
        this(businessObjectType, otherObjectType, new ArrayList<>(0), new HashMap<>(0));
    }

    private void configureMapping(ClassMapBuilder<B, O> boClassMapBuilder) {
        mapperBuilderOptions
                .forEach( option -> configureMapping(boClassMapBuilder, option));

        fields.entrySet().forEach(fieldOptionEntry -> fieldOptionEntry.getValue().getMapperBuilderOptions()
                .forEach( fieldOption -> configFieldMapping(fieldOptionEntry.getKey(),fieldOptionEntry.getValue(), boClassMapBuilder)));
    }

    private void configFieldMapping(String fieldName, FieldMapperBuilderContext<B, O> context, ClassMapBuilder<B, O> boClassMapBuilder) {
        final FieldMapBuilder<B, O> boFieldMapBuilder = boClassMapBuilder.fieldMap(fieldName, fieldName);
        context.getMapperBuilderOptions().forEach(option -> configFieldMapping(option, boFieldMapBuilder));
        boFieldMapBuilder.add();

    }

    private void configFieldMapping(MapperBuilderOption option, FieldMapBuilder<B, O> boFieldMapBuilder) {
        if (option instanceof FieldHintA) {
            FieldHintA fieldHintA = (FieldHintA) option;
            boFieldMapBuilder.aElementType(fieldHintA.getHintClass());
        } else if (option instanceof FieldHintB) {
            FieldHintB fieldHintB = (FieldHintB) option;
            boFieldMapBuilder.bElementType(fieldHintB.getHintClass());
        }
    }

    private void configureMapping(ClassMapBuilder<B, O> boClassMapBuilder, MapperBuilderOption option) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    protected MapperFacade createMapper() {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        final ClassMapBuilder<B, O> boClassMapBuilder = mapperFactory.classMap(businessObjectType, otherObjectType);

        configureMapping(boClassMapBuilder);

        boClassMapBuilder
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }

}
