/**
 * 
 */
package org.divy.common.bo.endpoint.test;

import com.google.inject.servlet.GuiceFilter;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBOCRUDBase;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.jetty.JettyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainer;
import org.glassfish.jersey.test.spi.TestContainerException;
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
import java.net.URI;
import java.util.List;

/**
 * @author Divyakumar
 *
 */
public abstract class AbstractBOEndpointContainerTest<ENTITY extends IBusinessObject<ID>, ID>
    extends AbstractBOEndpointTest<ENTITY, ID> {

    protected JerseyTest jerseyTestProxy;

    public class RestResourceTest extends JerseyTest {

        protected Client client(TestContainer tc) {
            Client client = super.client();
            client.register(LoggingFilter.class);
            return client;
        }

        public RestResourceTest() {
            super();
        }

        protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
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

    private WebTarget getEndPointTarget() {
        return jerseyTestProxy.target(UriBuilder.fromResource(getEndPointClass()).build().toString());
    }

    private WebTarget getEntityTarget(String idenity) {
        return jerseyTestProxy.target(UriBuilder.fromResource(getEndPointClass()).path(idenity).build().toString());
    }

    @Override
    protected void doUpdateEntity(ENTITY entity) {
        getEntityTarget((String) entity.identity()).request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(entity,MediaType.APPLICATION_JSON_TYPE));
    }

    @Override
    protected ENTITY doGetByKey(ID id) {
        return getEntityTarget((String) id).request(MediaType.APPLICATION_JSON_TYPE).get(getEntityClass());
    }

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.TestBaseManager#doDeleteEntity(org.divy.common.bo.IBusinessObject)
     */
    @Override
    protected void doDeleteEntity(ENTITY entity) {
        getEntityTarget((String) entity.identity()).request(MediaType.APPLICATION_JSON_TYPE).delete();
    }

    @Override
    protected List<ENTITY> doSearchEntities(IQuery searchQuery) {
        return (List<ENTITY>) getEndPointTargetMethod("search").request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(getEntityListClass(),MediaType.APPLICATION_JSON_TYPE))
                .getEntity();
    }

    protected abstract GenericType<ENTITY> getEntityClass();

    protected abstract GenericType<List<ENTITY>> getEntityListClass();

    /**
     * @param testDataProvider
     */
    public AbstractBOEndpointContainerTest(ITestDataProvider<ENTITY, ID> testDataProvider) {
        super(testDataProvider);
        jerseyTestProxy = new RestResourceTest();
    }

    /**
     * Create a web resource whose URI refers to the base URI the Web
     * application is deployed at.
     *
     * @return the created web resource
     */
    protected UriBuilder uriBuilder() {
        return jerseyTestProxy.target().getUriBuilder();
    }
}
