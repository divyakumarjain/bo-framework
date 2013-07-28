package org.divy.common.bo.database.mock;

import org.divy.common.bo.command.db.AbstractDatabaseDeleteCommand;
import org.divy.common.bo.command.db.IDBCommandContext;

public class MockDeleteCommand extends AbstractDatabaseDeleteCommand<MockEntity, String> {

	public MockDeleteCommand(IDBCommandContext context) {
		super(MockEntity.class, context);
	}

	protected String getIdentity(MockEntity entity) {
		return "";
	}

}
