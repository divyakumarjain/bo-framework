/**
 * 
 */
package org.divy.common.bo.defaults;

import org.divy.common.bo.AbstractDatabaseGetCommand;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IDBCommandContext;

/**
 * @author Divyakumar
 *
 */
public class DefaultDatabaseGetCommand<E extends IBusinessObject<I>, I>
        extends AbstractDatabaseGetCommand<E, I> {

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseGetCommand(
            Class<? extends E> entityClass,
            IDBCommandContext context) {
        super(entityClass, context);
    }

}
