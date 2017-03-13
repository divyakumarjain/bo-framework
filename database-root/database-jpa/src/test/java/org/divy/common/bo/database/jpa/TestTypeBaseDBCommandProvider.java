package org.divy.common.bo.database.jpa;

import org.divy.common.bo.command.*;
import org.divy.common.bo.database.jpa.context.DatabaseContext;
import org.divy.common.bo.database.jpa.mock.*;
import org.divy.common.bo.mapper.IBOMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class TestTypeBaseDBCommandProvider {

    ICommandProvider<MockEntity, UUID> commandProvider;
    EntityManagerCommandContext commandContext;

    IBOMapper<MockEntity, MockEntity> updateMapper;

    @Before
    public void before()
    {
        commandContext = new DatabaseContext("");
        updateMapper = mock(IBOMapper.class);

        TypeBaseDBCommandProvider<MockEntity, UUID> commandProvider
                = new TypeBaseDBCommandProvider<MockEntity, UUID>(commandContext
                    , MockEntity.class
                    ,(Class<? extends IGetCommand<MockEntity, UUID>>) (Class<?>) MockGetCommand.class
                    ,(Class<? extends IUpdateCommand<MockEntity, UUID>>) (Class<?>) MockUpdateCommand.class
                    ,(Class<? extends IDeleteCommand<MockEntity, UUID>>) (Class<?>)MockDeleteCommand.class
                    ,(Class<? extends ICreateCommand<MockEntity>>) (Class<?>) MockCreateCommand.class
                    ,(Class<? extends ISearchCommand<MockEntity>>) (Class<?>) MockSearchCommand.class
                    , updateMapper);

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
