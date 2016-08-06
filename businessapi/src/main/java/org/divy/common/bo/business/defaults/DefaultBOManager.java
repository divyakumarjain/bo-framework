package org.divy.common.bo.business.defaults;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.AbstractBOManager;

import javax.inject.Inject;


public class DefaultBOManager<E extends IBusinessObject<I>, I> extends AbstractBOManager<E, I> {

    @Inject
    public DefaultBOManager(IBORepository<E, I> repository) {
        super(repository);
    }
}
