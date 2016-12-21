package org.divy.bo.demos.spring.greetings;

import org.divy.common.bo.database.AbstractBODatabaseRepository;
import org.divy.common.bo.database.ICommandProvider;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

@Component
public class GreetingsRepository extends AbstractBODatabaseRepository<GreetingEntity, UUID> {
    @Inject
    public GreetingsRepository(ICommandProvider<GreetingEntity, UUID> commandProvider) {
        super(commandProvider);
    }
}
