/**
 * 
 */
package org.divy.common.bo.endpoint.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.query.defaults.Query;
import org.divy.common.bo.test.ITestDataProvider;
import org.junit.Before;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;

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
	protected ENTITY doAssertExists(ID id) {
        Response response = endpointInstance.read(id, mock(UriInfo.class));

        assertThat(response,
                either(
                        both(hasProperty("status", is(equalTo(200)))).and(hasProperty("entity", notNullValue())))
                .or(both(hasProperty("status", is(equalTo(204)))).and(hasProperty("entity", nullValue()))));
        return (ENTITY) response.getEntity();
    }

    @Override
    protected ENTITY doCreateEntity(ENTITY entity) {

        Response response = endpointInstance.create(entity, mock(UriInfo.class));
        assertThat(response, hasProperty("headers",
                hasKey(is(equalTo(HttpHeaders.LOCATION)))));

        ID key = (ID)createKeyFromURI(response.getHeaders().getFirst(HttpHeaders.LOCATION).toString());
        return doGetByKey(key);
    }
    

	@Override
	protected ENTITY doGetByKey(ID id) {
		Response response = endpointInstance.read(id, mock(UriInfo.class));
        return (ENTITY) response.getEntity();
	}

	@Override
	protected void doAssertNotExists(ID id) {
		Response response = endpointInstance.read(id, mock(UriInfo.class));

        assertThat(response,both(hasProperty("status", is(equalTo(404)))).and(hasProperty("entity", nullValue())));
		
	}

    @Override
    protected void doUpdateEntity(ENTITY entity) {
        Response response = this.endpointInstance.update(entity.identity(),entity, mock(UriInfo.class));
        assertThat(response, hasProperty("status", is(equalTo(204))));
    }

    @Override
    protected void doDeleteEntity(ENTITY entity) {
        Response response = this.endpointInstance.delete(entity.identity(), mock(UriInfo.class));
        assertThat(response,hasProperty("status",is(equalTo(204))));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<ENTITY> doSearchEntities(IQuery searchQuery) {
        Response response = this.endpointInstance.search((Query) searchQuery, mock(UriInfo.class));
        assertThat(response,hasProperty("status",is(equalTo(200))));
        assertThat(response,hasProperty("entity",notNullValue()));
        return (List<ENTITY>)response.getEntity();
    }

    private ID createKeyFromURI(String uri) {
        String[] segments = UriBuilder.fromPath(uri).build().getPath().split("/");
        return toKey(segments[segments.length-1]);
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
                @SuppressWarnings("unchecked")
				Class<AbstractBOEndpoint<ENTITY, ID>> endPointClass = (Class<AbstractBOEndpoint<ENTITY, ID>>)getEndPointClass();

                bind(getEndpointTypeLiteral()).to(endPointClass);
                bind(getManagerTypeLiteral()).toInstance(getMockManagerInstance());
                bind(HttpServletRequest.class).toInstance(mock(HttpServletRequest.class));
            }
        });
    }

    protected abstract InMemoryBOManager<ENTITY, ID> getMockManagerInstance();

    protected abstract TypeLiteral<IBOManager<ENTITY, ID>> getManagerTypeLiteral();

    protected abstract Class<ENTITY> getEntityClass();

    protected abstract TypeLiteral<AbstractBOEndpoint<ENTITY, ID>> getEndpointTypeLiteral();

    protected abstract ID toKey(String segment);
}
