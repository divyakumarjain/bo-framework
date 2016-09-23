package org.divy.common.bo;

import java.util.UUID;
import org.divy.common.bo.command.ICreateCommand;
import org.divy.common.bo.command.IDeleteCommand;
import org.divy.common.bo.command.IGetCommand;
import org.divy.common.bo.command.IUpdateCommand;
import org.divy.common.bo.context.DatabaseContext;
import org.divy.common.bo.database.mock.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

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
    public void testGetReadCommand() {
        IGetCommand<MockEntity, UUID> readCommand = commandProvider.getGetCommand();
        assertThat(readCommand,notNullValue());
    }

    @Test
    public void testGetCreateCommand() {
        ICreateCommand<MockEntity> createCommand = commandProvider.getCreateCommand();
        assertThat(createCommand,notNullValue());
    }

    @Test
    public void testGetDeleteCommand() {
        IDeleteCommand<MockEntity, UUID> deleteCommand = commandProvider.getDeleteCommand();
        assertThat(deleteCommand,notNullValue());
    }

    @Test
    public void testGetUpdateCommand() {
        IUpdateCommand<MockEntity, UUID> updateCommand = commandProvider.getUpdateCommand();
        assertThat(updateCommand,notNullValue());
    }

}
