/**
 * 
 */
package org.divy.common.bo.database.mock;

import org.divy.common.bo.AbstractBODatabaseManager;
import org.divy.common.bo.TypeBaseDBCommandProvider;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class MockBODBManager extends
		AbstractBODatabaseManager<MockEntity, String> {

	/**
	 * 
	 */
	public MockBODBManager() {
		TypeBaseDBCommandProvider<MockEntity, String> commandProvider = new TypeBaseDBCommandProvider<MockEntity, String>(
				"org.divy.mock");

		commandProvider.setGetCommandType(MockGetCommand.class);
		commandProvider.setUpdateCommandType(MockUpdateCommand.class);
		commandProvider.setDeleteCommandType(MockDeleteCommand.class);
		commandProvider.setCreateCommandType(MockCreateCommand.class);
		commandProvider.setSearchCommandType(MockSearchCommand.class);

		setCommandProvider(commandProvider);
	}

}
