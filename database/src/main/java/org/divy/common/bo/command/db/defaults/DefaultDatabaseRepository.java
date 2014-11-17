package org.divy.common.bo.command.db.defaults;

import org.divy.common.bo.AbstractBODatabaseRepository;
import org.divy.common.bo.IBusinessObject;

public class DefaultDatabaseRepository<ENTITY extends IBusinessObject<ID>,ID> extends
        AbstractBODatabaseRepository<ENTITY, ID> {

    public DefaultDatabaseRepository(String persistantUnitName,
            Class<ENTITY> entityClass){
        setCommandProvider(new DefaultDBCommandProvider<ENTITY, ID>(persistantUnitName, entityClass));
    }

}
