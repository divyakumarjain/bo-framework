package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.dynamic.clazz.common.EnumArrayAnnotationParam;
import org.divy.common.bo.dynamic.clazz.common.StringArrayAnnotationParam;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

public class SpringMVCEndPointFactory {

    final BeanNamingStrategy beanNamingStrategy;
    final EndPointRegistry endPointRegistry;
    final SpringMVCEndpointConfigProperties configProperties;

    public SpringMVCEndPointFactory(BeanNamingStrategy beanNamingStrategy
            , EndPointRegistry endPointRegistry
            , SpringMVCEndpointConfigProperties configProperties) {

        this.beanNamingStrategy = beanNamingStrategy;
        this.endPointRegistry = endPointRegistry;
        this.configProperties = configProperties;
    }

     public Optional<Class> buildEndpointClass(Class<? extends IBusinessObject> typeClass) {
        return DynamicClassBuilder.createClass(typeClass.getSimpleName() + "EndPoint")
                .subClass(BaseBOEndpoint.class)
                    .addAnnotation(RestController.class)
                        .and()
                    .addAnnotation(RequestMapping.class)
                        .value(new StringArrayAnnotationParam("/" +configProperties.getApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase()))
                        .and()
                .proxySuperMethod("create").name("createMethod")
                    .addAnnotation(RequestMapping.class)
                        .param("method", new EnumArrayAnnotationParam(RequestMethod.POST))
                        .and()
                    .param()
                        .type(typeClass)
                            .addAnnotation(RequestBody.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("update").name("updateMethod")
                    .addAnnotation(RequestMapping.class)
                        .param("method", new EnumArrayAnnotationParam(RequestMethod.PUT))
                        .value(new StringArrayAnnotationParam(new String[]{"/{entityId}"}))
                        .and()
                    .param(UUID.class)
                        .addAnnotation(PathVariable.class)
                            .value("entityId")
                            .and()
                        .and()
                    .param()
                        .type(typeClass)
                            .addAnnotation(RequestBody.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("delete").name("deleteMethod")
                    .addAnnotation(RequestMapping.class)
                        .param("method", new EnumArrayAnnotationParam(RequestMethod.DELETE))
                        .value(new StringArrayAnnotationParam(new String[]{"/{id}"}))
                        .and()
                    .param(UUID.class)
                        .addAnnotation(PathVariable.class)
                            .value("id")
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("search").name("searchMethod")
                    .addAnnotation(RequestMapping.class)
                        .param("method", new EnumArrayAnnotationParam(RequestMethod.POST))
                        .value(new StringArrayAnnotationParam(new String[]{"/search"}))
                        .and()
                    .param(Query.class)
                        .addAnnotation(RequestBody.class)
                            .and()
                        .and()
                    .and()
                .proxySuperMethod("list").name("listMethod")
                    .addAnnotation(RequestMapping.class)
                        .param("method", new EnumArrayAnnotationParam(RequestMethod.GET))
                        .and()
                    .and()
                .proxySuperMethod("read").name("readMethod")
                    .addAnnotation(RequestMapping.class)
                        .param("method", new EnumArrayAnnotationParam(RequestMethod.GET))
                        .value(new StringArrayAnnotationParam(new String[]{"/{id}"}))
                        .and()
                    .param(UUID.class)
                        .addAnnotation(PathVariable.class)
                            .value("id")
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
                            .value("mvcResponseEntityBuilderFactory")
                            .and()
                        .and()
                .build()
        .map(endpointClass-> {
            endPointRegistry.addEntityEndPointMap(typeClass.getSimpleName(), endpointClass);
            return endpointClass;
        });
    }
}