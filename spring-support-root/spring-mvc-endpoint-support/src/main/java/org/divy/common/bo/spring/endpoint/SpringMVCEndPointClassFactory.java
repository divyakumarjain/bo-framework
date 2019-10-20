package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.dynamic.clazz.common.EnumArrayAnnotationParam;
import org.divy.common.bo.dynamic.clazz.common.StringArrayAnnotationParam;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SpringMVCEndPointClassFactory {

    protected static final String HTTP_METHOD = "method";
    final BeanNamingStrategy beanNamingStrategy;
    final EndPointRegistry endPointRegistry;
    final SpringMVCEndpointConfigProperties configProperties;

    public SpringMVCEndPointClassFactory(BeanNamingStrategy beanNamingStrategy
            , EndPointRegistry endPointRegistry
            , SpringMVCEndpointConfigProperties configProperties) {

        this.beanNamingStrategy = beanNamingStrategy;
        this.endPointRegistry = endPointRegistry;
        this.configProperties = configProperties;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger( SpringMVCEndPointClassFactory.class);

    private static       MethodHandles.Lookup prvlookup;

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        SpringMVCHATOASEndPointClassFactory.class.getModule().addReads( SpringMVCHATOASEndPointClassFactory.class.getModule());
        try
        {
            prvlookup = MethodHandles.privateLookupIn( SpringMVCHATOASEndPointClassFactory.class, lookup);
        }
        catch( IllegalAccessException e ) {
            LOGGER.error( e.getMessage(), e );
        }
    }

    public Optional<Map<String,Class<?>>> buildEndpointClass(Class<? extends BusinessObject> typeClass) {
        String url = "/" + configProperties.getApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase();

        Map<String, Class<?>> result = new HashMap<>( 1 );
        Optional<Class<?>> aClass = buildClass( typeClass, url );
        aClass.ifPresent( value -> result.put( url, value ) );

        return Optional.of( result );
    }

    private Optional<Class<?>> buildClass( Class<? extends BusinessObject> typeClass, String url ) {
        return DynamicClassBuilder.createClass( SpringMVCHATOASEndPointClassFactory.class.getPackageName() + "." + typeClass.getSimpleName() + "EndPoint")
            .subClass( BaseBOEndpoint.class)
                .addAnnotation(RestController.class)
                    .and()
                .addAnnotation(RequestMapping.class)
                    .value( new StringArrayAnnotationParam(url) )
                    .and()
            .proxySuperMethod("create").name("createMethod")
                .addAnnotation(RequestMapping.class)
                    .param(HTTP_METHOD, new EnumArrayAnnotationParam(RequestMethod.POST))
                    .and()
                .param()
                    .type(typeClass)
                        .addAnnotation(RequestBody.class)
                        .and()
                    .and()
                .and()
            .proxySuperMethod("update").name("updateMethod")
                .addAnnotation(RequestMapping.class)
                    .param(HTTP_METHOD, new EnumArrayAnnotationParam(RequestMethod.PUT))
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
                    .param(HTTP_METHOD, new EnumArrayAnnotationParam(RequestMethod.DELETE))
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
                    .param(HTTP_METHOD, new EnumArrayAnnotationParam(RequestMethod.POST))
                    .value(new StringArrayAnnotationParam(new String[]{"/search"}))
                    .and()
                .param(Query.class)
                    .addAnnotation(RequestBody.class)
                        .and()
                    .and()
                .and()
            .proxySuperMethod("list").name("listMethod")
                .addAnnotation(RequestMapping.class)
                    .param(HTTP_METHOD, new EnumArrayAnnotationParam(RequestMethod.GET))
                    .and()
                .and()
            .proxySuperMethod("read").name("readMethod")
                .addAnnotation(RequestMapping.class)
                    .param(HTTP_METHOD, new EnumArrayAnnotationParam(RequestMethod.GET))
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
                .superParam(BOManager.class)
                    .addAnnotation(Qualifier.class)
                        .value(beanNamingStrategy.calculateManagerId(typeClass))
                        .and()
                    .and()
                .superParam(ResponseEntityBuilderFactory.class)
                    .addAnnotation(Qualifier.class)
                        .value("mvcResponseEntityBuilderFactory")
                        .and()
                    .and()
            .build( prvlookup );
    }
}
