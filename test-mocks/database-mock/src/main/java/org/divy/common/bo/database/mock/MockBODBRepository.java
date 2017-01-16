package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.database.AbstractBODatabaseRepository;
import org.divy.common.bo.database.TypeBaseDBCommandProvider;
import org.divy.common.bo.mapper.IBOMapper;

import static org.mockito.Mockito.*;

/**
 *
 *
 */
class MockBODBRepository extends
        AbstractBODatabaseRepository<MockEntity, UUID> {

    IBOMapper<MockEntity, MockEntity> updateMapper;

    MockBODBRepository() {
        super(new TypeBaseDBCommandProvider<>("org.divy.task"
                , MockEntity.class
                , MockGetCommand.class
                , MockUpdateCommand.class
                , MockDeleteCommand.class
                , MockCreateCommand.class
                , MockSearchCommand.class
                , mock(IBOMapper.class)));
    }

}
