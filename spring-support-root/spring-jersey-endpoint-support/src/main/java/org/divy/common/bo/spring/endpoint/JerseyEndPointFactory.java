package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.factory.DefaultJerseyEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;

import javax.inject.Inject;
import java.util.Optional;

//TODO For Endpoint without Hypermedia in jersey the method cannot have generic parameters.
// Thus using DefaultJerseyEndpoint is not working
public class JerseyEndPointFactory implements ResourceConfigCustomizer {

    private final MetaDataProvider metaDataProvider;
    private final BeanNamingStrategy beanNamingStrategy;

    @Autowired
    public JerseyEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy) {

        this.metaDataProvider = metaDataProvider;
        this.beanNamingStrategy = beanNamingStrategy;
    }

    private void initializeEndpoints(ResourceConfig config) {
        metaDataProvider.getEntityTypes()
                .stream()
                .map(typeClass -> buildEndPointClass(typeClass
                        , typeClass.getSimpleName().toLowerCase()
                        , DefaultJerseyEndpoint.class
                        , typeClass.getSimpleName() + "EndPoint"))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(config::register);
    }



    private Optional<Class<?>> buildEndPointClass(Class<? extends IBusinessObject> typeClass
            , String apiPath
            , Class<?> implementationClass
            , String className) {

        return DynamicClassBuilder.createClass(className)
                .subClass(implementationClass)
                    .addAnnotation(javax.ws.rs.Path.class)
                        .value(apiPath)
                        .and()
                .addConstructor()
                    .addAnnotation(Autowired.class)
                        .and()
                    .addAnnotation(Inject.class)
                        .and()
                    .superParam(IBOManager.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateManagerId(typeClass))
                            .and()
                        .and()
                    .superParam(LinkBuilderFactory.class)
                .build();
    }

    @Override
    public void customize(ResourceConfig config) {
        initializeEndpoints(config);
    }
}

