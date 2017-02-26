package org.divy.common.bo.mapper;

import ma.glasnost.orika.MapperFacade;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;
import org.divy.common.bo.mapper.orika.builder.OrikaMapperBuilder;
import org.divy.common.bo.mapper.orika.builder.OrikaTypeMapperBuilderContext;
import org.divy.common.bo.metadata.MetaDataProvider;


public class BOMergeMapper<B extends IBusinessObject> extends AdvanceBOMapper<B, B>{
    public BOMergeMapper(Class<B> businessObjectType
            , MetaDataProvider metaDataProvider
            , OrikaMapperBuilder builder) {
        super(businessObjectType, businessObjectType, buildMapperFacade(businessObjectType, metaDataProvider, builder));
    }

    private static <B extends IBusinessObject> MapperFacade buildMapperFacade(Class<B> businessObjectType
            , MetaDataProvider metaDataProvider
            , OrikaMapperBuilder builder) {

        final OrikaTypeMapperBuilderContext<B, B> mapping = builder.mapping(businessObjectType, businessObjectType);

        mapping.field("lastUpdateTimestamp", FieldMapperOptions.exclude())
                .and()
                .field("createTimestamp", FieldMapperOptions.exclude());

//        metaDataProvider.getChildEntity(businessObjectType)
        return mapping.buildMapperFacade();
    }
}
