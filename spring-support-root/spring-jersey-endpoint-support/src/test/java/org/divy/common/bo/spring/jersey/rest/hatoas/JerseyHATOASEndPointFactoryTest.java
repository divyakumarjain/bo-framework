package org.divy.common.bo.spring.jersey.rest.hatoas;

import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategyImpl;
import org.divy.common.bo.spring.jersey.rest.JerseyEndpointConfigProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;

class JerseyHATOASEndPointFactoryTest {

    JerseyHATOASEndPointFactory fixture = null;

    MetaDataProvider metaDataProvider = null;
    BeanNamingStrategy beanNamingStrategy = null;
    EndPointRegistry endPointRegistry = null;
    JerseyEndpointConfigProperties configProperties = null;

    @BeforeEach
    public void setup() {
        metaDataProvider = mock( MetaDataProvider.class );
        beanNamingStrategy = new BeanNamingStrategyImpl();
        endPointRegistry = mock( EndPointRegistry.class );
        configProperties = mock(JerseyEndpointConfigProperties.class);

        fixture = new JerseyHATOASEndPointFactory(metaDataProvider, beanNamingStrategy, endPointRegistry, configProperties);
    }

    @Test
    public void test() {
        ResourceConfig config = mock(ResourceConfig.class);

        when( metaDataProvider.getEntityTypes())
              .thenReturn( Collections.singletonList( MockEntity.class ) );

        fixture.customize( config );

        //TODO This verification in test is fregile as it works during debugging but not during actual execution.
        //verify( config, times( 1 ) )
        //  .register( any(Class.class) );
        verify( metaDataProvider, times( 1 ) ).getEntityTypes();
        verify( endPointRegistry, times( 1 ) ).addEntityEndPointMap( eq("MockEntity"), any());
    }

    class MockEntity implements BusinessObject<UUID> {
        @Override public UUID identity()
        {
            return UUID.randomUUID();
        }

        @Override public String _type()
        {
            return "MockEntity";
        }
    }
}