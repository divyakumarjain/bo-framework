package org.divy.bo.demos.spring;

import org.divy.bo.demos.spring.cards.GreetingCardEndpoint;
import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.stereotype.Component;

import org.divy.bo.demos.spring.greetings.GreetingEndpoint;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        initialize();
    }

    private void initialize() {
        register(GreetingEndpoint.class);
        register(GreetingCardEndpoint.class);
    }
}
