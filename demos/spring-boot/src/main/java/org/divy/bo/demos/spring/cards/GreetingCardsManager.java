package org.divy.bo.demos.spring.cards;
/**
 * Created by divyjain on 9/24/2016.
 */

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.business.AbstractBOManager;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

@Component
public class GreetingCardsManager extends AbstractBOManager<GreetingCardEntity, UUID> {

    @Inject
    public GreetingCardsManager(IBORepository<GreetingCardEntity, UUID> repository) {
        super(repository);
    }
}