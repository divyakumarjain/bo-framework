package org.divy.common.bo.spring.jersey.rest.hatoas;

import org.divy.common.bo.jersey.rest.hatoas.AbstractHATOASJerseyEndpoint;
import org.divy.common.bo.jersey.rest.hatoas.JerseyRepresentation;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.rest.HATOASMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import jakarta.ws.rs.core.Link;
import java.util.UUID;

public class DefaultHATEOASJerseyEndpoint<E extends BusinessObject<UUID>>
        extends AbstractHATOASJerseyEndpoint<E, JerseyRepresentation<UUID>, UUID>
{


    private final BOManager<E, UUID>                          manager;
    private final HATOASMapper<E, JerseyRepresentation<UUID>> greetingHATEOSMapper;

    public DefaultHATEOASJerseyEndpoint(BOManager<E, UUID> manager,
                                        ResponseEntityBuilderFactory responseEntityBuilderFactory,
                                        HATOASMapper<E, JerseyRepresentation<UUID>> greetingHATEOASMapper,
                                        AssociationsHandler<E,UUID, Link> associationsHandler) {
        super(responseEntityBuilderFactory, associationsHandler);
        this.manager = manager;
        this.greetingHATEOSMapper = greetingHATEOASMapper;
    }

    @Override
    public BOManager<E, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HATOASMapper<E, JerseyRepresentation<UUID>> getRepresentationMapper() {
        return greetingHATEOSMapper;
    }
}
