package org.divy.common.bo.spring.mvc.graphql.hatoas.association;

import org.divy.common.bo.endpoint.hatoas.association.AbstractAssociationsHandler;
import org.divy.common.bo.endpoint.hatoas.association.Cardinality;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.divy.common.bo.metadata.FieldMetaData;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.spring.mvc.graphql.hatoas.SpringMVCHATOASMapper;
import org.springframework.hateoas.Link;

import java.util.Map;

public class SpringAssociationsHandler<E extends BusinessObject<I>, I> extends AbstractAssociationsHandler<E, I, Link> {

    protected SpringAssociationsHandler(Class<E> source,
          MetaDataProvider metaDataProvider,
          LinkBuilderFactory<Link> linkBuilderFactory,
          MapperBuilder mapperBuilder){

        super(metaDataProvider, source, mapperBuilder, linkBuilderFactory);
    }

    protected void doBuildAssociations() {
        final Map<String, FieldMetaData> childEntities = metaDataProvider.getChildEntities(source);
        childEntities.forEach((name, entityMeta) -> {
            final var association = association();
            var keyValuePairMapper = new KeyValuePairMapperImpl<>( source, mapperBuilder, metaDataProvider );
            var mapper = new SpringMVCHATOASMapper<E, I>( keyValuePairMapper, linkBuilderFactory, metaDataProvider );

            association
                  .withMapper( mapper )
                  .attribute(name);

            if(entityMeta.isCollection()) {
                association.cardinality(Cardinality.MANY);
            }
        });

    }

}
