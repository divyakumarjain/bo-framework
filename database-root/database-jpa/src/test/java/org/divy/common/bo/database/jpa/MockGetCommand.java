package org.divy.common.bo.database.jpa;

import org.divy.common.bo.database.command.impl.AbstractDatabaseGetCommand;

import java.util.Optional;
import java.util.UUID;

public class MockGetCommand extends AbstractDatabaseGetCommand<MockEntity, UUID>
{

    public MockGetCommand(EntityManagerCommandContext context) {
        super(MockEntity.class, context);
    }

    @Override
    protected Optional<MockEntity> getReference(UUID id) {
        return null;
    }
}
