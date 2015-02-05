/**
 * 
 */
package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.AbstractBODatabaseRepository;
import org.divy.common.bo.TypeBaseDBCommandProvider;

/**
 * @author Divyakumar
 *
 */
public class MockBODBRepository extends
        AbstractBODatabaseRepository<MockEntity, UUID> {

    /**
     *
     */
    public MockBODBRepository() {
        TypeBaseDBCommandProvider<MockEntity, UUID> commandProvider = new TypeBaseDBCommandProvider<>(
                "org.divy.task");

        commandProvider.setGetCommandType(MockGetCommand.class);
        commandProvider.setUpdateCommandType(MockUpdateCommand.class);
        commandProvider.setDeleteCommandType(MockDeleteCommand.class);
        commandProvider.setCreateCommandType(MockCreateCommand.class);
        commandProvider.setSearchCommandType(MockSearchCommand.class);

        setCommandProvider(commandProvider);
    }

}
