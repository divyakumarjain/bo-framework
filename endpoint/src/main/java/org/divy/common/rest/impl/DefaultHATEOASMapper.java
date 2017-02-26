package org.divy.common.rest.impl;

import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.rest.LinkBuilderFactory;

import java.util.Optional;


public class DefaultHATEOASMapper<E extends AbstractBusinessObject>
    extends AbstractHATEOASMapper<E, DefaultRepresentation> {

    public DefaultHATEOASMapper(Class<E> businessObjectType
            , KeyValuePairMapper<E> keyValuePairMapper
            , LinkBuilderFactory linkBuilderFactory
            , MetaDataProvider metaDataProvider) {

        super(businessObjectType
                , DefaultRepresentation.class
                , keyValuePairMapper
                , linkBuilderFactory
                , metaDataProvider);
    }

    @Override
    protected void doFillLinks(DefaultRepresentation representation, E businessObject) {
        final Optional<Class<?>> optionalEndPointClass = getMetaDataProvider().getEndpointClass(getMetaDataProvider());
        optionalEndPointClass.ifPresent(aClass -> representation.addLink(getLinkBuilderFactory().newBuilder()
                .path(aClass)
                .path(aClass, "read")
                .buildLink("self", representation.getId())));

    }

    @Override
    protected void doFillAssociations(DefaultRepresentation representation, E businessObject) {

    }

    @Override
    protected void doReadLinks(DefaultRepresentation representation, E businessObject) {

    }

    @Override
    protected void doReadAssociations(DefaultRepresentation representation, E businessObject) {

    }
}
