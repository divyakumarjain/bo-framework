package org.divy.bo.demos.spring;

import org.divy.bo.demos.spring.cards.GreetingCardEndpoint;
import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.stereotype.Component;

import org.divy.bo.demos.spring.greetings.GreetingsEndpoint;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        initialize();
    }

    private final void initialize() {
        register(GreetingsEndpoint.class);
        register(GreetingCardEndpoint.class);
    }
}
