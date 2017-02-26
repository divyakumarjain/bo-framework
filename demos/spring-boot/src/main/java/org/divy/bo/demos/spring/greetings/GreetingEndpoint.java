package org.divy.bo.demos.spring.greetings;

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

@Path("/greeting")
public class GreetingEndpoint extends AbstractHATEOASEndpoint<Greeting, DefaultRepresentation, UUID> {


    private final IBOManager<Greeting, UUID> manager;
    private final AbstractAssociations<Greeting> associations;
    private final HATEOASMapper<Greeting, DefaultRepresentation> greetingHATEOSMapper;

    @Autowired
    public GreetingEndpoint(@Qualifier("greetingManager") IBOManager<Greeting, UUID> greetingManager,
                            LinkBuilderFactoryImpl linkBuilderFactory,
                            HATEOASMapper<Greeting, DefaultRepresentation> greetingHATEOASMapper) {
        super(linkBuilderFactory);
        this.manager = greetingManager;
        this.greetingHATEOSMapper = greetingHATEOASMapper;
        this.associations = new AbstractAssociations<Greeting>(Greeting.class) {
            @Override
            protected void doBuildAssociations() {
//                association().name("pages").includeInRead()
//                        .attribute("section")
            }
        };
    }

    @Override
    public IBOManager<Greeting, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HATEOASMapper<Greeting, DefaultRepresentation> getRepresentationMapper() {
        return greetingHATEOSMapper;
    }

    @Override
    public AbstractAssociations<Greeting> getAssociations() {
        return associations;
    }
}