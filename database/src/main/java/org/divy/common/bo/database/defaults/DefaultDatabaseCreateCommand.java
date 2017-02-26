package org.divy.common.bo.database.defaults;

import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.database.AbstractDatabaseCreateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

/**
 *
 *
 */
public class DefaultDatabaseCreateCommand<E extends AbstractBusinessObject>
        extends
        AbstractDatabaseCreateCommand<E> {

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseCreateCommand(Class<E> entityClass,
            EntityManagerCommandContext context) {
        super(entityClass, context);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.divy.common.bo.command.ICreateCommand#create(java.lang.Object)
     */
    @Override
    public E create(E entity) {

        persist(entity);
        return entity;
    }

}
