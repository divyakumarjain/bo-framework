package org.divy.common.bo.database.mock;

import org.divy.common.bo.database.AbstractDatabaseCreateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;

public class MockCreateCommand extends AbstractDatabaseCreateCommand<MockEntity> {

    public MockCreateCommand(
            EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }

    public MockEntity create(MockEntity entity) {
        persist(entity);
        return entity;
    }
}
