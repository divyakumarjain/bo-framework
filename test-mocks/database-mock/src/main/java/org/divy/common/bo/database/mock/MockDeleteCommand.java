package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.database.AbstractDatabaseDeleteCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

public class MockDeleteCommand extends AbstractDatabaseDeleteCommand<MockEntity, UUID> {

    public MockDeleteCommand(EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }

    protected String getIdentity(MockEntity entity) {
        return entity.getUuid().toString();
    }

}
