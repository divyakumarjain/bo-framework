package org.divy.common.bo.spring.jersey.rest.hatoas;

import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.DynamicSubClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.constructor.DynamicClassConstructorBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.method.DynamicProxyMethodBuilderContext;
import org.divy.common.bo.jersey.rest.hatoas.JerseyRepresentation;
import org.divy.common.bo.jersey.rest.response.JerseyResponseEntityBuilderFactory;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.HATOASMapper;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.jersey.rest.JerseyEndPointClassFactory;
import org.divy.common.bo.spring.jersey.rest.JerseyEndpointConfigProperties;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class JerseyHATOASEndPointFactory extends JerseyEndPointClassFactory implements ResourceConfigCustomizer {

    private static final Logger                         LOGGER  = LoggerFactory.getLogger( JerseyHATOASEndPointFactory.class);

    private static final String                         ID_PATH = "/{id}";

    private final        MetaDataProvider               metaDataProvider;
    private final        BeanNamingStrategy             beanNamingStrategy;
    private final        EndPointRegistry               endPointRegistry;
    private final        JerseyEndpointConfigProperties configProperties;

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
        List<Class<? extends BusinessObject>> entityTypes = metaDataProvider.getEntityTypes();
        for( int i = 0, entityTypesSize = entityTypes.size(); i < entityTypesSize; i++ )
        {
            var entityType = entityTypes.get( i );
            var buildEndpointClass = buildHATOASEndpointClass( entityType );
            if( buildEndpointClass.isPresent() )
            {
                var endpointClass = buildEndpointClass.get();
                if( endpointClass instanceof Class ) config.register( (Class) endpointClass );
            }
        }
    }

    private static MethodHandles.Lookup prvlookup;

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

    private <E extends BusinessObject<UUID>> Optional<Class<?>> buildHATOASEndpointClass(Class<E> typeClass) {
        String className = JerseyHATOASEndPointFactory.class.getPackageName() + "." + typeClass.getSimpleName() + "HyperMediaEndPoint";
        LOGGER.debug("Building class {}", className   );

        DynamicSubClassBuilderContext dynamicSubClassBuilderContext = DynamicClassBuilder.createClass(className)
                .subClass(DefaultHATEOASJerseyEndpoint.class);

        dynamicSubClassBuilderContext
                    .addAnnotation(jakarta.ws.rs.Path.class)
                        .value(configProperties.getHateoasApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase());

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
                    .param( JerseyRepresentation.class)
                        .addAnnotation(NotNull.class);

        dynamicSubClassBuilderContext.proxySuperMethod("update").name("updateMethod")
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

        searchMethod.param()
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

        DynamicClassConstructorBuilderContext<DynamicClassBuilderContext> constructorMethod = dynamicSubClassBuilderContext.addConstructor();
        constructorMethod
                    .addAnnotation(Autowired.class);

        constructorMethod.superParam(BOManager.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateManagerId(typeClass));

        constructorMethod.superParam( JerseyResponseEntityBuilderFactory.class)
                        .addAnnotation(Qualifier.class)
                            .value("jerseyResponseEntityBuilderHATOASFactory");

        constructorMethod.superParam( HATOASMapper.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateHATOASMapperId(typeClass));

        constructorMethod.superParam(AssociationsHandler.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateAssociationsHandler(typeClass));

        return dynamicSubClassBuilderContext.build( prvlookup ).map(endpointClass-> {
            endPointRegistry.addEntityEndPointMap(typeClass.getSimpleName(), endpointClass);
            return endpointClass;
        });
    }


    @Override
    public void customize(ResourceConfig config) {
        this.initializeHATOASEndpoints(config);
    }
}

