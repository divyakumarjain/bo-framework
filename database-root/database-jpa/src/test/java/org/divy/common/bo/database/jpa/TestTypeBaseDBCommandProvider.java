package org.divy.common.bo.database.jpa;

import org.divy.common.bo.database.command.*;
import org.divy.common.bo.database.jpa.context.DatabaseContext;
import org.divy.common.bo.mapper.BOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import static org.mockito.Mockito.mock;

class TestTypeBaseDBCommandProvider {

    CommandProvider<MockEntity, UUID> commandProvider;
    EntityManagerCommandContext commandContext;

    BOMapper<MockEntity, MockEntity> updateMapper;

    @BeforeEach
    void before()
    {
        commandContext = new DatabaseContext("");
        updateMapper = mock(BOMapper.class);

        TypeBaseDBCommandProvider<MockEntity, UUID> commandProvider
                = new TypeBaseDBCommandProvider<MockEntity, UUID>(commandContext
                    , MockEntity.class
                    ,(Class<? extends GetCommand<MockEntity, UUID>>) MockGetCommand.class
                    ,(Class<? extends UpdateCommand<MockEntity, UUID>>) MockUpdateCommand.class
                    ,(Class<? extends DeleteCommand<MockEntity, UUID>>) MockDeleteCommand.class
                    ,(Class<? extends CreateCommand<MockEntity>>) MockCreateCommand.class
                    ,(Class<? extends SearchCommand<MockEntity>>) MockSearchCommand.class
                    , updateMapper);

        commandProvider.setContext(commandContext);

        this.commandProvider = commandProvider;
    }

    @Test
    void testGetReadCommand() {
        GetCommand<MockEntity, UUID> readCommand = commandProvider.getGetCommand();
        assertThat(readCommand,notNullValue());
    }

    @Test
    void testGetCreateCommand() {
        CreateCommand<MockEntity> createCommand = commandProvider.getCreateCommand();
        assertThat(createCommand,notNullValue());
    }

    @Test
    void testGetDeleteCommand() {
        DeleteCommand<MockEntity, UUID> deleteCommand = commandProvider.getDeleteCommand();
        assertThat(deleteCommand,notNullValue());
    }

    @Test
    void testGetUpdateCommand() {
        UpdateCommand<MockEntity, UUID> updateCommand = commandProvider.getUpdateCommand();
        assertThat(updateCommand,notNullValue());
    }

}
