/**
 * 
 */
package org.divy.common.bo.database.mock;

import org.divy.common.bo.AbstractBODatabaseRepository;
import org.divy.common.bo.TypeBaseDBCommandProvider;

/**
 * @author Divyakumar
 *
 */
public class MockBODBRepository extends
        AbstractBODatabaseRepository<MockEntity, String> {

    /**
     *
     */
    public MockBODBRepository() {
        TypeBaseDBCommandProvider<MockEntity, String> commandProvider = new TypeBaseDBCommandProvider<MockEntity, String>(
                "org.divy.task");

        commandProvider.setGetCommandType(MockGetCommand.class);
        commandProvider.setUpdateCommandType(MockUpdateCommand.class);
        commandProvider.setDeleteCommandType(MockDeleteCommand.class);
        commandProvider.setCreateCommandType(MockCreateCommand.class);
        commandProvider.setSearchCommandType(MockSearchCommand.class);

        setCommandProvider(commandProvider);
    }

}
