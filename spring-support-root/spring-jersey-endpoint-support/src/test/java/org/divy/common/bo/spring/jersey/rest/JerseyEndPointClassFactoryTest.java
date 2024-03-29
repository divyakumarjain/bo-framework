package org.divy.common.bo.spring.jersey.rest;

import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategyImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.UUID;

import static org.divy.common.bo.dynamic.test.AnnotationWithParameterMatcher.*;
import static org.divy.common.bo.dynamic.test.HasMethodMatcher.hasMethod;
import static org.divy.common.bo.dynamic.test.MethodParameterWithAnnotationMatcher.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class JerseyEndPointClassFactoryTest {

    JerseyEndPointClassFactory fixture = null;

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

        fixture = new JerseyEndPointClassFactory(metaDataProvider, beanNamingStrategy, endPointRegistry, configProperties);
    }

    @Test
    void test() {
        ResourceConfig config = mock(ResourceConfig.class);

        when( metaDataProvider.getEntityTypes())
                .thenReturn( Collections.singletonList( MockEntity.class ) );

        when(configProperties.getApiEndpointPath())
                .thenReturn("api");

        fixture.customize( config );

        var argument = ArgumentCaptor.forClass(Class.class);
        verify( config, times( 1 ) )
                .register( argument.capture() );

        verify( metaDataProvider, times( 1 ) ).getEntityTypes();
        verify( endPointRegistry, times( 1 ) ).addEntityEndPointMap( eq("MockEntity"), any());

        Class endpointClass = argument.getValue();

        assertThat(endpointClass, isAnnotatedWith(Path.class, value(equalTo("api/mockentity"))));

        assertThat(endpointClass, hasMethod("createMethod", hasParameter(MockEntity.class, isAnnotatedWith(NotNull.class)),
                allOf(
                        isAnnotatedWith(POST.class),
                        isAnnotatedWith(Produces.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON)))),
                        isAnnotatedWith(Consumes.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON))))
                )));

        assertThat(endpointClass, hasMethod("updateMethod",
                allOf(
                        hasParameter(UUID.class, isAnnotatedWith(PathParam.class, value(equalTo("id")))),
                        hasParameter(MockEntity.class, isAnnotatedWith(NotNull.class))
                ),
                allOf(
                        isAnnotatedWith(PUT.class),
                        isAnnotatedWith(Path.class, value(equalTo("/{id}"))),
                        isAnnotatedWith(Produces.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON)))),
                        isAnnotatedWith(Consumes.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON))))
                )));

        assertThat(endpointClass, hasMethod("deleteMethod",
                allOf(
                        hasParameter(UUID.class,
                                allOf(
                                        isAnnotatedWith(NotNull.class),
                                        isAnnotatedWith(PathParam.class, value(equalTo("id")))
                                ))
                ),
                allOf(
                        isAnnotatedWith(DELETE.class),
                        isAnnotatedWith(Path.class, value(equalTo("/{id}"))),
                        isAnnotatedWith(Produces.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON)))),
                        isAnnotatedWith(Consumes.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON))))
                )));

        assertThat(endpointClass, hasMethod("searchMethod",
                hasParameter(Query.class),
                allOf(
                        isAnnotatedWith(POST.class),
                        isAnnotatedWith(Path.class, value(equalTo("/search"))),
                        isAnnotatedWith(Produces.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON)))),
                        isAnnotatedWith(Consumes.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON))))
                )));

        assertThat(endpointClass, hasMethod("listMethod", null,
                allOf(
                        isAnnotatedWith(GET.class),
                        isAnnotatedWith(Produces.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON)))),
                        isAnnotatedWith(Consumes.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON))))
                )));

        assertThat(endpointClass, hasMethod("readMethod",
                allOf(
                        hasParameter(UUID.class,
                                allOf(
                                        isAnnotatedWith(NotNull.class),
                                        isAnnotatedWith(PathParam.class, value(equalTo("id")))
                                ))),
                allOf(
                        isAnnotatedWith(GET.class),
                        isAnnotatedWith(Path.class, value(equalTo("/{id}"))),
                        isAnnotatedWith(Produces.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON)))),
                        isAnnotatedWith(Consumes.class, value(arrayContainingInAnyOrder(equalTo(MediaType.APPLICATION_JSON))))
                )));
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
