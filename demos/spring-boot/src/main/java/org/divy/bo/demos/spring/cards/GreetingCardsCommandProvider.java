package org.divy.bo.demos.spring.cards;

import org.divy.common.bo.database.defaults.DefaultDBCommandProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GreetingCardsCommandProvider extends DefaultDBCommandProvider<GreetingCardEntity, UUID> {
    public GreetingCardsCommandProvider() {
        super("org.divy.greeting", GreetingCardEntity.class);
    }
}
