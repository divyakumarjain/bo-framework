package org.divy.common.bo;

import org.divy.common.bo.command.IGetCommand;

public abstract class AbstractDatabaseGetCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I> implements IGetCommand<E, I>
{
    protected AbstractDatabaseGetCommand(
            Class<E> typeParameterClass, IDBCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    @Override
    public E get(I id)
    {
        return getEntityManager().find(getEntityType(), id);
    }
}
