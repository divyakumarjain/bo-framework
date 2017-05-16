package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.hypermedia.AbstractHyperMediaJerseyEndpoint;
import org.divy.common.bo.endpoint.hypermedia.JerseyRepresentation;
import org.divy.common.bo.endpoint.hypermedia.association.AbstractAssociations;
import org.divy.common.bo.rest.HyperMediaMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import java.util.UUID;

public class DefaultHATEOASJerseyEndpoint<E extends BusinessObject<UUID>>
        extends AbstractHyperMediaJerseyEndpoint<E, JerseyRepresentation<UUID>, UUID> {


    private final BOManager<E, UUID> manager;
    private final AbstractAssociations<E> associations;
    private final HyperMediaMapper<E, JerseyRepresentation<UUID>> greetingHATEOSMapper;
    private final Class<E> type;

    public DefaultHATEOASJerseyEndpoint(Class<E> type,
                                        BOManager<E, UUID> manager,
                                        ResponseEntityBuilderFactory responseEntityBuilderFactory,
                                        HyperMediaMapper<E, JerseyRepresentation<UUID>> greetingHATEOASMapper) {
        super(responseEntityBuilderFactory);
        this.manager = manager;
        this.greetingHATEOSMapper = greetingHATEOASMapper;
        this.type = type;
        this.associations = new AbstractAssociations<E>(DefaultHATEOASJerseyEndpoint.this.type) {
            @Override
            protected void doBuildAssociations() {
//                association().name("pages").includeInRead()
//                        .attribute("section")
            }
        };
    }

    @Override
    public BOManager<E, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HyperMediaMapper<E, JerseyRepresentation<UUID>> getRepresentationMapper() {
        return greetingHATEOSMapper;
    }

    @Override
    public AbstractAssociations<E> getAssociations() {
        return associations;
    }
}