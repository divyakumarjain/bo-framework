package org.divy.common.bo.database.jpa;

import org.divy.common.bo.database.command.impl.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.mapper.BOMapper;

import java.util.UUID;

public class MockUpdateCommand extends AbstractDatabaseUpdateCommand<MockEntity>
{

    public MockUpdateCommand(EntityManagerCommandContext context, BOMapper<MockEntity, MockEntity> updateMapper) {
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
