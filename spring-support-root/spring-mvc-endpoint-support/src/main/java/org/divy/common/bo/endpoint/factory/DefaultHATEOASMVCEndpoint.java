package org.divy.common.bo.endpoint.factory;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.AbstractHyperMediaMVCEndpoint;
import org.divy.common.bo.endpoint.hypermedia.SpringMVCRepresentation;
import org.divy.common.bo.endpoint.hypermedia.association.AssociationsHandler;
import org.divy.common.bo.rest.HyperMediaMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import java.util.UUID;

public class DefaultHATEOASMVCEndpoint<E extends BusinessObject<UUID>>
        extends AbstractHyperMediaMVCEndpoint<E, SpringMVCRepresentation, UUID> {


    private final BOManager<E, UUID> manager;
    private final HyperMediaMapper<E, SpringMVCRepresentation> hateosMapper;
    private final Class<E> type;

    public DefaultHATEOASMVCEndpoint(Class<E> type,
                                        BOManager<E, UUID> manager,
                                        ResponseEntityBuilderFactory responseEntityBuilderFactory,
                                        HyperMediaMapper<E, SpringMVCRepresentation> hateosMapper,
                                        AssociationsHandler<E,UUID> associationsHandler) {
        super(responseEntityBuilderFactory, associationsHandler);
        this.manager = manager;
        this.hateosMapper = hateosMapper;
        this.type = type;

    }

    @Override
    public BOManager<E, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HyperMediaMapper<E, SpringMVCRepresentation> getRepresentationMapper() {
        return hateosMapper;
    }
}