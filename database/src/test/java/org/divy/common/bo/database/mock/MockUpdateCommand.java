package org.divy.common.bo.database.mock;

import org.divy.common.bo.database.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.mapper.IBOMapper;

public class MockUpdateCommand extends AbstractDatabaseUpdateCommand<MockEntity> {

    public MockUpdateCommand(EntityManagerCommandContext context, IBOMapper<MockEntity, MockEntity> updateMapper) {
        super(MockEntity.class, context, updateMapper);
    }
}
