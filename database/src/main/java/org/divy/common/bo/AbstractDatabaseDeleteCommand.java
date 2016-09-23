package org.divy.common.bo;

import org.divy.common.bo.command.IDeleteCommand;

public abstract class AbstractDatabaseDeleteCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I> implements
        IDeleteCommand<E, I>
{
    protected AbstractDatabaseDeleteCommand(
            Class<E> typeParameterClass, IDBCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    @Override
    public E delete(E entity)
    {

        I id = entity.identity();

        return deleteById(id);
    }


    @Override
    public E deleteById(I id)
    {
        transactionBegin();

        E entity = find(id);

        if (entity != null) {
            getEntityManager().remove(entity);
        }

        transactionCommit();

        return entity;
    }

}
