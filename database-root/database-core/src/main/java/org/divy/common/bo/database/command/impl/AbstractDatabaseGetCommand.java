package org.divy.common.bo.database.command.impl;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.database.context.CommandContext;

public abstract class AbstractDatabaseGetCommand<E extends BusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I> implements org.divy.common.bo.database.command.GetCommand<E, I>
{
    protected AbstractDatabaseGetCommand(
            Class<E> typeParameterClass, CommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    @Override
    public E get(I id)
    {
        return getReference(id);
    }

    protected abstract E getReference(I id);
}
