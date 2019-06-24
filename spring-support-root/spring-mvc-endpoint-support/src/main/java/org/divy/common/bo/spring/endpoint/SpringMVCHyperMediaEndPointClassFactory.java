package org.divy.common.bo.spring.endpoint;

import org.divy.common.bo.dynamic.clazz.DynamicClassBuilderContext;
import org.divy.common.bo.metadata.FieldMetaData;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.dynamic.clazz.DynamicSubClassBuilderContext;
import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.dynamic.clazz.DynamicClassBuilder;
import org.divy.common.bo.dynamic.clazz.common.EnumArrayAnnotationParam;
import org.divy.common.bo.dynamic.clazz.common.StringArrayAnnotationParam;
import org.divy.common.bo.spring.endpoint.hypermedia.SpringMVCRepresentation;
import org.divy.common.bo.endpoint.hypermedia.association.AssociationsHandler;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.EndPointRegistry;
import org.divy.common.bo.rest.HyperMediaMapper;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.spring.core.factory.BeanNamingStrategy;
import org.divy.common.bo.spring.endpoint.factory.DefaultHATEOASMVCEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class SpringMVCHyperMediaEndPointClassFactory extends SpringMVCEndPointClassFactory
{
    private static final Logger LOGGER = LoggerFactory.getLogger( SpringMVCHyperMediaEndPointClassFactory.class);

    private static MethodHandles.Lookup prvlookup;
    private MetaDataProvider metaDataProvider;

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        SpringMVCHyperMediaEndPointClassFactory.class.getModule().addReads(SpringMVCHyperMediaEndPointClassFactory.class.getModule());
        try
        {
            prvlookup = MethodHandles.privateLookupIn(SpringMVCHyperMediaEndPointClassFactory.class, lookup);
        }
        catch( IllegalAccessException e ) {
            LOGGER.error( e.getMessage(), e );
        }
    }

    public SpringMVCHyperMediaEndPointClassFactory( BeanNamingStrategy beanNamingStrategy
          , EndPointRegistry endPointRegistry
          , MetaDataProvider metaDataProvider,
          SpringMVCEndpointConfigProperties configProperties ) {

        super(beanNamingStrategy, endPointRegistry, configProperties);
        this.metaDataProvider = metaDataProvider;
    }

    @Override
    public Optional<Map<String, Class<?>>> buildEndpointClass(Class<? extends BusinessObject> typeClass) {

        String url = "/" + configProperties.getHateoasApiEndpointPath() + "/" + typeClass.getSimpleName().toLowerCase();

        Map<String, Class<?>> result = new HashMap<>( 1 );

        buildHateOASClass( typeClass, url )
              .ifPresent( value -> result.put( url, value ) );
        return Optional.of( result );
    }

    private Optional<Class<?>> buildHateOASClass( Class<? extends BusinessObject> typeClass, String url ) {
        DynamicClassBuilderContext<DynamicSubClassBuilderContext> dynamicClassBuilderContext =
                DynamicClassBuilder.createClass(SpringMVCHyperMediaEndPointClassFactory.class.getPackageName() + "." + typeClass.getSimpleName() + "HyperMediaEndPoint")
                      .subClass( DefaultHATEOASMVCEndpoint.class);
        dynamicClassBuilderContext
                .addAnnotation(RestController.class)
                        .and()
                    .addAnnotation(RequestMapping.class)
                        .value( new StringArrayAnnotationParam( url ) )
                        .and()
                .proxySuperMethod("create").name("createMethod")
                    .addAnnotation(RequestMapping.class)
                        .param(HTTP_METHOD, new EnumArrayAnnotationParam(RequestMethod.POST))
                        .and()
                    .param()
                        .type(SpringMVCRepresentation.class)
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
                            .type(SpringMVCRepresentation.class)
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
                            .value("mvcResponseEntityBuilderHyperMediaFactory")
                            .and()
                        .and()
                    .superParam(HyperMediaMapper.class)
                        .addAnnotation(Qualifier.class)
                            .value(beanNamingStrategy.calculateHyperMediaMapperId(typeClass))
                            .and()
                        .and()
                    .superParam(AssociationsHandler.class)
                        .addAnnotation(Qualifier.class)
                        .value(beanNamingStrategy.calculateAssociationsHandler(typeClass));

        enrichAssociationMethods(dynamicClassBuilderContext, typeClass);

        return dynamicClassBuilderContext.build( prvlookup );
    }
    private void enrichAssociationMethods( DynamicClassBuilderContext<DynamicSubClassBuilderContext> dynamicClassBuilderContext, Class<? extends BusinessObject> typeClass) {
//        final Map<String, FieldMetaData> childEntities = metaDataProvider.getChildEntities(typeClass);
        //        childEntities.forEach( childEntity -> {
        //
        //            dynamicClassBuilderContext.addMethod()
        //                    .param()
        //
        //                }
        //        );
    }
}

