package org.divy.common.bo.business.defaults;

import org.divy.common.bo.BORepository;
import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.AbstractBOManager;
import org.divy.common.bo.business.validation.BOValidator;


public class DefaultBOManager<E extends BusinessObject<I>, I> extends AbstractBOManager<E, I> {

    public DefaultBOManager(BORepository<E, I> repository, BOValidator boValidator) {
        super(repository, boValidator);
    }
}
