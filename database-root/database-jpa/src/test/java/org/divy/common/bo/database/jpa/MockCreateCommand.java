package org.divy.common.bo.database.jpa;

import org.divy.common.bo.database.command.impl.AbstractDatabaseCreateCommand;

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
