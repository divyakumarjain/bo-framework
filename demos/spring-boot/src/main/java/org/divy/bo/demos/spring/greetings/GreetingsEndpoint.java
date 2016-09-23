package org.divy.bo.demos.spring.greetings;

import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.association.AbstractAssociations;
import org.divy.common.bo.endpoint.hypermedia.AbstractHATEOASEndpoint;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilderFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import java.util.UUID;

@Path("/greetings")
public class GreetingsEndpoint extends AbstractHATEOASEndpoint<GreetingEntity,GreetingRepresentation, UUID> {


    private final IBOManager<GreetingEntity, UUID> manager;
    private AbstractAssociations<GreetingEntity> associations;
    private GreetingHATEOASMapper greetingHATEOSMapper;

    @Autowired
    public GreetingsEndpoint(IBOManager<GreetingEntity, UUID> greetingsManager,
                             LinkBuilderFactoryImpl linkBuilderFactory,
                             GreetingHATEOASMapper greetingHATEOASMapper) {
        super(linkBuilderFactory);
        this.manager = greetingsManager;
        this.greetingHATEOSMapper = greetingHATEOASMapper;
        this.associations = new AbstractAssociations<GreetingEntity>(GreetingEntity.class) {
            @Override
            protected void doBuildAssociations() {
//                association().name("pages").includeInRead()
//                        .attribute("section")
            }
        };;
    }

    @Override
    public IBOManager<GreetingEntity, UUID> getManager() {
        return this.manager;
    }

    @Override
    public HATEOASMapper<GreetingEntity, GreetingRepresentation> getRepresentationMapper() {
        return greetingHATEOSMapper;
    }

    @Override
    public AbstractAssociations<GreetingEntity> getAssociations() {
        return associations;
    }
}