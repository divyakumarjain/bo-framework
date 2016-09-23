/**
 * 
 */
package org.divy.common.bo.defaults;

import org.divy.common.bo.AbstractBusinessObject;
import org.divy.common.bo.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.IDBCommandContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.defaults.DefaultBOMapper;

/**
 * @author Divyakumar
 *
 */
public class DefaultDatabaseUpdateCommand<E extends AbstractBusinessObject>
        extends
        AbstractDatabaseUpdateCommand<E> {

    IBOMapper<E, E> mapper;

    /**
     * @param typeParameterClass
     * @param context
     */
    public DefaultDatabaseUpdateCommand(
            Class<E> typeParameterClass,
            IDBCommandContext context) {
        super(typeParameterClass, context);

        mapper = new DefaultBOMapper<>(typeParameterClass,typeParameterClass);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.divy.common.bo.command.AbstractDatabaseUpdateCommand#copyFields(org
     * .divy.common.bo.IBusinessObject, org.divy.common.bo.IBusinessObject)
     */
    @Override
    protected void merge(E source, E target) {
        if(source != target) {
            mapper.mapToBO(source, target);
        }
    }
}
