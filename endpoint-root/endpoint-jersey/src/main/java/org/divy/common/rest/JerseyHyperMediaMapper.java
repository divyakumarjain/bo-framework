package org.divy.common.rest;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.hypermedia.JerseyRepresentation;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.AbstractHyperMediaMapper;
import org.divy.common.bo.rest.LinkBuilderFactory;

import javax.ws.rs.core.Link;
import java.util.Optional;
import java.util.UUID;


public class JerseyHyperMediaMapper<E extends IBusinessObject<UUID>>
    extends AbstractHyperMediaMapper<E, JerseyRepresentation<UUID>, Link> {

    public JerseyHyperMediaMapper(KeyValuePairMapper<E> keyValuePairMapper
            , LinkBuilderFactory linkBuilderFactory
            , MetaDataProvider metaDataProvider) {

        super((Class<JerseyRepresentation<UUID>>) (Class)JerseyRepresentation.class
                , keyValuePairMapper
                , linkBuilderFactory
                , metaDataProvider);
    }

    @Override
    protected void doFillLinks(JerseyRepresentation<UUID> representation, E businessObject) {
        final Optional<Class<?>> optionalEndPointClass = getMetaDataProvider().getEndpointClass(getMetaDataProvider());
        optionalEndPointClass.ifPresent(aClass -> representation.addLink(getLinkBuilderFactory().newBuilder()
                .buildLinkFor("self", aClass, "read")));

    }

    @Override
    protected void doFillAssociations(JerseyRepresentation<UUID> representation, E businessObject) {
        //noop
    }

    @Override
    protected void doReadLinks(JerseyRepresentation<UUID> representation, E businessObject) {
        //noop
    }

    @Override
    protected void doReadAssociations(JerseyRepresentation<UUID> representation, E businessObject) {
        //noop
    }
}
