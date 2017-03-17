package org.divy.common.bo.database.jpa.defaults;

import org.divy.common.bo.command.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.database.jpa.AbstractJPABusinessObject;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;
import org.divy.common.bo.mapper.IBOMapper;

import java.util.UUID;

/**
 *
 *
 */
public class DefaultDatabaseUpdateCommand<E extends AbstractJPABusinessObject>
        extends
        AbstractDatabaseUpdateCommand<E> {

    private final EntityManagerCommandContext context;

    /**
     * @param entityType The Entity type
     * @param context The Context for Database operation
     * @param updateMapper mapper which will merge object from to be updated with object from database
     */
    public DefaultDatabaseUpdateCommand( Class<E> entityType
            , EntityManagerCommandContext context
            , IBOMapper<E,E> updateMapper) {
        super(entityType, context, updateMapper);
        this.context = context;
    }

    @Override
    protected void doPersist(E entity) {
        context.getEntityManager().merge(entity);
    }

    @Override
    protected E getReference(UUID id) {
        return context.getEntityManager().find(getEntityType(), id);
    }
}
