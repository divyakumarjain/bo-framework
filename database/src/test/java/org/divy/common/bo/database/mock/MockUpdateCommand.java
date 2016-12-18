package org.divy.common.bo.database.mock;

import org.divy.common.bo.database.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.database.context.EntityManagerCommandContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.defaults.DefaultBOMapper;

public class MockUpdateCommand extends AbstractDatabaseUpdateCommand<MockEntity> {

    public MockUpdateCommand(
            EntityManagerCommandContext context) {
        super(MockEntity.class, context);
        mapper = new DefaultBOMapper<MockEntity, MockEntity>(MockEntity.class,MockEntity.class);
    }


    IBOMapper<MockEntity,MockEntity> mapper;


    @Override
    protected void merge(MockEntity source, MockEntity target) {
        if(source!=target) {
            mapper.mapToBO(source, target);
        }
    }
}
