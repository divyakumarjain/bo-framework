package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.spring.endpoint.AbstractHATOASMVCEndpoint;
import org.divy.common.bo.spring.endpoint.hatoas.SpringMVCRepresentation;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.rest.HATOASMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import java.util.UUID;

public class DefaultHATEOASMVCEndpoint<E extends BusinessObject<UUID>>
        extends AbstractHATOASMVCEndpoint<E, SpringMVCRepresentation, UUID>
{


    private final BOManager<E, UUID>                       manager;
    private final HATOASMapper<E, SpringMVCRepresentation> hateosMapper;

    public DefaultHATEOASMVCEndpoint(BOManager<E, UUID> manager,
                                        ResponseEntityBuilderFactory responseEntityBuilderFactory,
                                        HATOASMapper<E, SpringMVCRepresentation> hateosMapper,
                                        AssociationsHandler<E,UUID> associationsHandler) {
        super(responseEntityBuilderFactory, associationsHandler);
        this.manager = manager;
        this.hateosMapper = hateosMapper;

    }

    @Override
    public BOManager<E, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HATOASMapper<E, SpringMVCRepresentation> getRepresentationMapper() {
        return hateosMapper;
    }
}
