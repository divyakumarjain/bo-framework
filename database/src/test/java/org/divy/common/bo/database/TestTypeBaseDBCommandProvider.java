package org.divy.common.bo.database;

import java.util.UUID;
import org.divy.common.bo.ICommandProvider;
import org.divy.common.bo.TypeBaseDBCommandProvider;
import org.divy.common.bo.IDBCommandContext;
import org.divy.common.bo.context.DatabaseContext;
import org.divy.common.bo.database.mock.MockCreateCommand;
import org.divy.common.bo.database.mock.MockDeleteCommand;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.database.mock.MockGetCommand;
import org.divy.common.bo.database.mock.MockUpdateCommand;
import org.junit.Before;
import org.junit.Test;

public class TestTypeBaseDBCommandProvider {

    ICommandProvider<MockEntity, UUID> commandProvider;
    IDBCommandContext commandContext;

    @Before
    public void before()
    {
        commandContext = new DatabaseContext("");

        TypeBaseDBCommandProvider<MockEntity, UUID> commandProvider = new TypeBaseDBCommandProvider<>("");

        commandProvider.setGetCommandType(MockGetCommand.class);
        commandProvider.setUpdateCommandType(MockUpdateCommand.class);
        commandProvider.setDeleteCommandType(MockDeleteCommand.class);
        commandProvider.setCreateCommandType(MockCreateCommand.class);

        commandProvider.setContext(commandContext);

        this.commandProvider = commandProvider;
    }

    @Test
    public void testGetGetCommand() {
        commandProvider.getGetCommand();
    }

    @Test
    public void testGetCreateCommand() {
        commandProvider.getCreateCommand();
    }

    @Test
    public void testGetDeleteCommand() {
        commandProvider.getDeleteCommand();
    }

    @Test
    public void testGetUpdateCommand() {
        commandProvider.getUpdateCommand();
    }

}
