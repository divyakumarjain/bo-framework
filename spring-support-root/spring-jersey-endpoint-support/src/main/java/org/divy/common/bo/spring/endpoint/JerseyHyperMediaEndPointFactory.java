package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.HATEOASMapper;
import org.divy.common.bo.rest.builder.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.factory.DefaultHATEOASJerseyEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;

import java.util.Optional;


public class JerseyHyperMediaEndPointFactory implements ResourceConfigCustomizer {

    private final MetaDataProvider metaDataProvider;
    private final BeanNamingStrategy beanNamingStrategy;
    private final EndPointRegistry endPointRegistry;
    private final JerseyEndpointConfigProperties configProperties;

    @Autowired
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

    private Optional<Class> buildEndpointClass(Class<? extends IBusinessObject> typeClass) {

        return DynamicClassBuilder.createClass(typeClass.getSimpleName() + "HyperMediaEndPoint")
                .subClass(DefaultHATEOASJerseyEndpoint.class)
                    .addAnnotation(javax.ws.rs.Path.class)
                        .value(configProperties.getHateoasApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase())
                        .and()
                .addConstructor()
                    .addAnnotation(Autowired.class)
                        .and()
                    .superValue(typeClass)
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
                    .superParam(HATEOASMapper.class).addAnnotation(Qualifier.class)
                        .value(beanNamingStrategy.calculateHATEOASMapperId(typeClass))
                        .build()
        .map(endpointClass-> {
            endPointRegistry.addEntityEndPointMap(typeClass, endpointClass);
            return endpointClass;
        });
    }


    @Override
    public void customize(ResourceConfig config) {
        this.initializeHypermediaEndpoints(config);
    }
}

