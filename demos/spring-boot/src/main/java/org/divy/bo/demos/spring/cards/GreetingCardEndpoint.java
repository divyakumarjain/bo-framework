package org.divy.bo.demos.spring.cards;

import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.association.AbstractAssociations;
import org.divy.common.bo.endpoint.hypermedia.AbstractHATEOASEndpoint;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilderFactoryImpl;
import org.divy.common.rest.impl.DefaultRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.Path;
import java.util.UUID;


@Path("/GreetingCard")
public class GreetingCardEndpoint extends AbstractHATEOASEndpoint<GreetingCard, DefaultRepresentation, UUID> {

    private final IBOManager<GreetingCard, UUID> manager;
    private final AbstractAssociations<GreetingCard> associations;
    private final HATEOASMapper<GreetingCard, DefaultRepresentation> greetingCardHATEOASMapper;

    @Autowired
    public GreetingCardEndpoint(@Qualifier("greetingCardManager") IBOManager<GreetingCard, UUID> greetingCardManager,
                                HATEOASMapper<GreetingCard, DefaultRepresentation> greetingCardHATEOASMapper,
                                LinkBuilderFactoryImpl linkBuilderFactory) {
        super(linkBuilderFactory);
        this.manager = greetingCardManager;
        this.greetingCardHATEOASMapper = greetingCardHATEOASMapper;
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
    public HATEOASMapper<GreetingCard, DefaultRepresentation> getRepresentationMapper() {
        return greetingCardHATEOASMapper;
    }

    @Override
    public AbstractAssociations<GreetingCard> getAssociations() {
        return associations;
    }
}