package org.divy.common.bo.command;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.context.CommandContext;

public abstract class AbstractDatabaseGetCommand<E extends BusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I> implements GetCommand<E, I>
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
