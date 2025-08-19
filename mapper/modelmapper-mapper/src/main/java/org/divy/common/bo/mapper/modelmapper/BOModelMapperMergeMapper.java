package org.divy.common.bo.mapper.modelmapper;

import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.defaults.AdvanceModelMapperBO;
import org.divy.common.bo.mapper.modelmapper.builder.ModelMapperBuilder;
import org.divy.common.bo.mapper.modelmapper.builder.ModelMapperTypeMapperBuilderContext;
import org.divy.common.bo.repository.BusinessObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class BOModelMapperMergeMapper<B extends BusinessObject<I>, I> extends AdvanceModelMapperBO<B, B> {

    public BOModelMapperMergeMapper(Class<B> businessObjectType, ModelMapperBuilder builder) {
        super(businessObjectType, businessObjectType, buildModelMapper(businessObjectType, builder));
    }

    private static <B extends BusinessObject<I>, I> ModelMapper buildModelMapper(Class<B> businessObjectType, ModelMapperBuilder builder) {
        final ModelMapperTypeMapperBuilderContext<B, B> mapping = 
            (ModelMapperTypeMapperBuilderContext<B, B>) builder.mapping(businessObjectType, businessObjectType);

        // Configure field exclusions similar to Orika version
        mapping.field("lastUpdateTimestamp", FieldMapperOptions.exclude())
               .and()
               .field("createTimestamp", FieldMapperOptions.exclude())
               .and()
               .field("uuid", FieldMapperOptions.exclude());

        return mapping.buildMapperFacade();
    }

    /**
     * Alternative constructor that creates a ModelMapper with explicit field exclusions
     * This provides a more direct approach when the builder pattern is not needed
     */
    public BOModelMapperMergeMapper(Class<B> businessObjectType) {
        super(businessObjectType, businessObjectType, createSimpleMergeMapper(businessObjectType));
    }

    private static <B extends BusinessObject<I>, I> ModelMapper createSimpleMergeMapper(Class<B> businessObjectType) {
        ModelMapper mapper = new ModelMapper();
        
        // Configure to exclude timestamp and uuid fields - simpler than Orika!
        mapper.getConfiguration()
               .setPropertyCondition(context -> {
                   String propertyName = context.getMapping().getLastDestinationProperty().getName();
                   return !propertyName.equals("lastUpdateTimestamp") &&
                          !propertyName.equals("createTimestamp") &&
                          !propertyName.equals("uuid");
               });
        
        return mapper;
    }
}
