package org.divy.common.bo.endpoint.json.test;

import com.google.inject.RuntimeTypeLiteral;
import com.google.inject.TypeLiteral;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.endpoint.test.AbstractBOEndpointUnitTest;
import org.divy.common.bo.endpoint.test.InMemoryBOManager;

import java.util.UUID;

public class MockBoEndpointUnitTest extends AbstractBOEndpointUnitTest<MockEntity, UUID> {

    public MockBoEndpointUnitTest() {
        super(new MockBoTestDataProvider());
    }

    @Override
    protected UUID getIdentifier(MockEntity mockEntity) {
        return mockEntity.identity();
    }

    @Override
    protected void extendedTestCreatedEntity(MockEntity mockEntity) {

    }

    @Override
    protected void extendedTestUpdatedEntity(MockEntity mockEntity) {

    }

    @Override
    protected Class<? extends AbstractBOEndpoint<MockEntity, UUID>> getEndPointClass() {
        return MockBoEndpoint.class;
    }

    @Override
    protected MockBoEndpoint createEndpointInstance() {
        return new MockBoEndpoint(new InMemoryBOManager<MockEntity, UUID>());
    }

    @Override
    protected TypeLiteral<AbstractBOEndpoint<MockEntity, UUID>> getEndpointTypeLiteral() {
        return new TypeLiteral<AbstractBOEndpoint<MockEntity, UUID>>(){};
    }

    @Override
    protected UUID toKey(String key) {
        return UUID.fromString(key);
    }

    protected Class<MockEntity> getEntityClass() {
        return MockEntity.class;
    }

    protected TypeLiteral<IBOManager<MockEntity, UUID>> getManagerTypeLiteral() {
        return RuntimeTypeLiteral.boManager(new TypeLiteral<MockEntity>() {
        }, new TypeLiteral<UUID>() {
        });
    }

    protected InMemoryBOManager<MockEntity, UUID> getMockManagerInstance() {
        return new InMemoryBOManager<>();
    }
}