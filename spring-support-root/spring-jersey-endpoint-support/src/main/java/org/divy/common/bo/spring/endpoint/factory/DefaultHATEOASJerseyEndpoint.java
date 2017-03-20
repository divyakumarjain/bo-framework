package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.hypermedia.AbstractHATEOASJerseyEndpoint;
import org.divy.common.bo.endpoint.hypermedia.association.AbstractAssociations;
import org.divy.common.bo.rest.DefaultRepresentation;
import org.divy.common.bo.rest.HATEOASMapper;
import org.divy.common.bo.rest.builder.ResponseEntityBuilderFactory;

import java.util.UUID;

public class DefaultHATEOASJerseyEndpoint<E extends IBusinessObject<UUID>>
        extends AbstractHATEOASJerseyEndpoint<E, DefaultRepresentation, UUID> {


    private final IBOManager<E, UUID> manager;
    private final AbstractAssociations<E> associations;
    private final HATEOASMapper<E, DefaultRepresentation> greetingHATEOSMapper;
    private final Class<E> type;

    public DefaultHATEOASJerseyEndpoint(Class<E> type,
                                        IBOManager<E, UUID> manager,
                                        ResponseEntityBuilderFactory responseEntityBuilderFactory,
                                        HATEOASMapper<E, DefaultRepresentation> greetingHATEOASMapper) {
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
    public IBOManager<E, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HATEOASMapper<E, DefaultRepresentation> getRepresentationMapper() {
        return greetingHATEOSMapper;
    }

    @Override
    public AbstractAssociations<E> getAssociations() {
        return associations;
    }
}