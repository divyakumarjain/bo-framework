package org.divy.common.bo.database.command.impl;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.database.context.CommandContext;
import org.divy.common.exception.NotFoundException;

public abstract class AbstractDatabaseDeleteCommand<E extends BusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I> implements
        org.divy.common.bo.database.command.DeleteCommand<E, I>
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

        var id = entity.identity();

        return deleteById(id);
    }


    @Override
    public E deleteById(I id)
    {
        var isDeleteSuccess = false;
        try {
            var entity = getReference(id);

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
