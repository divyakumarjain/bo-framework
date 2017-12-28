package org.divy.common.bo.database.jpa;

import org.divy.common.bo.database.command.impl.AbstractDatabaseDeleteCommand;

import java.util.UUID;

public class MockDeleteCommand extends AbstractDatabaseDeleteCommand<MockEntity, UUID> {

    public MockDeleteCommand(EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }

    protected String getIdentity(MockEntity entity) {
        return "";
    }

    @Override
    protected MockEntity getReference(UUID id) {
        return null;
    }

    @Override
    protected void doDeleteEntity(MockEntity entity) {

    }
}
