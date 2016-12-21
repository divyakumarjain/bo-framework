/**
 * 
 */
package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.database.AbstractBODatabaseRepository;
import org.divy.common.bo.database.TypeBaseDBCommandProvider;

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
        super(new TypeBaseDBCommandProvider<>("org.divy.task",
                MockGetCommand.class,
                MockUpdateCommand.class,
                MockDeleteCommand.class,
                MockCreateCommand.class,
                MockSearchCommand.class));
    }

}
