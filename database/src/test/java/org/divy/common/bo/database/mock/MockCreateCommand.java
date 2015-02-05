package org.divy.common.bo.database.mock;

import org.divy.common.bo.AbstractDatabaseCreateCommand;
import org.divy.common.bo.IDBCommandContext;

public class MockCreateCommand extends AbstractDatabaseCreateCommand<MockEntity> {

    public MockCreateCommand(
            IDBCommandContext context) {
        super(MockEntity.class, context);
    }

    public MockEntity create(MockEntity entity) {
        persist(entity);
        return entity;
    }
}
