package org.divy.common.bo.endpoint.json.test;

import com.google.inject.RuntimeTypeLiteral;
import com.google.inject.TypeLiteral;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.endpoint.test.BaseBOEndpointUnitTest;
import org.divy.common.bo.endpoint.test.InMemoryBOManager;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.rest.JerseyEntityURLBuilderImpl;
import org.divy.common.rest.JerseyLinkBuilderFactoryImpl;
import org.divy.common.rest.response.JerseyResponseEntityBuilderFactory;

import javax.ws.rs.core.Response;
import java.util.UUID;

public class MockBoEndpointUnitTest extends BaseBOEndpointUnitTest<MockBoJerseyEndpoint.MockEntity, UUID> {

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
    protected Class<? extends BaseBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID, Response>> getEndPointClass() {
        return MockBoJerseyEndpoint.class;
    }

    @Override
    protected BaseBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID, Response> createEndpointInstance() {
        JerseyLinkBuilderFactoryImpl mock = new JerseyLinkBuilderFactoryImpl();
        JerseyEntityURLBuilderImpl jerseyEntityURLBuilder = new JerseyEntityURLBuilderImpl(mock);
        jerseyEntityURLBuilder.addEntityEndPointMap(MockBoJerseyEndpoint.MockEntity.class.getSimpleName(), getEndPointClass());
        ResponseEntityBuilderFactory responseEntityBuilderFactory =  new JerseyResponseEntityBuilderFactory<>(jerseyEntityURLBuilder);
        return new MockBoJerseyEndpoint(new InMemoryBOManager<>(), responseEntityBuilderFactory);
    }

    @Override
    protected TypeLiteral<BaseBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID, Response>> getEndpointTypeLiteral() {
        return new TypeLiteral<BaseBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID, Response>>(){};
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