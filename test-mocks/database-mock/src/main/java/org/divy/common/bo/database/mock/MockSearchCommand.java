package org.divy.common.bo.database.mock;

import org.divy.common.bo.database.AbstractDatabaseSearchCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

import java.util.UUID;

/**
 *.a.jain@hp.com
 *
 */
public class MockSearchCommand extends
        AbstractDatabaseSearchCommand<MockEntity, UUID> {

    /**
     * @param context
     */
    public MockSearchCommand(EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }
}
