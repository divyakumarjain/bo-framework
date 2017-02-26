package org.divy.common.rest.impl;

import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.rest.LinkBuilderFactory;
import org.divy.common.rest.impl.mock.MockRepresentation;
import org.junit.Test;

public class AbstractHATEOASMapperTest {

    @Test
    public void testCreateRepresentationFromBO() {

    }

    @Test
    public void testCreateRepresentationFromBOList() {

    }


    @Test
    public void testCreateBOFromRepresentation() {

    }

    @Test
    public void createBOFromRepresentation() {

    }
}

class TestHATEOASMapperMock extends AbstractHATEOASMapper<MockEntity, MockRepresentation> {

    TestHATEOASMapperMock(KeyValuePairMapper keyValuePairMapper, LinkBuilderFactory linkBuilderFactory, MetaDataProvider metaDataProvider) {
        super(MockEntity.class
                , MockRepresentation.class
                , keyValuePairMapper
                , linkBuilderFactory, metaDataProvider);
    }

    @Override
    protected void doFillLinks(MockRepresentation representation, MockEntity businessObject) {

    }

    @Override
    protected void doFillAssociations(MockRepresentation representation, MockEntity businessObject) {

    }

    @Override
    protected void doReadLinks(MockRepresentation representation, MockEntity businessObject) {

    }

    @Override
    protected void doReadAssociations(MockRepresentation representation, MockEntity businessObject) {

    }
}