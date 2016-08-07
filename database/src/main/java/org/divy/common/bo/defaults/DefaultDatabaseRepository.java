package org.divy.common.bo.defaults;

import org.divy.common.bo.AbstractBODatabaseRepository;
import org.divy.common.bo.IBusinessObject;

public class DefaultDatabaseRepository<E extends IBusinessObject<I>, I> extends
        AbstractBODatabaseRepository<E, I> {

    public DefaultDatabaseRepository(String persistentUnitName,
                                     Class<E> entityClass) {
        super(new DefaultDBCommandProvider<E, I>(persistentUnitName, entityClass));
    }

}
