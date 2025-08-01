package org.divy.common.bo.endpoint.test;

import com.google.inject.servlet.GuiceFilter;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.test.TestDataProvider;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.jetty.JettyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainer;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.util.List;

/**
 *
 *
 */
public abstract class BaseBOEndpointContainerTest<E extends BusinessObject<I>
        , I extends Serializable
        , R>
        extends AbstractBOEndpointTest<E, I, R> {

    private final JerseyTest jerseyTestProxy;

    /**
     * @param testDataProvider data provider for the test
     */
    protected BaseBOEndpointContainerTest(TestDataProvider<E> testDataProvider) {
        super(testDataProvider);
        jerseyTestProxy = new RestResourceTest();
    }

    @BeforeEach
    public void start() throws Exception {
        jerseyTestProxy.setUp();
    }

    @AfterEach
    public void stop() throws Exception {
        jerseyTestProxy.tearDown();
    }

    private WebTarget getEndPointTargetMethod(String method) {
        return jerseyTestProxy.target(UriBuilder.fromMethod(getEndPointClass(), method).build().toString());
    }

    private WebTarget getEntityTarget(String identity) {
        return jerseyTestProxy.target(UriBuilder.fromResource(getEndPointClass()).path(identity).build().toString());
    }

    @Override
    protected void doUpdateEntity(I id, E entity) {
        getEntityTarget((String) entity.identity()).request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(entity,MediaType.APPLICATION_JSON_TYPE));
    }

    @Override
    protected E doAssertExists(I id) {
        return getEntityTarget((String) id).request(MediaType.APPLICATION_JSON_TYPE).get(getEntityClass());
    }

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.TestBaseManager#doDeleteEntity(org.divy.common.bo.repository.BusinessObject)
     */
    @Override
    protected void doDeleteEntity(E entity) {
        getEntityTarget((String) entity.identity()).request(MediaType.APPLICATION_JSON_TYPE).delete();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<E> doSearchEntities(Query searchQuery) {
        return (List<E>) getEndPointTargetMethod("search").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(getEntityListClass(),MediaType.APPLICATION_JSON_TYPE))
                .getEntity();
    }

    abstract GenericType<E> getEntityClass();

    abstract GenericType<List<E>> getEntityListClass();

    /**
     * Create a web resource whose URI refers to the base URI the Web
     * application is deployed at.
     *
     * @return the created web resource
     */
    protected UriBuilder uriBuilder() {
        return jerseyTestProxy.target().getUriBuilder();
    }

    public class RestResourceTest extends JerseyTest {

        RestResourceTest() {
            super();
        }

        protected Client client(TestContainer tc) {
            var client = super.client();
            client.register(new LoggingFeature());
            return client;
        }

        @Override
        protected TestContainerFactory getTestContainerFactory() {
            return new JettyTestContainerFactory();
        }


        @Override
        protected Application configure() {
            return new JerseyConfig();
        }
    }

    public class JerseyConfig extends ResourceConfig {
        public JerseyConfig() {
            register(GuiceFilter.class);
            register(new LoggingFeature());
            packages(getEndPointClass().getPackage().getName());
        }
    }
}
