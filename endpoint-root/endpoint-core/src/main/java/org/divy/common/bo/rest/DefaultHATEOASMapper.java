package org.divy.common.bo.rest;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.metadata.MetaDataProvider;

import java.util.Optional;
import java.util.UUID;


public class DefaultHATEOASMapper<E extends IBusinessObject<UUID>>
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
