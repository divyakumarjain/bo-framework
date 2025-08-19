package org.divy.common.bo.mapper.modelmapper;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.modelmapper.builder.ModelMapperBuilder;
import org.divy.common.bo.mapper.modelmapper.builder.ModelMapperTypeMapperBuilderContext;
import org.modelmapper.ModelMapper;

public class BOMergeModelMapper<B extends BusinessObject<I>, I> extends AbstractModelMapperBOMapper<B, B> {
    
    private final ModelMapperBuilder builder;
    
    public BOMergeModelMapper(Class<B> businessObjectType, ModelMapperBuilder builder) {
        super(businessObjectType, businessObjectType);
        this.builder = builder;
    }

    @Override
    protected ModelMapper createMapper() {
        return buildModelMapper(businessObjectType, builder);
    }

    private static <B extends BusinessObject<I>, I> ModelMapper buildModelMapper(Class<B> businessObjectType, ModelMapperBuilder builder) {

        final ModelMapperTypeMapperBuilderContext<B, B> mapping = (ModelMapperTypeMapperBuilderContext<B, B>) builder.mapping(businessObjectType, businessObjectType);

        mapping.field("lastUpdateTimestamp", FieldMapperOptions.exclude())
              .and()
                    .field("createTimestamp", FieldMapperOptions.exclude())
              .and()
                    .field("uuid", FieldMapperOptions.exclude());

        return mapping.buildModelMapper();
    }
}
