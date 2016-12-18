/**
 * 
 */
package org.divy.common.bo.database.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.database.AbstractDatabaseSearchCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

/**
 * @author Divyakumar
 *
 */
public class DefaultDatabaseSearchCommand<E extends IBusinessObject<I>, I>
        extends
        AbstractDatabaseSearchCommand<E, I> {

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseSearchCommand(Class<E> entityClass,
            EntityManagerCommandContext context) {
        super(entityClass, context);
    }


}
