package org.divy.common.bo.jersey.rest;

import org.divy.common.bo.endpoint.hatoas.Representation;
import org.divy.common.bo.jersey.rest.hatoas.JerseyRepresentation;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.AbstractHATOASMapper;
import org.divy.common.bo.rest.LinkBuilderFactory;

import javax.ws.rs.core.Link;
import java.util.Map;
import java.util.Optional;

public class JerseyHATOASMapper<E extends BusinessObject<I>, I>
    extends AbstractHATOASMapper<E, I, Representation<I, Map<String, Object>, Link>, Link>
{

    public JerseyHATOASMapper(KeyValuePairMapper<E> keyValuePairMapper
            , LinkBuilderFactory<Link> linkBuilderFactory
            , MetaDataProvider metaDataProvider) {

        super((Class)JerseyRepresentation.class
                , keyValuePairMapper
                , linkBuilderFactory
                , metaDataProvider);
    }

    @Override
    protected void doFillLinks(Representation<I, Map<String, Object>, Link> representation, E businessObject) {
        final Optional<Class<?>> optionalEndPointClass = getMetaDataProvider().getEndpointClass(getMetaDataProvider());
        optionalEndPointClass.ifPresent(aClass -> representation.addLink(getLinkBuilderFactory().newBuilder()
                .buildLinkFor("self", aClass, "read")));

    }

    @Override
    protected void doFillAssociations(Representation<I, Map<String, Object>, Link> representation, E businessObject) {
        //noop
    }

    @Override
    protected void doReadLinks(Representation<I, Map<String, Object>, Link> representation, E businessObject) {
        //noop
    }

    @Override
    protected void doReadAssociations(Representation<I, Map<String, Object>, Link> representation, E businessObject) {
        //noop
    }
}
