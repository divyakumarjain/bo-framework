package org.divy.common.bo.database.jpa.defaults;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.database.AbstractBODatabaseRepository;
import org.divy.common.bo.database.command.CommandProvider;

public class DefaultDatabaseRepository<E extends BusinessObject<I>, I> extends
        AbstractBODatabaseRepository<E, I> {
    public DefaultDatabaseRepository(CommandProvider<E, I> commandProvider) {
        super(commandProvider);
    }
}
