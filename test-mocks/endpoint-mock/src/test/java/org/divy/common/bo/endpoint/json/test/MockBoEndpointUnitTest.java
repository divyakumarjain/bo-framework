package org.divy.common.bo.endpoint.json.test;

import com.google.inject.RuntimeTypeLiteral;
import com.google.inject.TypeLiteral;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.endpoint.test.AbstractBOEndpointUnitTest;
import org.divy.common.bo.endpoint.test.InMemoryBOManager;
import org.divy.common.bo.endpoint.test.MockLinkBuilderFactory;

import java.util.UUID;

public class MockBoEndpointUnitTest extends AbstractBOEndpointUnitTest<MockBoJerseyEndpoint.MockEntity, UUID> {

    public MockBoEndpointUnitTest() {
        super(new MockBoTestDataProvider());
    }

    @Override
    protected UUID getIdentifier(MockBoJerseyEndpoint.MockEntity mockEntity) {
        return mockEntity.identity();
    }

    @Override
    protected void extendedTestCreatedEntity(MockBoJerseyEndpoint.MockEntity mockEntity) {

    }

    @Override
    protected void extendedTestUpdatedEntity(MockBoJerseyEndpoint.MockEntity mockEntity) {

    }

    @Override
    protected Class<? extends AbstractBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID>> getEndPointClass() {
        return (Class<? extends AbstractBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID>>) MockBoJerseyEndpoint.class;
    }

    @Override
    protected AbstractBOEndpoint createEndpointInstance() {
        return new MockBoJerseyEndpoint(new InMemoryBOManager<>(), new MockLinkBuilderFactory("http", "localhost:8080", ""));
    }

    @Override
    protected TypeLiteral<AbstractBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID>> getEndpointTypeLiteral() {
        return new TypeLiteral<AbstractBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID>>(){};
    }

    @Override
    protected UUID toKey(String key) {
        return UUID.fromString(key);
    }

    protected Class<MockBoJerseyEndpoint.MockEntity> getEntityClass() {
        return MockBoJerseyEndpoint.MockEntity.class;
    }

    protected TypeLiteral<IBOManager<MockBoJerseyEndpoint.MockEntity, UUID>> getManagerTypeLiteral() {
        return RuntimeTypeLiteral.boManager(new TypeLiteral<MockBoJerseyEndpoint.MockEntity>() {
        }, new TypeLiteral<UUID>() {
        });
    }

    protected InMemoryBOManager<MockBoJerseyEndpoint.MockEntity, UUID> getMockManagerInstance() {
        return new InMemoryBOManager<>();
    }
}