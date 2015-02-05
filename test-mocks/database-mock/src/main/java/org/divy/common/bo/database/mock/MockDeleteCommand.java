package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.AbstractDatabaseDeleteCommand;
import org.divy.common.bo.IDBCommandContext;

public class MockDeleteCommand extends AbstractDatabaseDeleteCommand<MockEntity, UUID> {

    public MockDeleteCommand(IDBCommandContext context) {
        super(MockEntity.class, context);
    }

    protected String getIdentity(MockEntity entity) {
        return "";
    }

}
