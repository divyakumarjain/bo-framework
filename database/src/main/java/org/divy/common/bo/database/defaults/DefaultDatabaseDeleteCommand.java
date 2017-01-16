/**
 * 
 */
package org.divy.common.bo.database.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.AbstractDatabaseDeleteCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

/**
 *
 *
 */
public class DefaultDatabaseDeleteCommand<E extends IBusinessObject<I>, I>
        extends
        AbstractDatabaseDeleteCommand<E, I> {

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseDeleteCommand(Class<E> entityClass,
            EntityManagerCommandContext context) {
        super(entityClass, context);
    }
}
