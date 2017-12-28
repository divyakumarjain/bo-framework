package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.endpoint.hypermedia.association.AssociationsHandler;
import org.divy.common.bo.endpoint.jersey.hypermedia.JerseyRepresentation;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.HyperMediaMapper;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.factory.DefaultHATEOASJerseyEndpoint;
import org.divy.common.rest.response.JerseyResponseEntityBuilderFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.UUID;


public class JerseyHyperMediaEndPointFactory implements ResourceConfigCustomizer {

    private static final String ID_PATH = "/{id}";

    private final MetaDataProvider metaDataProvider;
    private final BeanNamingStrategy beanNamingStrategy;
    private final EndPointRegistry endPointRegistry;
    private final JerseyEndpointConfigProperties configProperties;

    public JerseyHyperMediaEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy
            , EndPointRegistry endPointRegistry
            , JerseyEndpointConfigProperties configProperties) {

        this.metaDataProvider = metaDataProvider;
        this.beanNamingStrategy = beanNamingStrategy;
        this.endPointRegistry = endPointRegistry;
        this.configProperties = configProperties;
    }

    private void initializeHypermediaEndpoints(ResourceConfig config) {
        metaDataProvider.getEntityTypes().stream()
                .map(this::buildEndpointClass)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(config::register);
    }

    private Optional<Class> buildEndpointClass(Class<? extends BusinessObject> typeClass) {

        return DynamicClassBuilder.createClass(typeClass.getSimpleName() + "HyperMediaEndPoint")
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
                            .value("jerseyResponseEntityBuilderHyperMediaFactory")
                            .and()
                        .and()
                    .superParam(HyperMediaMapper.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateHyperMediaMapperId(typeClass))
                            .and()
                        .and()
                    .superParam(AssociationsHandler.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateAssociationsHandler(typeClass))
                        .build()

        .map(endpointClass-> {
            endPointRegistry.addEntityEndPointMap(typeClass.getSimpleName(), endpointClass);

            return endpointClass;
        });
    }


    @Override
    public void customize(ResourceConfig config) {
        this.initializeHypermediaEndpoints(config);
    }
}

