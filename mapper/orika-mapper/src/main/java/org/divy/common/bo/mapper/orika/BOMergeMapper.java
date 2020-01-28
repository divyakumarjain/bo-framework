package org.divy.common.bo.mapper.orika;

import ma.glasnost.orika.MapperFacade;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.defaults.AdvanceBOMapper;
import org.divy.common.bo.mapper.orika.builder.OrikaMapperBuilder;
import org.divy.common.bo.mapper.orika.builder.OrikaTypeMapperBuilderContext;
import org.divy.common.bo.metadata.MetaDataProvider;


public class BOMergeMapper<B extends BusinessObject> extends AdvanceBOMapper<B, B>{
    public BOMergeMapper(Class<B> businessObjectType
            , MetaDataProvider metaDataProvider
            , OrikaMapperBuilder builder) {
        super(businessObjectType, businessObjectType, buildMapperFacade(businessObjectType, metaDataProvider, builder));
    }

    private static <B extends BusinessObject> MapperFacade buildMapperFacade(Class<B> businessObjectType
            , MetaDataProvider metaDataProvider
            , OrikaMapperBuilder builder) {

        final OrikaTypeMapperBuilderContext<B, B> mapping = builder.mapping(businessObjectType, businessObjectType);

        mapping.field("lastUpdateTimestamp", FieldMapperOptions.exclude())
              .and()
                    .field("createTimestamp", FieldMapperOptions.exclude())
              .and()
                    .field( "uuid", FieldMapperOptions.exclude() );

        return mapping.buildMapperFacade();
    }
}
