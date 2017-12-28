package org.divy.common.bo.database.command.impl;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.database.context.CommandContext;

public abstract class AbstractDatabaseCommand<E extends BusinessObject<I>, I>
{

    private final Class<E> entityType;
    private CommandContext context;


    protected AbstractDatabaseCommand(Class<E> typeParameterClass) {
        this.entityType = typeParameterClass;
    }

    /**
     * @return the entityType
     */
    protected Class<E> getEntityType() {
        return entityType;
    }

    final void setContext(CommandContext context)
    {
        this.context = context;
    }


    void transactionCommit()
    {
        context.commit();
    }

    void transactionBegin()
    {
        context.begin();
    }

    void transactionRollback() {
        context.rollBack();
    }
}
