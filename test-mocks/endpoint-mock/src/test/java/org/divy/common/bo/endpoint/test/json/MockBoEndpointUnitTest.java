package org.divy.common.bo.endpoint.test.json;

import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.endpoint.test.BaseBOEndpointUnitTest;
import org.divy.common.bo.endpoint.test.InMemoryBOManager;
import org.divy.common.bo.jersey.rest.JerseyEndPointRegistry;
import org.divy.common.bo.jersey.rest.JerseyEntityURLBuilderImpl;
import org.divy.common.bo.jersey.rest.JerseyLinkBuilderFactoryImpl;
import org.divy.common.bo.jersey.rest.response.JerseyResponseEntityBuilderFactory;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.exception.NotFoundException;

import jakarta.ws.rs.core.Response;
import java.util.UUID;

import static org.junit.Assert.fail;

public class MockBoEndpointUnitTest extends BaseBOEndpointUnitTest<MockBoJerseyEndpoint.MockEntity, UUID> {

    public MockBoEndpointUnitTest() {
        super(new MockBoTestDataProvider());
    }

    @Override
    protected UUID getIdentifier(MockBoJerseyEndpoint.MockEntity mockEntity) {
        return mockEntity.identity();
    }

    @Override
    protected void doAssertNotExists(UUID id) {
        try {
            super.doAssertNotExists( id );
            fail("Expected org.divy.common.exception.NotFoundException");
        } catch ( NotFoundException ex ) {
            //success
        }
    }

    @Override
    protected void extendedTestCreatedEntity(MockBoJerseyEndpoint.MockEntity mockEntity) {

    }

    @Override
    protected void extendedTestUpdatedEntity(MockBoJerseyEndpoint.MockEntity mockEntity) {

    }

    @Override
    protected Class<? extends BaseBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID, Response>> getEndPointClass() {
        return MockBoJerseyEndpoint.class;
    }

    @Override
    protected BaseBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID, Response> createEndpointInstance() {
        JerseyLinkBuilderFactoryImpl mock = new JerseyLinkBuilderFactoryImpl();
        JerseyEndPointRegistry registry = new JerseyEndPointRegistry();
        JerseyEntityURLBuilderImpl jerseyEntityURLBuilder = new JerseyEntityURLBuilderImpl(mock, registry);
        registry.addEntityEndPointMap(MockBoJerseyEndpoint.MockEntity.class.getSimpleName(), getEndPointClass());
        ResponseEntityBuilderFactory responseEntityBuilderFactory =  new JerseyResponseEntityBuilderFactory<>(jerseyEntityURLBuilder);
        return new MockBoJerseyEndpoint(new InMemoryBOManager<>(), responseEntityBuilderFactory);
    }

    @Override
    protected UUID toKey(String key) {
        return UUID.fromString(key);
    }

    protected Class<MockBoJerseyEndpoint.MockEntity> getEntityClass() {
        return MockBoJerseyEndpoint.MockEntity.class;
    }

    protected InMemoryBOManager<MockBoJerseyEndpoint.MockEntity, UUID> getMockManagerInstance() {
        return new InMemoryBOManager<>();
    }
}
