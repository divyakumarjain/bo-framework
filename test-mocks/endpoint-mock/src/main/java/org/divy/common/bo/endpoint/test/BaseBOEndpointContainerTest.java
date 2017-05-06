package org.divy.common.bo.endpoint.test;

import com.google.inject.servlet.GuiceFilter;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.test.ITestDataProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.jetty.JettyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainer;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.After;
import org.junit.Before;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.util.List;

/**
 *
 *
 */
public abstract class BaseBOEndpointContainerTest<E extends IBusinessObject<I>
        , I extends Serializable
        , R>
        extends AbstractBOEndpointTest<E, I, R> {

    private final JerseyTest jerseyTestProxy;

    /**
     * @param testDataProvider data provider for the test
     */
    public BaseBOEndpointContainerTest(ITestDataProvider<E> testDataProvider) {
        super(testDataProvider);
        jerseyTestProxy = new RestResourceTest();
    }

    @Before
    public void start() throws Exception {
        jerseyTestProxy.setUp();
    }

    @After
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
     * @see org.divy.common.bo.test.TestBaseManager#doDeleteEntity(org.divy.common.bo.IBusinessObject)
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
            Client client = super.client();
            client.register(LoggingFilter.class);
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
            register(LoggingFilter.class);
            packages(getEndPointClass().getPackage().getName());
        }
    }
}
