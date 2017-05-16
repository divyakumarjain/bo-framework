package org.divy.common.bo.business.defaults;

import org.divy.common.bo.BORepository;
import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.AbstractBOManager;



public class DefaultBOManager<E extends BusinessObject<I>, I> extends AbstractBOManager<E, I> {

    public DefaultBOManager(BORepository<E, I> repository) {
        super(repository);
    }
}
