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
public class GreetingCardEndpoint extends AbstractHATEOASEndpoint<GreetingCard, GreetingCardRepresentation, UUID> {

    private final IBOManager<GreetingCard, UUID> manager;
    private final AbstractAssociations<GreetingCard> associations;
    private final GreetingCardsHATEOASMapper greetingCardsHATEOASMapper;

    @Autowired
    public GreetingCardEndpoint(IBOManager<GreetingCard, UUID> greetingCardManager,
                                GreetingCardsHATEOASMapper greetingCardsHATEOASMapper,
                                LinkBuilderFactoryImpl linkBuilderFactory) {
        super(linkBuilderFactory);
        this.manager = greetingCardManager;
        this.greetingCardsHATEOASMapper = greetingCardsHATEOASMapper;
        this.associations = new AbstractAssociations<GreetingCard>(GreetingCard.class) {
            @Override
            protected void doBuildAssociations() {
                association().name("pages").includeInRead()
                        .attribute("pages");
            }
        };
    }

    @Override
    public IBOManager<GreetingCard, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HATEOASMapper<GreetingCard, GreetingCardRepresentation> getRepresentationMapper() {
        return greetingCardsHATEOASMapper;
    }

    @Override
    public AbstractAssociations<GreetingCard> getAssociations() {
        return associations;
    }
}