package org.divy.common.bo;

import org.divy.common.bo.command.IDeleteCommand;

public abstract class AbstractDatabaseDeleteCommand<ENTITY extends IBusinessObject<ID>, ID>
        extends AbstractDatabaseCommand<ENTITY, ID> implements
        IDeleteCommand<ENTITY, ID>
{
    protected AbstractDatabaseDeleteCommand(
            Class<? extends ENTITY> typeParameterClass, IDBCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    @Override
    public ENTITY delete(ENTITY entity)
    {

        ID id = entity.identity();

        return deleteById(id);
    }


    @Override
    public ENTITY deleteById(ID id)
    {
        transactionBegin();

        ENTITY entity = find(id);

        if (entity != null) {
            getEntityManager().remove(entity);
        }

        transactionCommit();

        return entity;
    }

}
