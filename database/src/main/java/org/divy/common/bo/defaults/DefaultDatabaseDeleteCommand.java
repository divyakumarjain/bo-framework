/**
 * 
 */
package org.divy.common.bo.defaults;

import org.divy.common.bo.AbstractDatabaseDeleteCommand;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IDBCommandContext;

/**
 * @author Divyakumar
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
                                        IDBCommandContext context) {
        super(entityClass, context);
    }
}
