package org.divy.common.bo.spring.jersey.rest;

import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.DynamicSubClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.constructor.DynamicClassConstructorBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicProxyMethodBuilderContext;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.lang.invoke.MethodHandles;
import java.util.Optional;
import java.util.UUID;

public class JerseyEndPointClassFactory implements ResourceConfigCustomizer {
    @SuppressWarnings("java:S1075")
    private static final String ID_PATH = "/{id}";
    private final MetaDataProvider metaDataProvider;
    private final BeanNamingStrategy beanNamingStrategy;
    private final EndPointRegistry endPointRegistry;
    private final JerseyEndpointConfigProperties configProperties;

    public JerseyEndPointClassFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy
            , EndPointRegistry endPointRegistry
            , JerseyEndpointConfigProperties configProperties) {

        this.metaDataProvider = metaDataProvider;
        this.beanNamingStrategy = beanNamingStrategy;
        this.endPointRegistry = endPointRegistry;
        this.configProperties = configProperties;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger( JerseyEndPointClassFactory.class);

    private static       MethodHandles.Lookup prvlookup;

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        JerseyEndPointClassFactory.class.getModule().addReads( JerseyEndPointClassFactory.class.getModule());
        try
        {
            prvlookup = MethodHandles.privateLookupIn( JerseyEndPointClassFactory.class, lookup);
        }
        catch( IllegalAccessException e ) {
            LOGGER.error( e.getMessage(), e );
        }
    }

    private void initializeEndpoints(ResourceConfig config) {
        for (Class<? extends BusinessObject> aClass : metaDataProvider.getEntityTypes()) {
            Optional<Class<?>> optionalClass = buildEndpointClass(aClass);
            if (optionalClass.isPresent()) {
                var object = optionalClass.get();
                if(object instanceof Class)
                    config.register(object);
            }
        }
    }

    protected Optional<Class<?>> buildEndpointClass(Class<? extends BusinessObject> typeClass) {
        String className = buildEndPointClassName(typeClass);

        DynamicSubClassBuilderContext dynamicSubClassBuilderContext = DynamicClassBuilder.createClass(className)
                .subClass(resolveEndPointBaseClass());

        dynamicSubClassBuilderContext
                    .addAnnotation(jakarta.ws.rs.Path.class)
                        .value(buildEndpointPath(typeClass));

        dynamicSubClassBuilderContext.proxySuperMethod("create").name("createMethod")
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
                    .param(resolveRepresentations(typeClass))
                        .addAnnotation(NotNull.class);

        dynamicSubClassBuilderContext .proxySuperMethod("update").name("updateMethod")
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
                    .param(resolveRepresentations(typeClass))
                        .addAnnotation(NotNull.class);

        dynamicSubClassBuilderContext.proxySuperMethod("delete").name("deleteMethod")
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
                            .value("id");

        DynamicProxyMethodBuilderContext searchMethod = dynamicSubClassBuilderContext.proxySuperMethod("search");

        searchMethod.name("searchMethod")
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
                        .value(new String[] {MediaType.APPLICATION_JSON});

        searchMethod.param(Query.class)
                        .addAnnotation(NotNull.class);

        dynamicSubClassBuilderContext.proxySuperMethod("list").name("listMethod")
                    .addAnnotation(GET.class)
                        .and()
                    .addAnnotation(Override.class)
                        .and()
                    .addAnnotation(Produces.class)
                        .value(new String[] {MediaType.APPLICATION_JSON})
                        .and()
                    .addAnnotation(Consumes.class)
                        .value(new String[] {MediaType.APPLICATION_JSON});

        dynamicSubClassBuilderContext.proxySuperMethod("read").name("readMethod")
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
                            .value("id");

        buildEndpointConstructorMethod(typeClass, dynamicSubClassBuilderContext);

        return dynamicSubClassBuilderContext.build(getPrvlookup()).map(endpointClass-> {
            endPointRegistry.addEntityEndPointMap(typeClass.getSimpleName(), endpointClass);
            return endpointClass;
        });
    }

    protected MethodHandles.Lookup getPrvlookup() {
        return prvlookup;
    }

    protected Class<?> resolveEndPointBaseClass() {
        return BaseBOEndpoint.class;
    }

    protected void buildEndpointConstructorMethod(Class<? extends BusinessObject> typeClass, DynamicSubClassBuilderContext dynamicSubClassBuilderContext) {
        DynamicClassConstructorBuilderContext<DynamicClassBuilderContext> constructorBuilderContext = dynamicSubClassBuilderContext.addConstructor();
        constructorBuilderContext.addAnnotation(Autowired.class);
        constructorBuilderContext.superParam(BOManager.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateManagerId(typeClass));

        constructorBuilderContext.superParam(ResponseEntityBuilderFactory.class)
                        .addAnnotation(Qualifier.class)
                            .value("jerseyResponseEntityBuilderFactory");
    }

    protected Class<?> resolveRepresentations(Class<? extends BusinessObject> typeClass) {
        return typeClass;
    }

    protected String buildEndpointPath(Class<? extends BusinessObject> typeClass) {
        return configProperties.getApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase();
    }

    protected String buildEndPointClassName(Class<? extends BusinessObject> typeClass) {
        return JerseyEndPointClassFactory.class.getPackageName() + "." + typeClass.getSimpleName() + "EndPoint";
    }

    @Override
    public void customize(ResourceConfig config) {
        initializeEndpoints(config);
    }
}

