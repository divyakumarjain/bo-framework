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
public class DefaultDatabaseCreateCommand<E extends AbstractBusinessObject>
        extends
        AbstractDatabaseCreateCommand<E> {

    /**
     * @param entityClass
     * @param context
     */
    public DefaultDatabaseCreateCommand(Class<E> entityClass,
                                        IDBCommandContext context) {
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
