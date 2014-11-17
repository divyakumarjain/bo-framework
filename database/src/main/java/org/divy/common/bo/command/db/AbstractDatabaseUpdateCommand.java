package org.divy.common.bo.command.db;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.IUpdateCommand;


public abstract class AbstractDatabaseUpdateCommand<ENTITY extends IBusinessObject<ID>, ID>
        extends AbstractDatabaseCommand<ENTITY, ID> implements
        IUpdateCommand<ENTITY, ID>
{
    protected AbstractDatabaseUpdateCommand(
            Class<? extends ENTITY> typeParameterClass,IDBCommandContext context)
    {
        super(typeParameterClass);
        this.setContext(context);
    }

    protected abstract void merge(ENTITY source, ENTITY target);

    @Override
    public ENTITY update(ENTITY entity)
    {

        return doUpdate(entity);
    }

    /**
     * @param entity
     * @return
     */
    private ENTITY doUpdate(ENTITY entity) {

        transactionBegin();

        ENTITY fromPersistance = getEntityManager().getReference(
                getEntityType(), entity.identity());

        merge(entity, fromPersistance);

        getEntityManager().merge(fromPersistance);

        transactionCommit();

        return fromPersistance;
    }
}
