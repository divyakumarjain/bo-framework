package org.divy.common.bo.command;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.context.CommandContext;
import org.divy.common.exception.NotFoundException;

public abstract class AbstractDatabaseDeleteCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I> implements
        IDeleteCommand<E, I>
{
    protected AbstractDatabaseDeleteCommand(
            Class<E> typeParameterClass, CommandContext context)
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
        boolean isDeleteSuccess = false;
        try {
            E entity = getReference(id);

            transactionBegin();
            if(entity == null) {
                throw new NotFoundException("Could not find " + getEntityType().getSimpleName() + " with id "+ id.toString());
            } else {
                doDeleteEntity(entity);
                isDeleteSuccess = true;
            }
            return entity;
        } finally {
            if(isDeleteSuccess) {
                transactionCommit();
            } else  {
                transactionRollback();
            }
        }
    }

    protected abstract E getReference(I id);

    protected abstract void doDeleteEntity(E entity);
}
