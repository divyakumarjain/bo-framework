package org.divy.common.bo.database.jpa.defaults;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.database.command.impl.AbstractDatabaseDeleteCommand;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;

/**
 *
 *
 */
public class DefaultDatabaseDeleteCommand<E extends BusinessObject<I>, I>
        extends AbstractDatabaseDeleteCommand<E, I> {

    private final EntityManagerCommandContext context;

    public DefaultDatabaseDeleteCommand(Class<E> entityClass,
            EntityManagerCommandContext context) {
        super(entityClass, context);
        this.context = context;
    }

    @Override
    protected E getReference(Object identity) {
        return this.context.getEntityManager().find(getEntityType(), identity);
    }

    @Override
    protected void doDeleteEntity(E entity) {
        context.getEntityManager().remove(entity);
    }
}
