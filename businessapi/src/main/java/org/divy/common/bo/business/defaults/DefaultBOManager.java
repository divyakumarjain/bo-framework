package org.divy.common.bo.business.defaults;

import javax.inject.Inject;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.AbstractBOManager;


public class DefaultBOManager<ENTITY extends IBusinessObject<ID>, ID> extends AbstractBOManager<ENTITY, ID> {

    @Inject
    public DefaultBOManager(IBORepository<ENTITY, ID> repository) {
        super(repository);
    }
}
