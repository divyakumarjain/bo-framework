package org.divy.bo.demos.spring.cards;

import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.association.AbstractAssociations;
import org.divy.common.bo.endpoint.hypermedia.AbstractHATEOASEndpoint;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilderFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import java.util.UUID;


@Path("/GreetingCards")
public class GreetingCardEndpoint extends AbstractHATEOASEndpoint<GreetingCardEntity, GreetingCardRepresentation, UUID> {

    private final IBOManager<GreetingCardEntity, UUID> manager;
    private final AbstractAssociations<GreetingCardEntity> associations;
    private final GreetingCardsHATEOASMapper greetingCardsHATEOASMapper;

    @Autowired
    public GreetingCardEndpoint(IBOManager<GreetingCardEntity, UUID> greetingManager,
                                GreetingCardsHATEOASMapper greetingCardsHATEOASMapper,
                                LinkBuilderFactoryImpl linkBuilderFactory) {
        super(linkBuilderFactory);
        this.manager = greetingManager;
        this.greetingCardsHATEOASMapper = greetingCardsHATEOASMapper;
        this.associations = new AbstractAssociations<GreetingCardEntity>(GreetingCardEntity.class) {
            @Override
            protected void doBuildAssociations() {
                association().name("pages").includeInRead()
                        .attribute("pages");
            }
        };
    }

    @Override
    public IBOManager<GreetingCardEntity, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HATEOASMapper<GreetingCardEntity, GreetingCardRepresentation> getRepresentationMapper() {
        return greetingCardsHATEOASMapper;
    }

    @Override
    public AbstractAssociations<GreetingCardEntity> getAssociations() {
        return associations;
    }
}