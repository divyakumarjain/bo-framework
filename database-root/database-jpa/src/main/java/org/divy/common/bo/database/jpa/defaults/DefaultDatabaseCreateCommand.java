package org.divy.common.bo.database.jpa.defaults;

import org.divy.common.bo.database.command.impl.AbstractDatabaseCreateCommand;
import org.divy.common.bo.database.jpa.AbstractJPABusinessObject;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;

/**
 *
 *
 */
public class DefaultDatabaseCreateCommand<E extends AbstractJPABusinessObject>
        extends AbstractDatabaseCreateCommand<E> {

    private final EntityManagerCommandContext context;

    public DefaultDatabaseCreateCommand(Class<E> entityClass,
            EntityManagerCommandContext context) {
        super(entityClass, context);
        this.context = context;
    }

    @Override
    protected void doPersist(E entity) {
        this.context.getEntityManager().merge(entity);
    }

}
