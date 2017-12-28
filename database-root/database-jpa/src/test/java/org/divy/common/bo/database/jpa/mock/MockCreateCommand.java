package org.divy.common.bo.database.jpa.mock;

import org.divy.common.bo.database.command.impl.AbstractDatabaseCreateCommand;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;

public class MockCreateCommand extends AbstractDatabaseCreateCommand<MockEntity> {

    public MockCreateCommand(
            EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }

    @Override
    protected void doPersist(MockEntity entity) {

    }

    public MockEntity create(MockEntity entity) {
        persist(entity);
        return entity;
    }
}
