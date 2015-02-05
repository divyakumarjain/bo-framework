/**
 * 
 */
package org.divy.common.bo.defaults;

import org.divy.common.bo.AbstractBusinessObject;
import org.divy.common.bo.AbstractDatabaseCreateCommand;
import org.divy.common.bo.IDBCommandContext;

/**
 * @author Divyakumar
 *
 */
public class DefaultDatabaseCreateCommand<ENTITY extends AbstractBusinessObject>
        extends
        AbstractDatabaseCreateCommand<ENTITY> {

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseCreateCommand(Class<? extends ENTITY> entityClass,
            IDBCommandContext context) {
        super(entityClass, context);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.divy.common.bo.command.ICreateCommand#create(java.lang.Object)
     */
    @Override
    public ENTITY create(ENTITY entity) {

        persist(entity);
        return entity;
    }

}
