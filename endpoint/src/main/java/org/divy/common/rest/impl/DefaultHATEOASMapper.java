package org.divy.common.rest.impl;

import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.database.MetaDataProvider;
import org.divy.common.rest.LinkBuilderFactory;



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
        representation.addLink(getLinkBuilderFactory().newBuilder()
                .path(metaDataProvider.getEndpointClass(metaDataProvider))
                .path(metaDataProvider.getEndpointClass(metaDataProvider), "read")
                .buildLink("self", representation.getId()));
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
