package org.divy.common.rest;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.hypermedia.SpringMVCRepresentation;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.AbstractHyperMediaMapper;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.springframework.hateoas.Link;

import java.util.Optional;
import java.util.UUID;


public class SpringMVCHyperMediaMapper<E extends IBusinessObject<UUID>>
    extends AbstractHyperMediaMapper<E, SpringMVCRepresentation<UUID>, Link> {

    public SpringMVCHyperMediaMapper(KeyValuePairMapper<E> keyValuePairMapper
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
