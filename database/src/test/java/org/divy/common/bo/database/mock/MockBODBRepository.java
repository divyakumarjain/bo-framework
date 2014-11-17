/**
 * 
 */
package org.divy.common.bo.database.mock;

import org.divy.common.bo.AbstractBODatabaseRepository;
import org.divy.common.bo.ICommandProvider;
import org.divy.common.bo.command.db.defaults.DefaultDatabaseRepository;

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
        ICommandProvider<MockEntity, String> commandProvider
            = (ICommandProvider<MockEntity, String>) new DefaultDatabaseRepository<MockEntity, String>("org.divy.task",MockEntity.class);

        setCommandProvider(commandProvider);
    }

}
