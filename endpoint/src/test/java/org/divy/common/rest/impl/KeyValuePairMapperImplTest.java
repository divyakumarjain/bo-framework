package org.divy.common.rest.impl;

import org.divy.common.bo.database.BoEntityMetaDataProvider;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.type.MapperBuilderOptions;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapper;
import org.divy.common.bo.mapper.keyvaluemap.KeyValuePairMapperImpl;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KeyValuePairMapperImplTest {

    BoEntityMetaDataProvider metaDataProvider = new BoEntityMetaDataProvider(Arrays.asList(MockEntity.class));
    private MapperBuilder mockMapperBuilder;

    @Before
    public void setup() {
        mockMapperBuilder = mock(MapperBuilder.class);
        TypeMapperBuilderContext<MockEntity, Map> typeMapperBuilderContext = mock(TypeMapperBuilderContext.class);
        when(mockMapperBuilder
                .mapping(MockEntity.class
                        , Map.class
                        , MapperBuilderOptions.oneWay()))
                .thenReturn(typeMapperBuilderContext);

    }

    @Test
    @Ignore
    public void testHierarchy() {
        final KeyValuePairMapper<MockEntity> keyValuePairMapper
                = new KeyValuePairMapperImpl<>(MockEntity.class
                , mockMapperBuilder
                , metaDataProvider);

        MockEntity businessObject = new MockEntity();
        businessObject.setName("TestEntity");

        MockEntity parentEntity = new MockEntity();
        parentEntity.setName("ParentEntity");
        businessObject.setParentEntity(parentEntity);

        MockEntity childEntity1 = new MockEntity();
        childEntity1.setName("child1");
        childEntity1.setParentEntity(parentEntity);

        MockEntity childEntity2 = new MockEntity();
        childEntity2.setName("child2");
        childEntity2.setParentEntity(parentEntity);

        businessObject.setChildEntities(Arrays.asList(childEntity1, childEntity2));

        Map<String, Object> representation = keyValuePairMapper.createFromBO(businessObject);

        assertThat(representation, notNullValue());

        Assert.assertThat(representation, hasEntry(is("parentEntity"), (Matcher) hasEntry(is("name"), is("ParentEntity"))));

        Assert.assertThat(representation, hasEntry(is("childEntities")
                        , (Matcher)contains(both(hasEntry(is("name"), is("child1")))
                                .and(hasEntry(is("name"), is("child2"))))));
    }
}