/**
 * 
 */
package org.divy.common.bo.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.AbstractDatabaseSearchCommand;
import org.divy.common.bo.IDBCommandContext;

/**
 * @author Divyakumar
 *
 */
public class DefaultDatabaseSearchCommand<ENTITY extends IBusinessObject<ID>, ID>
        extends
        AbstractDatabaseSearchCommand<ENTITY, ID> {

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseSearchCommand(Class<? extends ENTITY> entityClass,
            IDBCommandContext context) {
        super(entityClass, context);
    }


}
