/**
 * 
 */
package org.divy.common.bo.command.db.defaults;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.command.db.AbstractDatabaseDeleteCommand;
import org.divy.common.bo.command.db.IDBCommandContext;

/**
 * @author Divyakumar
 *
 */
public class DefaultDatabaseDeleteCommand<ENTITY extends IBusinessObject<ID>, ID>
        extends
        AbstractDatabaseDeleteCommand<ENTITY, ID> {

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseDeleteCommand(Class<? extends ENTITY> entityClass,
            IDBCommandContext context) {
        super(entityClass, context);
    }
}
