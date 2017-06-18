package org.divy.common.bo.database.jpa;

import org.divy.common.bo.command.*;
import org.divy.common.bo.database.jpa.context.DatabaseContext;
import org.divy.common.bo.database.jpa.mock.*;
import org.divy.common.bo.mapper.BOMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class TestTypeBaseDBCommandProvider {

    CommandProvider<MockEntity, UUID> commandProvider;
    EntityManagerCommandContext commandContext;

    BOMapper<MockEntity, MockEntity> updateMapper;

    @Before
    public void before()
    {
        commandContext = new DatabaseContext("");
        updateMapper = mock(BOMapper.class);

        TypeBaseDBCommandProvider<MockEntity, UUID> commandProvider
                = new TypeBaseDBCommandProvider<MockEntity, UUID>(commandContext
                    , MockEntity.class
                    ,(Class<? extends GetCommand<MockEntity, UUID>>) (Class<?>) MockGetCommand.class
                    ,(Class<? extends UpdateCommand<MockEntity, UUID>>) (Class<?>) MockUpdateCommand.class
                    ,(Class<? extends DeleteCommand<MockEntity, UUID>>) (Class<?>)MockDeleteCommand.class
                    ,(Class<? extends CreateCommand<MockEntity>>) (Class<?>) MockCreateCommand.class
                    ,(Class<? extends SearchCommand<MockEntity>>) (Class<?>) MockSearchCommand.class
                    , updateMapper);

        commandProvider.setContext(commandContext);

        this.commandProvider = commandProvider;
    }

    @Test
    public void testGetReadCommand() {
        GetCommand<MockEntity, UUID> readCommand = commandProvider.getGetCommand();
        assertThat(readCommand,notNullValue());
    }

    @Test
    public void testGetCreateCommand() {
        CreateCommand<MockEntity> createCommand = commandProvider.getCreateCommand();
        assertThat(createCommand,notNullValue());
    }

    @Test
    public void testGetDeleteCommand() {
        DeleteCommand<MockEntity, UUID> deleteCommand = commandProvider.getDeleteCommand();
        assertThat(deleteCommand,notNullValue());
    }

    @Test
    public void testGetUpdateCommand() {
        UpdateCommand<MockEntity, UUID> updateCommand = commandProvider.getUpdateCommand();
        assertThat(updateCommand,notNullValue());
    }

}
