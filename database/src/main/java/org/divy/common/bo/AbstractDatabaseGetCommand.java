package org.divy.common.bo;

import org.divy.common.bo.command.IGetCommand;

public abstract class AbstractDatabaseGetCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I> implements IGetCommand<E, I>
{
    protected AbstractDatabaseGetCommand(
            Class<? extends E> typeParameterClass, IDBCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    @Override
    public E get(I id)
    {

        E entity = getEntityManager().find(getEntityType(), id);

        return entity;
    }
}
