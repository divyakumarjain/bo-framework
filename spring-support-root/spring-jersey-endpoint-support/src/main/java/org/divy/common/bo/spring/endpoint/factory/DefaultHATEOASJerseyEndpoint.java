package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.hypermedia.association.AssociationsHandler;
import org.divy.common.bo.endpoint.jersey.hypermedia.AbstractHyperMediaJerseyEndpoint;
import org.divy.common.bo.endpoint.jersey.hypermedia.JerseyRepresentation;
import org.divy.common.bo.rest.HyperMediaMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import java.util.UUID;

public class DefaultHATEOASJerseyEndpoint<E extends BusinessObject<UUID>>
        extends AbstractHyperMediaJerseyEndpoint<E, JerseyRepresentation<UUID>, UUID> {


    private final BOManager<E, UUID> manager;
    private final HyperMediaMapper<E, JerseyRepresentation<UUID>> greetingHATEOSMapper;

    public DefaultHATEOASJerseyEndpoint(BOManager<E, UUID> manager,
                                        ResponseEntityBuilderFactory responseEntityBuilderFactory,
                                        HyperMediaMapper<E, JerseyRepresentation<UUID>> greetingHATEOASMapper,
                                        AssociationsHandler<E,UUID> associationsHandler) {
        super(responseEntityBuilderFactory, associationsHandler);
        this.manager = manager;
        this.greetingHATEOSMapper = greetingHATEOASMapper;
    }

    @Override
    public BOManager<E, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HyperMediaMapper<E, JerseyRepresentation<UUID>> getRepresentationMapper() {
        return greetingHATEOSMapper;
    }
}