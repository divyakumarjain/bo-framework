package org.divy.common.bo.database.jpa.defaults;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.command.AbstractDatabaseGetCommand;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;

/**
 *
 *
 */
public class DefaultDatabaseGetCommand<E extends BusinessObject<I>, I>
        extends AbstractDatabaseGetCommand<E, I> {

    private final EntityManagerCommandContext context;

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseGetCommand(
            Class<E> entityClass,
            EntityManagerCommandContext context) {
        super(entityClass, context);
        this.context = context;
    }

    @Override
    protected E getReference(Object identity) {
        return this.context.getEntityManager().find(getEntityType(), identity);
    }
}
