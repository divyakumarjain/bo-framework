package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.endpoint.jersey.hatoas.JerseyRepresentation;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.HATOASMapper;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.factory.DefaultHATEOASJerseyEndpoint;
import org.divy.common.rest.response.JerseyResponseEntityBuilderFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.invoke.MethodHandles;
import java.util.Optional;
import java.util.UUID;


public class JerseyHATOASEndPointFactory implements ResourceConfigCustomizer {

    private static final Logger LOGGER  = LoggerFactory.getLogger( JerseyHATOASEndPointFactory.class);

    private static final String ID_PATH = "/{id}";

    private final MetaDataProvider metaDataProvider;
    private final BeanNamingStrategy beanNamingStrategy;
    private final EndPointRegistry endPointRegistry;
    private final JerseyEndpointConfigProperties configProperties;

    public JerseyHATOASEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy
            , EndPointRegistry endPointRegistry
            , JerseyEndpointConfigProperties configProperties) {
        super(metaDataProvider,beanNamingStrategy,endPointRegistry,configProperties);
        this.metaDataProvider = metaDataProvider;
        this.beanNamingStrategy = beanNamingStrategy;
        this.endPointRegistry = endPointRegistry;
        this.configProperties = configProperties;
    }

    private void initializeHATOASEndpoints(ResourceConfig config) {
        metaDataProvider.getEntityTypes().stream()
                .map(this::buildEndpointClass)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(config::register);
    }

    private static       MethodHandles.Lookup           prvlookup;

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        JerseyHATOASEndPointFactory.class.getModule().addReads(JerseyHATOASEndPointFactory.class.getModule());
        try
        {
            prvlookup = MethodHandles.privateLookupIn(JerseyHATOASEndPointFactory.class, lookup);
        }
        catch( IllegalAccessException e ) {
            LOGGER.error( e.getMessage(), e );
        }
    }

    private Optional<Class> buildEndpointClass(Class<? extends BusinessObject> typeClass) {
        return DynamicClassBuilder.createClass( JerseyHyperMediaEndPointClassFactory.class.getPackageName() + "." + typeClass.getSimpleName() + "HyperMediaEndPoint")
                .subClass(DefaultHATEOASJerseyEndpoint.class)
                    .addAnnotation(javax.ws.rs.Path.class)
                        .value(configProperties.getHateoasApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase())
                        .and()
                .proxySuperMethod("create").name("createMethod")
                    .addAnnotation(POST.class)
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .param(JerseyRepresentation.class)
                        .addAnnotation(NotNull.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("update").name("updateMethod")
                    .addAnnotation(PUT.class)
                        .and()
                    .addAnnotation(Path.class)
                        .value(ID_PATH)
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .param(UUID.class)
                        .addAnnotation(PathParam.class)
                        .value("id")
                            .and()
                        .and()
                    .param(JerseyRepresentation.class)
                        .addAnnotation(NotNull.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("delete").name("deleteMethod")
                    .addAnnotation(DELETE.class)
                        .and()
                    .addAnnotation(Path.class)
                        .value(ID_PATH)
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .param(UUID.class)
                        .addAnnotation(NotNull.class)
                            .and()
                        .addAnnotation(PathParam.class)
                            .value("id")
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("search").name("searchMethod")
                    .addAnnotation(POST.class)
                        .and()
                    .addAnnotation(Path.class)
                        .value("/search")
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .param()
                        .addAnnotation(NotNull.class)
                            .and()
                         .and()
                    .and()
                .proxySuperMethod("list").name("listMethod")
                    .addAnnotation(GET.class)
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                         .and()
                    .and()
                .proxySuperMethod("read").name("readMethod")
                    .addAnnotation(GET.class)
                        .and()
                    .addAnnotation(Path.class)
                        .value(ID_PATH)
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .param(UUID.class)
                        .addAnnotation(NotNull.class)
                            .and()
                        .addAnnotation(PathParam.class)
                            .value("id")
                            .and()
                         .and()
                    .and()
                .addConstructor()
                    .addAnnotation(Autowired.class)
                        .and()
                    .superParam(BOManager.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateManagerId(typeClass))
                            .and()
                        .and()
                    .superParam(JerseyResponseEntityBuilderFactory.class)
                        .addAnnotation(Qualifier.class)
                            .value("jerseyResponseEntityBuilderHATOASFactory")
                            .and()
                        .and()
                    .superParam( HATOASMapper.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateHATOASMapperId(typeClass))
                            .and()
                        .and()
                    .superParam(AssociationsHandler.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateAssociationsHandler(typeClass))
                        .build( prvlookup )

        .map(endpointClass-> {
            endPointRegistry.addEntityEndPointMap(typeClass.getSimpleName(), endpointClass);

            return endpointClass;
        });
    }


    @Override
    public void customize(ResourceConfig config) {
        this.initializeHATOASEndpoints(config);
    }
}

