package org.divy.common.bo.endpoint.test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.test.TestDataProvider;
import org.divy.common.exception.NotFoundException;
import org.divy.common.rest.JerseyLinkBuilderFactoryImpl;
import org.junit.Before;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

/**
 *
 */
public abstract class BaseBOEndpointUnitTest<E extends BusinessObject<I>, I extends Serializable>
        extends AbstractBOEndpointTest<E, I, Response> {

    private static final String STATUS = "status";
    private static final String HEADERS = "headers";
    private static final String ENTITY = "entity";

    private BaseBOEndpoint<E, I, Response> endpointInstance;

    /**
     * @param testDataProvider the test data provider
     */
    public BaseBOEndpointUnitTest(TestDataProvider<E> testDataProvider) {
        super(testDataProvider);
    }


    @Override
    protected E doAssertExists(I id) {
        Response response = endpointInstance.read(id);


        assertThat(response,
                either(both(hasProperty(STATUS, is(equalTo(200)))).and(hasProperty(ENTITY, notNullValue())))
                        .or(both(hasProperty(STATUS, is(equalTo(204)))).and(hasProperty(ENTITY, nullValue()))));
        return (E) response.getEntity();
    }

    @Override
    protected E doCreateEntity(E entity)
    {
        Response response = endpointInstance.create(entity);
        assertThat(response, hasProperty(HEADERS,
                hasKey(is(equalTo(HttpHeaders.LOCATION)))));

        I key = createKeyFromURI(response.getHeaders().getFirst(HttpHeaders.LOCATION).toString());
        return doGetByKey(key).orElseThrow( () -> new NotFoundException("Could not find the entity") );
    }


    @Override
    protected Optional<E> doGetByKey(I id) {
        Response response = endpointInstance.read(id);
        return Optional.ofNullable( (E)response.getEntity() );
    }

    @Override
    protected void doAssertNotExists(I id) {
        Response response = endpointInstance.read(id);

        assertThat(response, both(hasProperty(STATUS, is(equalTo(404)))).and(hasProperty(ENTITY, nullValue())));

    }

    @Override
    protected void doUpdateEntity(I id, E entity) {
        Response response = this.endpointInstance.update(entity.identity(), entity);
        assertThat(response, hasProperty(STATUS, is(equalTo(204))));
    }

    @Override
    protected void doDeleteEntity(E entity) {
        Response response = this.endpointInstance.delete(entity.identity());
        assertThat(response, hasProperty(STATUS, is(equalTo(204))));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<E> doSearchEntities(Query searchQuery) {
        Response response = this.endpointInstance.search(searchQuery);
        assertThat(response, hasProperty(STATUS, is(equalTo(200))));
        assertThat(response, hasProperty(ENTITY, notNullValue()));
        return (List<E>) response.getEntity();
    }

    private I createKeyFromURI(String uri) {
        String[] segments = UriBuilder.fromPath(uri).build().getPath().split("/");
        return toKey(segments[segments.length - 1]);
    }

    @Before
    public void setup() {

        this.endpointInstance = createEndpointInstance();
        Guice.createInjector(getTestModules())
                .injectMembers(this);
    }

    protected abstract BaseBOEndpoint<E, I, Response> createEndpointInstance();

    public Iterable<Module> getTestModules() {
        return Collections.singletonList(new AbstractModule() {
            @Override
            public void configure() {
                bind(JerseyLinkBuilderFactoryImpl.class);
                bind(getManagerTypeLiteral()).toInstance(getMockManagerInstance());
                bind(HttpServletRequest.class).toInstance(mock(HttpServletRequest.class));
            }
        });
    }

    protected abstract InMemoryBOManager<E, I> getMockManagerInstance();

    protected abstract TypeLiteral<BOManager<E, I>> getManagerTypeLiteral();

    protected abstract Class<E> getEntityClass();

    protected abstract TypeLiteral<BaseBOEndpoint<E, I, Response>> getEndpointTypeLiteral();

    protected abstract I toKey(String segment);
}
