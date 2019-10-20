package org.divy.common.bo.database.command.impl;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.database.context.CommandContext;

import java.util.Optional;

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
    public Optional<E> get(I id)
    {
        return getReference(id);
    }

    protected abstract Optional<E> getReference(I id);
}
