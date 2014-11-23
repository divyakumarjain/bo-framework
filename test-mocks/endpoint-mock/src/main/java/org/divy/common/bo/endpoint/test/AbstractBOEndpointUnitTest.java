/**
 * 
 */
package org.divy.common.bo.endpoint.test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.query.defaults.Query;
import org.divy.common.bo.test.ITestDataProvider;
import org.glassfish.jersey.server.Uri;
import org.junit.After;
import org.junit.Before;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

/**
 * @author Divyakumar
 *
 */
public abstract class AbstractBOEndpointUnitTest<ENTITY extends IBusinessObject<ID>, ID extends Serializable>
    extends AbstractBOEndpointTest<ENTITY, ID> {

    @Inject
    public AbstractBOEndpoint<ENTITY, ID> endpointInstance;

    /**
     *  @param testDataProvider
     */
    public AbstractBOEndpointUnitTest(ITestDataProvider<ENTITY, ID> testDataProvider) {
        super(testDataProvider);
    }

    @Override
    protected ENTITY doGetByKey(ID id) {
        Response response = endpointInstance.get(id, mock(UriInfo.class));

        assertThat(response,
                either(
                        both(hasProperty("status", is(equalTo(200)))).and(hasProperty("entity", notNullValue())))
                .or(both(hasProperty("status", is(equalTo(204)))).and(hasProperty("entity", nullValue()))));
        return (ENTITY)response.getEntity();
    }

    @Override
    protected ENTITY doCreateEntity(ENTITY entity) {

        Response response = endpointInstance.create(entity, mock(UriInfo.class));
        assertThat(response, hasProperty("headers",
                hasKey(is(equalTo(HttpHeaders.LOCATION)))));

        ID key = (ID)createKeyFromURI(response.getHeaders().getFirst(HttpHeaders.LOCATION).toString());
        return (ENTITY)doGetByKey(key);
    }

    @Override
    protected void doUpdateEntity(ENTITY entity) {
        Response response = this.endpointInstance.update(entity, mock(UriInfo.class));
        assertThat(response, hasProperty("status", is(equalTo(200))));
    }

    @Override
    protected void doDeleteEntity(ENTITY entity) {
        Response response = this.endpointInstance.delete(entity, mock(UriInfo.class));
        assertThat(response,hasProperty("status",is(equalTo(200))));
    }

    @Override
    protected List<ENTITY> doSearchEntities(IQuery searchQuery) {
        Response response = this.endpointInstance.search((Query) searchQuery, mock(UriInfo.class));
        assertThat(response,hasProperty("status",is(equalTo(200))));
        assertThat(response,hasProperty("entity",notNullValue()));
        return (List<ENTITY>)response.getEntity();
    }

    private String createKeyFromURI(String uri) {
        String[] segments = UriBuilder.fromPath(uri).build().getPath().split("/");
        return segments[segments.length-1];
    }

    public AbstractBOEndpoint<ENTITY, ID> getEndpointInstance() {
        return endpointInstance;
    }

    public void setEndpointInstance(AbstractBOEndpoint<ENTITY, ID> endpointInstance) {
        this.endpointInstance = endpointInstance;
    }

    @Before
    public void setup() {

        this.endpointInstance = createEndpointInstance();
        Guice.createInjector(getTestModules())
                .injectMembers(this);
    }

    protected abstract AbstractBOEndpoint<ENTITY, ID> createEndpointInstance();

    public Iterable<? extends Module> getTestModules() {
        return Arrays.asList( new AbstractModule() {
            @Override
            public void configure() {
                HashMap entityEndpointMap = new HashMap(1);
                entityEndpointMap.put(getEntityClass(),getEndPointClass());

                bind(getEndpointTypeLiteral()).to((Class)getEndPointClass());
                bind(getManagerTypeLiteral()).toInstance(getMockManagerInstance());
                bind(new TypeLiteral<Map<Class<? extends IBusinessObject>, Class<? extends AbstractCRUDEndpoint>>>() {
                }).annotatedWith(Names.named("entityEndpointMap")).
                        toInstance(entityEndpointMap);
            }
        });
    }

    protected abstract InMemoryBOManager getMockManagerInstance();

    protected abstract TypeLiteral<IBOManager<ENTITY, ID>> getManagerTypeLiteral();

    protected abstract Class<MockEntity> getEntityClass();

    protected abstract TypeLiteral<AbstractBOEndpoint<ENTITY, ID>> getEndpointTypeLiteral();
}
