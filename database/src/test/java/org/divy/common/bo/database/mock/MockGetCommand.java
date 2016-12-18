package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.database.AbstractDatabaseGetCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

public class MockGetCommand extends AbstractDatabaseGetCommand<MockEntity, UUID> {

    public MockGetCommand(EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }
}
