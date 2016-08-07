package org.divy.common.bo.database;

import java.util.UUID;
import org.divy.common.bo.ICommandProvider;
import org.divy.common.bo.TypeBaseDBCommandProvider;
import org.divy.common.bo.IDBCommandContext;
import org.divy.common.bo.context.DatabaseContext;
import org.divy.common.bo.database.mock.*;
import org.junit.Before;
import org.junit.Test;

public class TestTypeBaseDBCommandProvider {

    ICommandProvider<MockEntity, UUID> commandProvider;
    IDBCommandContext commandContext;

    @Before
    public void before()
    {
        commandContext = new DatabaseContext("");

        TypeBaseDBCommandProvider<MockEntity, UUID> commandProvider = new TypeBaseDBCommandProvider<>("",
                MockGetCommand.class,
                MockUpdateCommand.class,
                MockDeleteCommand.class,
                MockCreateCommand.class,
                MockSearchCommand.class);

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
