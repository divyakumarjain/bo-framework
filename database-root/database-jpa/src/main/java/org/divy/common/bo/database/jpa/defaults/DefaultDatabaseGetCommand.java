package org.divy.common.bo.database.jpa.defaults;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.database.command.impl.AbstractDatabaseGetCommand;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;

import java.util.Optional;

/**
 *
 *
 */
public class DefaultDatabaseGetCommand<E extends BusinessObject<I>, I>
        extends AbstractDatabaseGetCommand<E,I>
{

    private final EntityManagerCommandContext context;

    /**
     * @param entityClass the entity class
     * @param context the context
     */
    public DefaultDatabaseGetCommand(
            Class<E> entityClass,
            EntityManagerCommandContext context) {
        super(entityClass, context);
        this.context = context;
    }

    @Override
    protected Optional<E> getReference( I identity) {
        return Optional.ofNullable(this.context.getEntityManager().find(getEntityType(), identity));
    }
}
