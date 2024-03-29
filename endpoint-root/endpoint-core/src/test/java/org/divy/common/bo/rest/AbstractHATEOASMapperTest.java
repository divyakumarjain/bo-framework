package org.divy.common.bo.rest;

import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class AbstractHATEOASMapperTest {

    private final KeyValuePairMapper<MockEntity> mockKeyValuePairMapper = mock(KeyValuePairMapperImpl.class);
    private       TestHATOASMapperMock           underTest;

    @BeforeEach
    void setup() {
        underTest = new TestHATOASMapperMock(mockKeyValuePairMapper, mock(LinkBuilderFactory.class), mock(MetaDataProvider.class));
    }

    @Test
    void testCreateRepresentationFromBO() {
        MockEntity businessObject = new MockEntity();
        MockRepresentation representationFromBO = underTest.createFromBO(businessObject);
        assertThat(representationFromBO, notNullValue());
    }


    @Test
    void testCreateBOFromRepresentation() {

        MockRepresentation representation = new MockRepresentation();
        representation.setData(new HashMap<>());
        doReturn(new MockEntity()).when(mockKeyValuePairMapper).createBO(representation.getData());
        MockEntity bo = underTest.createBO(representation);
        assertThat(bo, notNullValue());
    }

    @Test
    void testCreateRepresentationFromBOList() {
        MockEntity businessObject = new MockEntity();

        Collection<MockEntity> businessObjectList = new ArrayList<>();
        businessObjectList.add(businessObject);

        Collection<MockRepresentation> representationFromBOs = underTest.createFromBO(businessObjectList);
        assertThat(representationFromBOs, notNullValue());
        assertThat(representationFromBOs, hasSize(1));
    }


    @Test
    void testCreateBOFromRepresentationList() {

        MockRepresentation representation = new MockRepresentation();
        representation.setData(new HashMap<>());

        List<MockRepresentation> representationList = new ArrayList<>();
        representationList.add(representation);

        doReturn(new MockEntity()).when(mockKeyValuePairMapper).createBO(representation.getData());

        Collection<MockEntity> bos = underTest.createBO(representationList);
        assertThat(bos, notNullValue());
        assertThat(bos, hasSize(1));
    }


}

class TestHATOASMapperMock extends AbstractHATOASMapper<MockEntity, UUID, MockRepresentation, Link>
{

    TestHATOASMapperMock(KeyValuePairMapper<MockEntity> keyValuePairMapper, LinkBuilderFactory linkBuilderFactory, MetaDataProvider metaDataProvider) {
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
