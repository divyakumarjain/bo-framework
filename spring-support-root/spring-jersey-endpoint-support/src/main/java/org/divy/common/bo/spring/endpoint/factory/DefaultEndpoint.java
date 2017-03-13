package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.association.AbstractAssociations;
import org.divy.common.bo.endpoint.hypermedia.AbstractHATEOASEndpoint;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilderFactory;
import org.divy.common.rest.impl.DefaultRepresentation;

import java.util.UUID;

public class DefaultEndpoint<E extends IBusinessObject<UUID>> extends AbstractHATEOASEndpoint<E, DefaultRepresentation, UUID> {


    private final IBOManager<E, UUID> manager;
    private final AbstractAssociations<E> associations;
    private final HATEOASMapper<E, DefaultRepresentation> greetingHATEOSMapper;
    private final Class<E> type;

    public DefaultEndpoint(Class<E> type,
                           IBOManager<E, UUID> manager,
                           LinkBuilderFactory linkBuilderFactory,
                           HATEOASMapper<E, DefaultRepresentation> greetingHATEOASMapper) {
        super(linkBuilderFactory);
        this.manager = manager;
        this.greetingHATEOSMapper = greetingHATEOASMapper;
        this.type = type;
        this.associations = new AbstractAssociations<E>(DefaultEndpoint.this.type) {
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