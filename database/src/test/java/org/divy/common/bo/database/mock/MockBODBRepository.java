/**
 * 
 */
package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.AbstractBODatabaseRepository;
import org.divy.common.bo.ICommandProvider;
import org.divy.common.bo.defaults.DefaultDBCommandProvider;

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
        ICommandProvider<MockEntity, UUID> commandProvider
            =  new DefaultDBCommandProvider<>("org.divy.task",MockEntity.class);

        setCommandProvider(commandProvider);
    }

}
