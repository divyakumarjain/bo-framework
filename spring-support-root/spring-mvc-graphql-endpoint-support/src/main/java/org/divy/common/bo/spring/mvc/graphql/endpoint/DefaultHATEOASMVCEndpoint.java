package org.divy.common.bo.spring.mvc.rest.endpoint;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.rest.HATOASMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.mvc.rest.hatoas.SpringMVCRepresentation;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class DefaultHATEOASMVCEndpoint<B extends BusinessObject<UUID>>
        extends AbstractHATOASMVCEndpoint<B, SpringMVCRepresentation<UUID>, UUID, Link>
{


    private final BOManager<B, UUID>                       manager;
    private final HATOASMapper<B, SpringMVCRepresentation<UUID>> hateosMapper;

    public DefaultHATEOASMVCEndpoint(BOManager<B, UUID> manager,
                                        ResponseEntityBuilderFactory<SpringMVCRepresentation<UUID>, ResponseEntity<SpringMVCRepresentation<UUID>>> responseEntityBuilderFactory,
                                        HATOASMapper<B, SpringMVCRepresentation<UUID>> hateosMapper,
                                        AssociationsHandler<B,UUID, Link> associationsHandler) {
        super(responseEntityBuilderFactory, associationsHandler);
        this.manager = manager;
        this.hateosMapper = hateosMapper;

    }

    @Override
    public BOManager<B, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HATOASMapper<B, SpringMVCRepresentation<UUID>> getRepresentationMapper() {
        return hateosMapper;
    }
}
