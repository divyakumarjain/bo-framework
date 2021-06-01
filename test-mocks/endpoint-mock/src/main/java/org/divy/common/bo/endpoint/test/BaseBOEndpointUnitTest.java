package org.divy.common.bo.endpoint.test;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.test.TestDataProvider;
import org.divy.common.exception.NotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        assertThat(response, hasProperty(STATUS, is( Matchers.anyOf(equalTo(200), equalTo(204)))));
        return (List<E>) response.getEntity();
    }

    private I createKeyFromURI(String uri) {
        String[] segments = UriBuilder.fromPath(uri).build().getPath().split("/");
        return toKey(segments[segments.length - 1]);
    }

    @BeforeEach
    public void setup() {

        this.endpointInstance = createEndpointInstance();
    }

    protected abstract BaseBOEndpoint<E, I, Response> createEndpointInstance();


    protected abstract InMemoryBOManager<E, I> getMockManagerInstance();

    protected abstract Class<E> getEntityClass();

    protected abstract I toKey(String segment);
}
