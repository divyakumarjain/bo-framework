package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.HATEOASMapper;
import org.divy.common.bo.rest.LinkBuilderFactory;
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

    @Autowired
    public JerseyHyperMediaEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy) {

        this.metaDataProvider = metaDataProvider;
        this.beanNamingStrategy = beanNamingStrategy;
    }

    private void initializeHypermediaEndpoints(ResourceConfig config) {
        metaDataProvider.getEntityTypes()
                .stream()
                .map(typeClass -> buildHypermediaEndPointClass(typeClass
                        , "hypermedia/" + typeClass.getSimpleName().toLowerCase()
                        , DefaultHATEOASJerseyEndpoint.class
                        , typeClass.getSimpleName() + "HyperMediaEndPoint"))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(config::register);
    }

    private Optional<Class<?>> buildHypermediaEndPointClass(Class<? extends IBusinessObject> typeClass
            , String value
            , Class<?> implementationClass, String className) {

        return DynamicClassBuilder.createClass(className)
                .subClass(implementationClass)
                    .addAnnotation(javax.ws.rs.Path.class)
                        .value(value)
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
                    .superParam(LinkBuilderFactory.class)
                        .and()
                    .superParam(HATEOASMapper.class).addAnnotation(Qualifier.class)
                        .value(beanNamingStrategy.calculateHATEOASMapperId(typeClass))
                        .build();
    }


    @Override
    public void customize(ResourceConfig config) {
        this.initializeHypermediaEndpoints(config);
    }
}

