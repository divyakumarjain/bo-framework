package org.divy.bo.demos.spring.cards;

import org.divy.common.bo.database.AbstractBODatabaseRepository;
import org.divy.common.bo.database.ICommandProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GreetingCardsRepository extends AbstractBODatabaseRepository<GreetingCardEntity, UUID> {
    @Autowired
    public GreetingCardsRepository(ICommandProvider<GreetingCardEntity, UUID> greetingCardsCommandProvider) {
        super(greetingCardsCommandProvider);
    }
}
