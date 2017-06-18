package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.dynamic.clazz.common.EnumArrayAnnotationParam;
import org.divy.common.bo.dynamic.clazz.common.StringArrayAnnotationParam;
import org.divy.common.bo.endpoint.hypermedia.SpringMVCRepresentation;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.HyperMediaMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.factory.DefaultHATEOASMVCEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


public class SpringMVCHyperMediaEndPointFactory  extends SpringMVCEndPointFactory {

    public SpringMVCHyperMediaEndPointFactory(BeanNamingStrategy beanNamingStrategy
            , EndPointRegistry endPointRegistry
            , SpringMVCEndpointConfigProperties configProperties) {

        super(beanNamingStrategy, endPointRegistry, configProperties);

    }

    @Override
    public Optional<Class> buildEndpointClass(Class<? extends BusinessObject> typeClass) {

        return DynamicClassBuilder.createClass(typeClass.getSimpleName() + "HyperMediaEndPoint")
                .subClass(DefaultHATEOASMVCEndpoint.class)
                    .addAnnotation(RestController.class)
                        .and()
                    .addAnnotation(RequestMapping.class)
                        .value(new StringArrayAnnotationParam("/" + configProperties.getHateoasApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase()))
                        .and()
                .proxySuperMethod("create").name("createMethod")
                    .addAnnotation(RequestMapping.class)
                        .param("method", new EnumArrayAnnotationParam(RequestMethod.POST))
                        .and()
                    .param()
                        .type(SpringMVCRepresentation.class)
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
                            .type(SpringMVCRepresentation.class)
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
                    .superValue(typeClass)
                        .and()
                    .superParam(BOManager.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateManagerId(typeClass))
                            .and()
                        .and()
                    .superParam(ResponseEntityBuilderFactory.class)
                        .addAnnotation(Qualifier.class)
                            .value("mvcResponseEntityBuilderHyperMediaFactory")
                            .and()
                        .and()
                    .superParam(HyperMediaMapper.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateHyperMediaMapperId(typeClass))
                    .build()
        .map(endpointClass-> {
            endPointRegistry.addEntityEndPointMap(typeClass.getSimpleName(), endpointClass);
            return endpointClass;
        });
    }
}

