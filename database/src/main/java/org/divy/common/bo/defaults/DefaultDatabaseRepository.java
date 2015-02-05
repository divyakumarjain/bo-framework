package org.divy.common.bo.defaults;

import org.divy.common.bo.AbstractBODatabaseRepository;
import org.divy.common.bo.IBusinessObject;

public class DefaultDatabaseRepository<ENTITY extends IBusinessObject<ID>,ID> extends
        AbstractBODatabaseRepository<ENTITY, ID> {

    public DefaultDatabaseRepository(String persistentUnitName,
            Class<ENTITY> entityClass){
        setCommandProvider(new DefaultDBCommandProvider<ENTITY, ID>(persistentUnitName, entityClass));
    }

}
