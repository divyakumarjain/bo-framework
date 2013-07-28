package org.divy.common.bo.database.mock;

import org.divy.common.bo.command.db.AbstractDatabaseGetCommand;
import org.divy.common.bo.command.db.IDBCommandContext;

public class MockGetCommand extends AbstractDatabaseGetCommand<MockEntity, String> {

	public MockGetCommand(IDBCommandContext context) {
		super(MockEntity.class, context);
	}
}
