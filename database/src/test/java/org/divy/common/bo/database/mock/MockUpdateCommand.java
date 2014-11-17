package org.divy.common.bo.database.mock;

import org.divy.common.bo.command.db.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.command.db.IDBCommandContext;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.defaults.DefaultBOMapper;

public class MockUpdateCommand extends AbstractDatabaseUpdateCommand<MockEntity, String> {

    public MockUpdateCommand(
            IDBCommandContext context) {
        super(MockEntity.class, context);
        mapper = new DefaultBOMapper(MockEntity.class,MockEntity.class);
    }


    IBOMapper<MockEntity,MockEntity> mapper;


    @Override
    protected void merge(MockEntity source, MockEntity target) {
        if(source!=target)
            mapper.mapToBO(source, target);
    }
}
