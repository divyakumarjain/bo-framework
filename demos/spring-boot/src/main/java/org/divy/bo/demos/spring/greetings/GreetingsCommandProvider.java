package org.divy.bo.demos.spring.greetings;

import org.divy.common.bo.defaults.DefaultDBCommandProvider;
import org.springframework.stereotype.Component;

@Component
public class GreetingsCommandProvider extends DefaultDBCommandProvider {
    public GreetingsCommandProvider() {
        super("org.divy.greeting", GreetingEntity.class);
    }
}
