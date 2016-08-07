package org.divy.bo.demos.spring.greetings;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.business.AbstractBOManager;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

@Component
public class GreetingsManager extends AbstractBOManager<GreetingEntity, UUID>{

    @Inject
    public GreetingsManager(IBORepository<GreetingEntity, UUID> repository) {
        super(repository);
    }
}
