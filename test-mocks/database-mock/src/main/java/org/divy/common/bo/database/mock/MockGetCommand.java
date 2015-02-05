package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.AbstractDatabaseGetCommand;
import org.divy.common.bo.IDBCommandContext;

public class MockGetCommand extends AbstractDatabaseGetCommand<MockEntity, UUID> {

    public MockGetCommand(IDBCommandContext context) {
        super(MockEntity.class, context);
    }
}
