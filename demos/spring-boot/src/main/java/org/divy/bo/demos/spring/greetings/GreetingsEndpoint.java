package org.divy.bo.demos.spring.greetings;

import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.association.AbstractAssociations;
import org.divy.common.bo.endpoint.hypermedia.AbstractHATEOASEndpoint;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilderFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.Path;
import java.util.UUID;

@Path("/greetings")
public class GreetingsEndpoint extends AbstractHATEOASEndpoint<Greeting,GreetingRepresentation, UUID> {


    private final IBOManager<Greeting, UUID> manager;
    private AbstractAssociations<Greeting> associations;
    private GreetingHATEOASMapper greetingHATEOSMapper;

    @Autowired
    public GreetingsEndpoint(@Qualifier("greetingManager") IBOManager<Greeting, UUID> greetingManager,
                             LinkBuilderFactoryImpl linkBuilderFactory,
                             GreetingHATEOASMapper greetingHATEOASMapper) {
        super(linkBuilderFactory);
        this.manager = greetingManager;
        this.greetingHATEOSMapper = greetingHATEOASMapper;
        this.associations = new AbstractAssociations<Greeting>(Greeting.class) {
            @Override
            protected void doBuildAssociations() {
//                association().name("pages").includeInRead()
//                        .attribute("section")
            }
        };;
    }

    @Override
    public IBOManager<Greeting, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HATEOASMapper<Greeting, GreetingRepresentation> getRepresentationMapper() {
        return greetingHATEOSMapper;
    }

    @Override
    public AbstractAssociations<Greeting> getAssociations() {
        return associations;
    }
}