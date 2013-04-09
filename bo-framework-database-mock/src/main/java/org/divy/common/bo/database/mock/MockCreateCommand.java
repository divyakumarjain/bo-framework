package org.divy.common.bo.database.mock;

import org.divy.common.bo.command.AbstractDatabaseCreateCommand;
import org.divy.common.bo.command.IDBCommandContext;

public class MockCreateCommand extends AbstractDatabaseCreateCommand<MockEntity, String> {

	public MockCreateCommand(
			IDBCommandContext context) {
		super(MockEntity.class, context);
	}

	@Override
	public MockEntity create(MockEntity entity) {
		persist(entity);
		return entity;
	}
}
