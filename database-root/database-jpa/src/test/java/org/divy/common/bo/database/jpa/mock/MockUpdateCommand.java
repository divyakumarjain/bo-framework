package org.divy.common.bo.database.jpa.mock;

import org.divy.common.bo.command.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.database.jpa.EntityManagerCommandContext;
import org.divy.common.bo.mapper.IBOMapper;

import java.util.UUID;

public class MockUpdateCommand extends AbstractDatabaseUpdateCommand<MockEntity> {

    public MockUpdateCommand(EntityManagerCommandContext context, IBOMapper<MockEntity, MockEntity> updateMapper) {
        super(MockEntity.class, context, updateMapper);
    }

    @Override
    protected MockEntity getReference(UUID id) {
        return null;
    }

    @Override
    protected void doPersist(MockEntity entity) {

    }
}
