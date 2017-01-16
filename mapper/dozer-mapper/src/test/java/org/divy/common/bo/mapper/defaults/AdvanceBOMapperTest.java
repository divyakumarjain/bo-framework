package org.divy.common.bo.mapper.defaults;

import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.dozer.builder.DozerMapperBuilder;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.*;

public class AdvanceBOMapperTest {

    IBOMapper<MockEntity, Map> underTest;

    @Before
    public void setup() {

        DozerMapperBuilder builder = new DozerMapperBuilder();

        underTest = builder.mapping(MockEntity.class, Map.class)
                .field("parentEntity")
                    .option(FieldMapperOptions.hintB(Map.class))
                    .and()
                .field("childEntities")
                    .option(FieldMapperOptions.hintB(LinkedHashMap.class))
                .build();

    }

    @Test
    @Ignore
    public void testMapping() {

        MockEntity businessObject = new MockEntity();
        businessObject.setName("TestEntity");

        MockEntity parentEntity = new MockEntity();
        parentEntity.setName("ParentEntity");
        businessObject.setParentEntity(parentEntity);

        MockEntity childEntity1 = new MockEntity();
        childEntity1.setName("child1");
        childEntity1.setParentEntity(businessObject);

        MockEntity childEntity2 = new MockEntity();
        childEntity2.setName("child2");
        childEntity2.setParentEntity(businessObject);

        businessObject.setChildEntities(Arrays.asList(childEntity1, childEntity2));

        Map<String, Object> representation = underTest.createFromBO(businessObject);

        assertThat(representation, notNullValue());

        Assert.assertThat(representation, hasEntry(is("parentEntity"), (Matcher) hasEntry(is("name"), is("ParentEntity"))));

        Assert.assertThat(representation, hasEntry(is("childEntities")
                , (Matcher)contains(both(hasEntry(is("name"), is("child1")))
                        .and(hasEntry(is("name"), is("child2"))))));
    }
}