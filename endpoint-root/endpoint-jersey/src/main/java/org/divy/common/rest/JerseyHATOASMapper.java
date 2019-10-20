package org.divy.common.rest;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.endpoint.jersey.hatoas.JerseyRepresentation;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.AbstractHATOASMapper;
import org.divy.common.bo.rest.LinkBuilderFactory;

import javax.ws.rs.core.Link;
import java.util.Optional;
import java.util.UUID;


public class JerseyHATOASMapper<E extends BusinessObject<UUID>>
    extends AbstractHATOASMapper<E, JerseyRepresentation<UUID>, Link>
{

    public JerseyHATOASMapper(KeyValuePairMapper<E> keyValuePairMapper
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
