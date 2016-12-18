/**
 * 
 */
package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.database.AbstractBODatabaseRepository;
import org.divy.common.bo.database.ICommandProvider;
import org.divy.common.bo.database.defaults.DefaultDBCommandProvider;

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
        super(new DefaultDBCommandProvider<>("org.divy.task",MockEntity.class));
    }

}
