package org.divy.common.bo.database.mock;

import org.divy.common.bo.database.AbstractBODatabaseRepository;
import org.divy.common.bo.database.defaults.DefaultDBCommandProvider;
import org.divy.common.bo.mapper.IBOMapper;

import java.util.UUID;

import static org.mockito.Mockito.mock;

class MockBODBRepository extends
        AbstractBODatabaseRepository<MockEntity, UUID> {

    MockBODBRepository() {
        super(new DefaultDBCommandProvider<>("org.divy.task",MockEntity.class, mock(IBOMapper.class)));
    }

}
