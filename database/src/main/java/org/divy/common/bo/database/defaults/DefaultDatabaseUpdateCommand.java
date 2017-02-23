package org.divy.common.bo.database.defaults;

import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.database.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.mapper.IBOMapper;

/**
 *
 *
 */
public class DefaultDatabaseUpdateCommand<E extends AbstractBusinessObject>
        extends
        AbstractDatabaseUpdateCommand<E> {

    /**
     * @param entityType The Entity type
     * @param context The Context for Database operation
     * @param updateMapper mapper which will merge object from to be updated with object from database
     */
    public DefaultDatabaseUpdateCommand( Class<E> entityType
            , EntityManagerCommandContext context
            , IBOMapper<E,E> updateMapper) {
        super(entityType, context, updateMapper);
    }
}
