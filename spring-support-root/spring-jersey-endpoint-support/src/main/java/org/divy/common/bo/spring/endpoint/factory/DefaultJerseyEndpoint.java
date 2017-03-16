package org.divy.common.bo.spring.endpoint.factory;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractBOJerseyEndpoint;
import org.divy.common.bo.endpoint.hypermedia.association.AbstractAssociations;
import org.divy.common.bo.rest.LinkBuilderFactory;

import java.util.UUID;

public class DefaultJerseyEndpoint<E extends IBusinessObject<UUID>> extends AbstractBOJerseyEndpoint<E, UUID> {


    private final AbstractAssociations<E> associations;
    private final Class<E> type;

    public DefaultJerseyEndpoint(Class<E> type,
                                 IBOManager<E, UUID> manager,
                                 LinkBuilderFactory linkBuilderFactory) {
        super(manager, linkBuilderFactory);
        this.type = type;
        this.associations = new AbstractAssociations<E>(DefaultJerseyEndpoint.this.type) {
            @Override
            protected void doBuildAssociations() {
//                association().name("pages").includeInRead()
//                        .attribute("section")
            }
        };
    }

}