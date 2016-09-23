/**
 * 
 */
package org.divy.common.bo.defaults;

import org.divy.common.bo.AbstractDatabaseSearchCommand;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IDBCommandContext;

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
                                        IDBCommandContext context) {
        super(entityClass, context);
    }


}
