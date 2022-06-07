package org.divy.common.bo.spring.jersey.rest.hatoas;

import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.DynamicSubClassBuilderContext;
import org.divy.common.bo.dynamic.clazz.member.constructor.DynamicClassConstructorBuilderContext;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.jersey.rest.hatoas.JerseyRepresentation;
import org.divy.common.bo.jersey.rest.response.JerseyResponseEntityBuilderFactory;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.repository.BusinessObject;
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


public class JerseyHATOASEndPointFactory extends JerseyEndPointClassFactory implements ResourceConfigCustomizer {

    private static final Logger                         LOGGER  = LoggerFactory.getLogger( JerseyHATOASEndPointFactory.class);

    private static final String                         ID_PATH = "/{id}";

    private final        MetaDataProvider               metaDataProvider;
    private final        BeanNamingStrategy             beanNamingStrategy;
    private final        JerseyEndpointConfigProperties configProperties;

    public JerseyHATOASEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy
            , EndPointRegistry endPointRegistry
            , JerseyEndpointConfigProperties configProperties) {
        super(metaDataProvider,beanNamingStrategy,endPointRegistry,configProperties);
        this.metaDataProvider = metaDataProvider;
        this.beanNamingStrategy = beanNamingStrategy;
        this.configProperties = configProperties;
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

    private void initializeHATOASEndpoints(ResourceConfig config) {
        List<Class<? extends BusinessObject>> entityTypes = metaDataProvider.getEntityTypes();
        for( int i = 0, entityTypesSize = entityTypes.size(); i < entityTypesSize; i++ )
        {
            var entityType = entityTypes.get( i );
            var buildEndpointClass = buildEndpointClass( entityType );
            if( buildEndpointClass.isPresent() )
            {
                var endpointClass = buildEndpointClass.get();
                if( endpointClass instanceof Class )
                    config.register(endpointClass );
            }
        }
    }

    @Override
    protected void buildEndpointConstructorMethod(Class<? extends BusinessObject> typeClass, DynamicSubClassBuilderContext dynamicSubClassBuilderContext) {
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
    }

    @Override
    protected Class<?> resolveEndPointBaseClass() {
        return DefaultHATEOASJerseyEndpoint.class;
    }

    @Override
    protected Class<?> resolveRepresentations(Class<? extends BusinessObject> typeClass) {
        return JerseyRepresentation.class;
    }
    @Override
    protected String buildEndPointClassName(Class<? extends BusinessObject> typeClass) {
        return JerseyHATOASEndPointFactory.class.getPackageName() + "." + typeClass.getSimpleName() + "HyperMediaEndPoint";
    }


    @Override
    protected String buildEndpointPath(Class<? extends BusinessObject> typeClass){
        return configProperties.getHateoasApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase();
    }

    @Override
    public void customize(ResourceConfig config) {
        this.initializeHATOASEndpoints(config);
    }

    @Override
    protected MethodHandles.Lookup getPrvlookup() {
        return prvlookup;
    }
}

