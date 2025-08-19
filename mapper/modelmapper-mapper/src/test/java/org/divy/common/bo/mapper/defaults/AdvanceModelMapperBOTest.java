package org.divy.common.bo.mapper.defaults;

import org.divy.common.bo.mapper.BOMapper;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.modelmapper.builder.ModelMapperBuilder;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.OffsetDateTime;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AdvanceModelMapperBOTest {

    BOMapper<MockEntity, Map> underTest;

    @BeforeEach
    void setup() {
        ModelMapperBuilder builder = new ModelMapperBuilder();

        underTest = builder.mapping(MockEntity.class, Map.class)
                .field("parentEntity")
                    .option(FieldMapperOptions.hintA(MockEntity.class))
                    .option(FieldMapperOptions.hintB(Map.class))
                    .and()
                .field("childEntities")
                    .option(FieldMapperOptions.hintA(List.class))
                    .option(FieldMapperOptions.hintB(List.class))
                    .option(FieldMapperOptions.nestedHintA(Map.class))
                    .option(FieldMapperOptions.nestedHintB(Map.class))
                .build();
    }

    @Test
    void testMapping() {
        MockEntity businessObject = new MockEntity();
        businessObject.setName("TestEntity");

        MockEntity parentEntity = new MockEntity();
        parentEntity.setName("ParentEntity");
        businessObject.setParentEntity(parentEntity);
        parentEntity.setChildEntities(Collections.singletonList(businessObject));

        MockEntity childEntity1 = new MockEntity();
        childEntity1.setName("child1");
        childEntity1.setParentEntity(businessObject);

        MockEntity childEntity2 = new MockEntity();
        childEntity2.setName("child2");
        childEntity2.setParentEntity(businessObject);

        businessObject.setChildEntities(Arrays.asList(childEntity1, childEntity2));

        Map<String, Object> representation = underTest.createFromBO(businessObject);

        assertThat(representation, notNullValue());

        assertThat(representation, hasEntry(is("parentEntity"), both(isA(Map.class)).and((Matcher) hasEntry(is("name"), is("ParentEntity")))));

        assertThat(representation, hasEntry(is("childEntities")
                , (Matcher)hasItem(hasEntry(is("name"), is("child1")))));
        assertThat(representation, hasEntry(is("childEntities")
                , (Matcher)hasItem(hasEntry(is("name"), is("child2")))));
    }

    @Test
    void testOffsetDateTime() {
        MockEntity businessObject = new MockEntity();
        businessObject.setName("TestEntity");
        final OffsetDateTime dob = OffsetDateTime.now();
        businessObject.setDob(dob);

        Map<String, Object> representation = underTest.createFromBO(businessObject);

        assertThat(representation, notNullValue());
        assertThat(representation, hasEntry(is("dob"), is(dob)));
    }

    @Test
    void testModelMapperList() {
        ModelMapper mapper = new ModelMapper();

        // For simple mapping without complex field mapping, ModelMapper works automatically
        BeanWithList srcBean = new BeanWithList();

        final BeanWithMap srcChild = new BeanWithMap();
        srcChild.setId("child");
        srcBean.setSrcBeanList(Collections.singletonList(srcChild));
        srcBean.setId("parentBean");

        final BeanWithList destList = mapper.map(srcBean, BeanWithList.class);

        assertThat(destList, notNullValue());
        assertThat(destList.getId(), is("parentBean"));
        // ModelMapper automatically maps srcBeanList to srcBeanList (same field name)
        assertThat(destList.getSrcBeanList(), notNullValue());
        assertThat(destList.getSrcBeanList().size(), is(1));
    }

    @Test
    void testModelMapperMap() {
        ModelMapper mapper = new ModelMapper();

        BeanWithMap srcBean = new BeanWithMap();
        srcBean.setId("parentBean");

        final BeanWithMap srcChild = new BeanWithMap();
        srcChild.setId("child");

        final HashMap<String, BeanWithMap> srcChildList = new HashMap<>();
        srcChildList.put(srcChild.getId(), srcChild);
        srcBean.setSrcBeanList(srcChildList);

        final BeanWithMap destList = mapper.map(srcBean, BeanWithMap.class);

        assertThat(destList, notNullValue());
        assertThat(destList.getId(), is("parentBean"));
        // ModelMapper automatically maps srcBeanList to srcBeanList
        assertThat(destList.getSrcBeanList(), notNullValue());
        assertThat(destList.getSrcBeanList().size(), is(1));
    }

    public static class BeanWithList {
        List<BeanWithMap> srcBeanList;
        List<BeanWithMap> destBeanList;
        String id;

        public List<BeanWithMap> getSrcBeanList() {
            return srcBeanList;
        }

        public void setSrcBeanList(List<BeanWithMap> srcBeanList) {
            this.srcBeanList = srcBeanList;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<BeanWithMap> getDestBeanList() {
            return destBeanList;
        }

        public void setDestBeanList(List<BeanWithMap> destBeanList) {
            this.destBeanList = destBeanList;
        }
    }

    public static class BeanWithMap {
        Map<String, BeanWithMap> srcBeanList;
        Map<String, BeanWithMap> destBeanList;
        Map<String, Object> data;
        String id;

        public BeanWithMap() {
            srcBeanList = new HashMap<>();
            destBeanList = new HashMap<>();
        }

        public Map<String, BeanWithMap> getSrcBeanList() {
            return srcBeanList;
        }

        public void setSrcBeanList(Map<String, BeanWithMap> srcBeanList) {
            this.srcBeanList = srcBeanList;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Map<String, BeanWithMap> getDestBeanList() {
            return destBeanList;
        }

        public void setDestBeanList(Map<String, BeanWithMap> destBeanList) {
            this.destBeanList = destBeanList;
        }
    }
}
