package org.divy.common.rest.impl;

import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.divy.common.bo.rest.AbstractHATEOASMapper;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.divy.common.rest.impl.mock.MockEntity;
import org.divy.common.rest.impl.mock.MockRepresentation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class AbstractHATEOASMapperTest {

    private final KeyValuePairMapper<MockEntity> mockKeyValuePairMapper = mock(KeyValuePairMapperImpl.class);
    private TestHATEOASMapperMock underTest;

    @Before
    public void setup() {
        underTest = new TestHATEOASMapperMock(mockKeyValuePairMapper, mock(LinkBuilderFactory.class), mock(MetaDataProvider.class));
    }

    @Test
    public void testCreateRepresentationFromBO() {
        MockEntity businessObject = new MockEntity();
        MockRepresentation representationFromBO = underTest.createRepresentationFromBO(businessObject);
        assertThat(representationFromBO, notNullValue());
    }


    @Test
    public void testCreateBOFromRepresentation() {

        MockRepresentation representation = new MockRepresentation();
        representation.setData(new HashMap<>());
        doReturn(new MockEntity()).when(mockKeyValuePairMapper).createBO(representation.getData());
        MockEntity bo = underTest.createBOFromRepresentation(representation);
        assertThat(bo, notNullValue());
    }

    @Test
    public void testCreateRepresentationFromBOList() {
        MockEntity businessObject = new MockEntity();

        Collection<MockEntity> businessObjectList = new ArrayList<>();
        businessObjectList.add(businessObject);

        Collection<MockRepresentation> representationFromBOs = underTest.createRepresentationFromBO(businessObjectList);
        assertThat(representationFromBOs, notNullValue());
        assertThat(representationFromBOs, hasSize(1));
    }


    @Test
    public void testCreateBOFromRepresentationList() {

        MockRepresentation representation = new MockRepresentation();
        representation.setData(new HashMap<>());

        List<MockRepresentation> representationList = new ArrayList<>();
        representationList.add(representation);

        doReturn(new MockEntity()).when(mockKeyValuePairMapper).createBO(representation.getData());

        Collection<MockEntity> bos = underTest.createBOFromRepresentation(representationList);
        assertThat(bos, notNullValue());
        assertThat(bos, hasSize(1));
    }


}

class TestHATEOASMapperMock extends AbstractHATEOASMapper<MockEntity, MockRepresentation> {

    TestHATEOASMapperMock(KeyValuePairMapper<MockEntity> keyValuePairMapper, LinkBuilderFactory linkBuilderFactory, MetaDataProvider metaDataProvider) {
        super(MockRepresentation.class
                , keyValuePairMapper
                , linkBuilderFactory
                , metaDataProvider);
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