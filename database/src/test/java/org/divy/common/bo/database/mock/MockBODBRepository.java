package org.divy.common.bo.database.mock;

import java.util.UUID;
import org.divy.common.bo.database.AbstractBODatabaseRepository;
import org.divy.common.bo.database.defaults.DefaultDBCommandProvider;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.MapperBuilder;

import static org.mockito.Mockito.*;

/**
 *
 *
 */
class MockBODBRepository extends
        AbstractBODatabaseRepository<MockEntity, UUID> {

    MockBODBRepository() {
        super(new DefaultDBCommandProvider<>("org.divy.task",MockEntity.class, mock(MapperBuilder.class)));
    }

}
