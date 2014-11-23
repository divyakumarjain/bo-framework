package org.divy.common.bo.endpoint.json.test;

import com.google.inject.RuntimeTypeLiteral;
import com.google.inject.TypeLiteral;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.endpoint.test.AbstractBOEndpointUnitTest;
import org.divy.common.bo.endpoint.test.InMemoryBOManager;
import org.divy.common.bo.query.IQuery;

import java.util.*;

public class MockBoEndpointUnitTest extends AbstractBOEndpointUnitTest<MockEntity,String> {

    private static final String MOCK_ENTITY_PATH = "/mock";

    public MockBoEndpointUnitTest() {
        super(new MockBoTestDataProvider());
    }

    @Override
    protected String getIdentifier(MockEntity mockEntity) {
        return mockEntity.identity();
    }

    @Override
    protected void extendedTestCreatedEntity(MockEntity mockEntity) {

    }

    @Override
    protected void extendedTestUpdatedEntity(MockEntity mockEntity) {

    }

    @Override
    protected Class<? extends AbstractBOEndpoint> getEndPointClass() {
        return MockBoEndpoint.class;
    }

    @Override
    protected MockBoEndpoint createEndpointInstance() {
        return new MockBoEndpoint();
    }

    @Override
    protected TypeLiteral<AbstractBOEndpoint<MockEntity, String>> getEndpointTypeLiteral() {
        return new TypeLiteral<AbstractBOEndpoint<MockEntity,String>>(){};
    }

    protected Class<MockEntity> getEntityClass() {
        return MockEntity.class;
    }

    protected TypeLiteral<IBOManager<MockEntity, String>> getManagerTypeLiteral() {
        return RuntimeTypeLiteral.boManager(new TypeLiteral<MockEntity>() {
        }, new TypeLiteral<String>() {
        });
    }

    protected InMemoryBOManager getMockManagerInstance() {
        return new InMemoryBOManager(new KeyGenerator<MockEntity,String>() {
            public String getRandomKey() {
                return UUID.randomUUID().toString();
            }

            @Override
            public void initializeKey(MockEntity mockEntity) {
                mockEntity.setUuid(getRandomKey());
            }
        });
    }
}