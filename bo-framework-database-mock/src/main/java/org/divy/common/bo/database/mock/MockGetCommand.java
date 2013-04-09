package org.divy.common.bo.database.mock;

import org.divy.common.bo.command.AbstractDatabaseGetCommand;
import org.divy.common.bo.command.IDBCommandContext;

public class MockGetCommand extends AbstractDatabaseGetCommand<MockEntity, String> {

	public MockGetCommand(IDBCommandContext context) {
		super(MockEntity.class, context);
	}
}
