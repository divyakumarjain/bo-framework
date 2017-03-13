package org.divy.common.bo.database.jpa.defaults;

import org.divy.common.bo.AbstractBODatabaseRepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.ICommandProvider;

public class DefaultDatabaseRepository<E extends IBusinessObject<I>, I> extends
        AbstractBODatabaseRepository<E, I> {
    public DefaultDatabaseRepository(ICommandProvider<E, I> commandProvider) {
        super(commandProvider);
    }
}
