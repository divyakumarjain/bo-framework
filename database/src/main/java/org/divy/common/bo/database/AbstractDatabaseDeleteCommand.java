package org.divy.common.bo.database;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.IDeleteCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.exception.impl.NotFoundException;

public abstract class AbstractDatabaseDeleteCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseCommand<E, I> implements
        IDeleteCommand<E, I>
{
    protected AbstractDatabaseDeleteCommand(
            Class<E> typeParameterClass, EntityManagerCommandContext context)
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
            E entity = find(id);

            transactionBegin();
            if(entity == null) {
                throw new NotFoundException("Could nt find " + getEntityType().getSimpleName() + " with id "+ id.toString());
            } else {
                getEntityManager().remove(entity);
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

}
