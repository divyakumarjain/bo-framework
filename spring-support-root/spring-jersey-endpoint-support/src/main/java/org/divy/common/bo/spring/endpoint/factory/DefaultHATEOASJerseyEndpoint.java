package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.endpoint.jersey.hatoas.AbstractHATOASJerseyEndpoint;
import org.divy.common.bo.endpoint.jersey.hatoas.JerseyRepresentation;
import org.divy.common.bo.rest.HATOASMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import java.util.UUID;

public class DefaultHATEOASJerseyEndpoint<E extends BusinessObject<UUID>>
        extends AbstractHATOASJerseyEndpoint<E, JerseyRepresentation<UUID>, UUID> {


    private final BOManager<E, UUID>                          manager;
    private final HATOASMapper<E, JerseyRepresentation<UUID>> greetingHATEOSMapper;

    public DefaultHATEOASJerseyEndpoint(BOManager<E, UUID> manager,
                                        ResponseEntityBuilderFactory responseEntityBuilderFactory,
                                        HATOASMapper<E, JerseyRepresentation<UUID>> greetingHATEOASMapper,
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
    public HATOASMapper<E, JerseyRepresentation<UUID>> getRepresentationMapper() {
        return greetingHATEOSMapper;
    }
}
