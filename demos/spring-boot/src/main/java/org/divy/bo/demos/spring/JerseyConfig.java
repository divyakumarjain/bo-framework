package org.divy.bo.demos.spring;

import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.stereotype.Component;

import org.divy.bo.demos.spring.greetings.GreetingsEndpoint;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(GreetingsEndpoint.class);
    }

}
