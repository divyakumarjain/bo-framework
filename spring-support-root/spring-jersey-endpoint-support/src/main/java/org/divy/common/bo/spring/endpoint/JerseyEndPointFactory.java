package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.factory.DefaultEndpoint;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilderFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;


public class JerseyEndPointFactory extends ResourceConfig {

    private final MetaDataProvider metaDataProvider;
    private final BeanNamingStrategy beanNamingStrategy;

    @Autowired
    public JerseyEndPointFactory(MetaDataProvider metaDataProvider
            , BeanNamingStrategy beanNamingStrategy) {

        this.metaDataProvider = metaDataProvider;
        this.beanNamingStrategy = beanNamingStrategy;
        initialize();
    }

    private void initialize() {
        metaDataProvider.getEntityTypes()
                .stream()
                .map(this::buildEndPointClass)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(this::register);
    }

    private Optional<Class<?>> buildEndPointClass(Class<? extends IBusinessObject> typeClass) {
        return DynamicClassBuilder.createClass(typeClass.getSimpleName()+"EndPoint")
                .subClass(DefaultEndpoint.class)
            .addAnnotation(javax.ws.rs.Path.class)
                .value(typeClass.getSimpleName().toLowerCase())
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
                    .superParam(HATEOASMapper.class).addAnnotation(Qualifier.class).value(beanNamingStrategy.calculateHATEOASMapperId(typeClass))
            .build();
    }

}

