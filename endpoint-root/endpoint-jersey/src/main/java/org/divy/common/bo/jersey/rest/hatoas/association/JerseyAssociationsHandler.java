package org.divy.common.bo.jersey.rest.hatoas.association;

import org.divy.common.bo.endpoint.hatoas.association.Cardinality;
import org.divy.common.bo.endpoint.hatoas.association.builder.AssociationBuilder;
import org.divy.common.bo.jersey.rest.JerseyHATOASMapper;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.endpoint.hatoas.association.AbstractAssociationsHandler;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.divy.common.bo.metadata.FieldMetaData;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.LinkBuilderFactory;

import java.util.*;

public class JerseyAssociationsHandler<T extends BusinessObject<UUID>> extends AbstractAssociationsHandler<T>
{

    protected JerseyAssociationsHandler(Class<T> source
            , MetaDataProvider metaDataProvider
            , LinkBuilderFactory linkBuilderFactory
            , MapperBuilder mapperBuilder){
        super(metaDataProvider, source, mapperBuilder, linkBuilderFactory);
    }

    protected void doBuildAssociations() {
        final Map<String, FieldMetaData> childEntities = metaDataProvider.getChildEntities(source);
        childEntities.forEach((name, entityMeta) -> {
            final AssociationBuilder association = association();
            association
                  .withMapper(new JerseyHATOASMapper<>(
                        new KeyValuePairMapperImpl<>(source, mapperBuilder, metaDataProvider), linkBuilderFactory,metaDataProvider))
                  .attribute(name);

            if(entityMeta.isCollection()) {
                association.cardinality( Cardinality.MANY);
            }
        });

    }
}
