/**
 * 
 */
package org.divy.common.bo.database.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.AbstractDatabaseGetCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

/**
 *
 *
 */
public class DefaultDatabaseGetCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseGetCommand<E, I> {

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseGetCommand(
            Class<E> entityClass,
            EntityManagerCommandContext context) {
        super(entityClass, context);
    }

}
