package org.divy.common.bo.database;

import org.divy.common.bo.ICommandProvider;
import org.divy.common.bo.TypeBaseDBCommandProvider;
import org.divy.common.bo.command.IDBCommandContext;
import org.divy.common.bo.context.DatabaseContext;
import org.divy.common.bo.database.mock.MockCreateCommand;
import org.divy.common.bo.database.mock.MockDeleteCommand;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.database.mock.MockGetCommand;
import org.divy.common.bo.database.mock.MockUpdateCommand;
import org.junit.Before;
import org.junit.Test;

public class TestTypeBaseDBCommandProvider {
	
	ICommandProvider<MockEntity, String> commandProvider;
	IDBCommandContext commandContext;
	
	@Before
	public void before()
	{
		commandContext = new DatabaseContext("");
		
		TypeBaseDBCommandProvider<MockEntity, String> commandProvider = new TypeBaseDBCommandProvider<MockEntity, String>("");
		
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
