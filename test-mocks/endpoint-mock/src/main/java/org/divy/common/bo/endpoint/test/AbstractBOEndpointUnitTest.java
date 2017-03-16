package org.divy.common.bo.endpoint.test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.test.ITestDataProvider;
import org.junit.Before;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

/**
 *
 */
public abstract class AbstractBOEndpointUnitTest<E extends IBusinessObject<I>, I extends Serializable>
        extends AbstractBOEndpointTest<E, I> {

    public static final String STATUS = "status";
    public static final String HEADERS = "headers";
    public static final String ENTITY = "entity";
    @Inject
    public AbstractBOEndpoint<E, I> endpointInstance;

    /**
     * @param testDataProvider
     */
    public AbstractBOEndpointUnitTest(ITestDataProvider<E> testDataProvider) {
        super(testDataProvider);
    }


    @Override
    protected E doAssertExists(I id) {
        Response response = endpointInstance.read(id, mock(UriInfo.class));

        assertThat(response,
                either(both(hasProperty(STATUS, is(equalTo(200)))).and(hasProperty(ENTITY, notNullValue())))
                        .or(both(hasProperty(STATUS, is(equalTo(204)))).and(hasProperty(ENTITY, nullValue()))));
        return (E) response.getEntity();
    }

    @Override
    protected E doCreateEntity(E entity) {

        Response response = endpointInstance.create(entity, mock(UriInfo.class));
        assertThat(response, hasProperty(HEADERS,
                hasKey(is(equalTo(HttpHeaders.LOCATION)))));

        I key = createKeyFromURI(response.getHeaders().getFirst(HttpHeaders.LOCATION).toString());
        return doGetByKey(key);
    }


    @Override
    protected E doGetByKey(I id) {
        Response response = endpointInstance.read(id, mock(UriInfo.class));
        return (E) response.getEntity();
    }

    @Override
    protected void doAssertNotExists(I id) {
        Response response = endpointInstance.read(id, mock(UriInfo.class));

        assertThat(response, both(hasProperty(STATUS, is(equalTo(404)))).and(hasProperty(ENTITY, nullValue())));

    }

    @Override
    protected void doUpdateEntity(I id, E entity) {
        Response response = this.endpointInstance.update(entity.identity(), entity, mock(UriInfo.class));
        assertThat(response, hasProperty(STATUS, is(equalTo(204))));
    }

    @Override
    protected void doDeleteEntity(E entity) {
        Response response = this.endpointInstance.delete(entity.identity(), mock(UriInfo.class));
        assertThat(response, hasProperty(STATUS, is(equalTo(204))));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<E> doSearchEntities(Query searchQuery) {
        Response response = this.endpointInstance.search(searchQuery, mock(UriInfo.class));
        assertThat(response, hasProperty(STATUS, is(equalTo(200))));
        assertThat(response, hasProperty(ENTITY, notNullValue()));
        return (List<E>) response.getEntity();
    }

    private I createKeyFromURI(String uri) {
        String[] segments = UriBuilder.fromPath(uri).build().getPath().split("/");
        return toKey(segments[segments.length - 1]);
    }

    public AbstractBOEndpoint<E, I> getEndpointInstance() {
        return endpointInstance;
    }

    public void setEndpointInstance(AbstractBOEndpoint<E, I> endpointInstance) {
        this.endpointInstance = endpointInstance;
    }

    @Before
    public void setup() {

        this.endpointInstance = createEndpointInstance();
        Guice.createInjector(getTestModules())
                .injectMembers(this);
    }

    protected abstract AbstractBOEndpoint<E, I> createEndpointInstance();

    public Iterable<Module> getTestModules() {
        return Collections.singletonList(new AbstractModule() {
            @Override
            public void configure() {
                @SuppressWarnings("unchecked")
                Class<AbstractBOEndpoint<E, I>> endPointClass = (Class<AbstractBOEndpoint<E, I>>) getEndPointClass();

                bind(LinkBuilderFactory.class).toInstance(new MockLinkBuilderFactory("http", "localhost:8080", ""));
                bind(getEndpointTypeLiteral()).to(endPointClass);
                bind(getManagerTypeLiteral()).toInstance(getMockManagerInstance());
                bind(HttpServletRequest.class).toInstance(mock(HttpServletRequest.class));
            }
        });
    }

    protected abstract InMemoryBOManager<E, I> getMockManagerInstance();

    protected abstract TypeLiteral<IBOManager<E, I>> getManagerTypeLiteral();

    protected abstract Class<E> getEntityClass();

    protected abstract TypeLiteral<AbstractBOEndpoint<E, I>> getEndpointTypeLiteral();

    protected abstract I toKey(String segment);
}