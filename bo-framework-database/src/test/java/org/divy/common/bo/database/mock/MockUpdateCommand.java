package org.divy.common.bo.database.mock;

import org.divy.common.bo.command.AbstractDatabaseUpdateCommand;
import org.divy.common.bo.command.IDBCommandContext;

public class MockUpdateCommand extends AbstractDatabaseUpdateCommand<MockEntity, String> {

	public MockUpdateCommand(
			IDBCommandContext context) {
		super(MockEntity.class, context);
	}

	@Override
	protected void copyFields(MockEntity source, MockEntity target) {
		
	}

}
