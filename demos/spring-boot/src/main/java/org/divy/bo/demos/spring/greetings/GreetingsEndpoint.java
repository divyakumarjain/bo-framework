package org.divy.bo.demos.spring.greetings;

import org.divy.bo.demos.spring.greetings.GreetingEntity;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.rest.LinkBuilderFactoryImpl;

import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.UUID;

@Path("/greetings")
public class GreetingsEndpoint extends AbstractBOEndpoint<GreetingEntity, UUID> {
    @Inject
    public GreetingsEndpoint(IBOManager<GreetingEntity, UUID> manager, LinkBuilderFactoryImpl linkBuilderFactory) {
        super(manager, linkBuilderFactory);
    }
}