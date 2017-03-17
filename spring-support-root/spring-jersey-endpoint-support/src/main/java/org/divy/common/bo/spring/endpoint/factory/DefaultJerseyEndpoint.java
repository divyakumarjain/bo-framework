package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractBOJerseyEndpoint;
import org.divy.common.bo.rest.LinkBuilderFactory;

import java.util.UUID;

public class DefaultJerseyEndpoint<E extends IBusinessObject<UUID>> extends AbstractBOJerseyEndpoint<E, UUID> {

    public DefaultJerseyEndpoint(IBOManager<E, UUID> manager,
                                 LinkBuilderFactory linkBuilderFactory) {
        super(manager, linkBuilderFactory);
    }
}