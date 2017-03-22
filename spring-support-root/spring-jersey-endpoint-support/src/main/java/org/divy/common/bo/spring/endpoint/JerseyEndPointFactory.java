package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.builder.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.Optional;
import java.util.UUID;

public class JerseyEndPointFactory implements ResourceConfigCustomizer {

    private final MetaDataProvider metaDataProvider;
    private final BeanNamingStrategy beanNamingStrategy;
    private final EndPointRegistry endPointRegistry;
    private final JerseyEndpointConfigProperties configProperties;

    @Autowired
    public JerseyEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy
            , EndPointRegistry endPointRegistry
            , JerseyEndpointConfigProperties configProperties) {

        this.metaDataProvider = metaDataProvider;
        this.beanNamingStrategy = beanNamingStrategy;
        this.endPointRegistry = endPointRegistry;
        this.configProperties = configProperties;
    }

    private void initializeEndpoints(ResourceConfig config) {
        metaDataProvider.getEntityTypes().stream()
                .map(this::buildEndpointClass)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(config::register);
    }

    private Optional<Class> buildEndpointClass(Class<? extends IBusinessObject> typeClass) {
        return DynamicClassBuilder.createClass(typeClass.getSimpleName() + "EndPoint")
                .subClass(BaseBOEndpoint.class)
                    .addAnnotation(javax.ws.rs.Path.class)
                        .value(configProperties.getApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase())
                        .and()
                .proxySuperMethod("create")
                    .addAnnotation(POST.class)
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .param(typeClass)
                        .addAnnotation(NotNull.class)
                            .and()
                        .and()
                    .param()
                        .addAnnotation(Context.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("update")
                    .addAnnotation(PUT.class)
                        .and()
                    .addAnnotation(Path.class)
                        .value("/{id}")
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .param(UUID.class)
                        .addAnnotation(PathParam.class)
                        .value("id")
                            .and()
                        .and()
                    .param(typeClass)
                        .addAnnotation(NotNull.class)
                            .and()
                        .and()
                    .param()
                        .addAnnotation(Context.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("delete")
                    .addAnnotation(DELETE.class)
                        .and()
                    .addAnnotation(Path.class)
                        .value("/{id}")
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .param(UUID.class)
                        .addAnnotation(NotNull.class)
                            .and()
                        .addAnnotation(PathParam.class)
                            .value("id")
                            .and()
                        .and()
                    .param()
                        .addAnnotation(Context.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("search")
                    .addAnnotation(POST.class)
                        .and()
                    .addAnnotation(Path.class)
                        .value("/search")
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .param()
                        .addAnnotation(NotNull.class)
                            .and()
                        .and()
                    .param()
                        .addAnnotation(Context.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("list")
                    .addAnnotation(GET.class)
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .param()
                        .addAnnotation(Context.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("read").name("readEndPoint")
                    .addAnnotation(GET.class)
                        .and()
                    .addAnnotation(Path.class)
                        .value("/{id}")
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {"application/json"})
                        .and()
                    .param(UUID.class)
                        .addAnnotation(NotNull.class)
                            .and()
                        .addAnnotation(PathParam.class)
                            .value("id")
                            .and()
                        .and()
                    .param()
                        .addAnnotation(Context.class)
                            .and()
                        .and()
                    .and()
                .addConstructor()
                    .addAnnotation(Autowired.class)
                        .and()
                    .superParam(IBOManager.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateManagerId(typeClass))
                            .and()
                        .and()
                    .superParam(ResponseEntityBuilderFactory.class)
                        .addAnnotation(Qualifier.class)
                            .value("responseEntityBuilderFactory")
                            .and()
                        .and()
                .build()
        .map(endpointClass-> {
            endPointRegistry.addEntityEndPointMap(typeClass, endpointClass);
            return endpointClass;
        });
    }

    @Override
    public void customize(ResourceConfig config) {
        initializeEndpoints(config);
    }
}

