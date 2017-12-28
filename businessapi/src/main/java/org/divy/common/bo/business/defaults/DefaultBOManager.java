package org.divy.common.bo.business.defaults;

import org.divy.common.bo.repository.BORepository;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.AbstractBOManager;
import org.divy.common.bo.validation.BOValidator;


public class DefaultBOManager<E extends BusinessObject<I>, I> extends AbstractBOManager<E, I> {

    public DefaultBOManager(BORepository<E, I> repository, BOValidator<E,I> boValidator) {
        super(repository, boValidator);
    }
}
