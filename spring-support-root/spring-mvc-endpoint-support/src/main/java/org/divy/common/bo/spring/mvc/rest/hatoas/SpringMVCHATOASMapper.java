package org.divy.common.bo.spring.mvc.rest.hatoas;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.AbstractHATOASMapper;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.spring.mvc.rest.hatoas.SpringMVCRepresentation;
import org.springframework.hateoas.Link;

import java.util.Optional;
import java.util.UUID;


public class SpringMVCHATOASMapper<E extends BusinessObject<UUID>>
    extends AbstractHATOASMapper<E, SpringMVCRepresentation<UUID>, Link>
{

    public SpringMVCHATOASMapper(KeyValuePairMapper<E> keyValuePairMapper
            , LinkBuilderFactory linkBuilderFactory
            , MetaDataProvider metaDataProvider) {

        super((Class<SpringMVCRepresentation<UUID>>) (Class)SpringMVCRepresentation.class
                , keyValuePairMapper
                , linkBuilderFactory
                , metaDataProvider);
    }

    @Override
    protected void doFillLinks(SpringMVCRepresentation<UUID> representation, E businessObject) {
        final Optional<Class<?>> optionalEndPointClass = getMetaDataProvider().getEndpointClass(getMetaDataProvider());
        optionalEndPointClass.ifPresent(aClass -> representation.addLink(getLinkBuilderFactory().newBuilder()
                .buildLinkFor("self", aClass, "read")));

    }

    @Override
    protected void doFillAssociations(SpringMVCRepresentation<UUID> representation, E businessObject) {
        //noop
    }

    @Override
    protected void doReadLinks(SpringMVCRepresentation<UUID> representation, E businessObject) {
        //noop
    }

    @Override
    protected void doReadAssociations(SpringMVCRepresentation<UUID> representation, E businessObject) {
        //noop
    }
}
