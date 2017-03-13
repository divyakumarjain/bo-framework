package org.divy.common.bo.endpoint.json.test;

import com.google.inject.RuntimeTypeLiteral;
import com.google.inject.TypeLiteral;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.endpoint.test.AbstractBOEndpointUnitTest;
import org.divy.common.bo.endpoint.test.InMemoryBOManager;
import org.divy.common.bo.endpoint.test.MockLinkBuilderFactory;

import java.util.UUID;

public class MockBoEndpointUnitTest extends AbstractBOEndpointUnitTest<MockBoEndpoint.MockEntity, UUID> {

    public MockBoEndpointUnitTest() {
        super(new MockBoTestDataProvider());
    }

    @Override
    protected UUID getIdentifier(MockBoEndpoint.MockEntity mockEntity) {
        return mockEntity.identity();
    }

    @Override
    protected void extendedTestCreatedEntity(MockBoEndpoint.MockEntity mockEntity) {

    }

    @Override
    protected void extendedTestUpdatedEntity(MockBoEndpoint.MockEntity mockEntity) {

    }

    @Override
    protected Class<? extends AbstractBOEndpoint<MockBoEndpoint.MockEntity, UUID>> getEndPointClass() {
        return (Class<? extends AbstractBOEndpoint<MockBoEndpoint.MockEntity, UUID>>) MockBoEndpoint.class;
    }

    @Override
    protected AbstractBOEndpoint createEndpointInstance() {
        return new MockBoEndpoint(new InMemoryBOManager<>(), new MockLinkBuilderFactory("http", "localhost:8080", ""));
    }

    @Override
    protected TypeLiteral<AbstractBOEndpoint<MockBoEndpoint.MockEntity, UUID>> getEndpointTypeLiteral() {
        return new TypeLiteral<AbstractBOEndpoint<MockBoEndpoint.MockEntity, UUID>>(){};
    }

    @Override
    protected UUID toKey(String key) {
        return UUID.fromString(key);
    }

    protected Class<MockBoEndpoint.MockEntity> getEntityClass() {
        return MockBoEndpoint.MockEntity.class;
    }

    protected TypeLiteral<IBOManager<MockBoEndpoint.MockEntity, UUID>> getManagerTypeLiteral() {
        return RuntimeTypeLiteral.boManager(new TypeLiteral<MockBoEndpoint.MockEntity>() {
        }, new TypeLiteral<UUID>() {
        });
    }

    protected InMemoryBOManager<MockBoEndpoint.MockEntity, UUID> getMockManagerInstance() {
        return new InMemoryBOManager<>();
    }
}