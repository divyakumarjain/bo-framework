package org.divy.common.bo.database.jpa.mock;

import org.divy.common.bo.database.command.impl.AbstractDatabaseGetCommand;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;

import java.util.UUID;

public class MockGetCommand extends AbstractDatabaseGetCommand<MockEntity, UUID>
{

    public MockGetCommand(EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }

    @Override
    protected MockEntity getReference(UUID id) {
        return null;
    }
}
