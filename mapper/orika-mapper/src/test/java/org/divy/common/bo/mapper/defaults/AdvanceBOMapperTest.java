package org.divy.common.bo.mapper.defaults;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.builder.options.field.FieldMapperOptions;
import org.divy.common.bo.mapper.orika.builder.OrikaMapperBuilder;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AdvanceBOMapperTest {

    IBOMapper<MockEntity, Map> underTest;

    @Before
    public void setup() {

        OrikaMapperBuilder builder = new OrikaMapperBuilder();

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
    public void testMapping() {

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

        Assert.assertThat(representation, hasEntry(is("parentEntity"), both(isA(Map.class)).and((Matcher) hasEntry(is("name"), is("ParentEntity")))));

        Assert.assertThat(representation, hasEntry(is("childEntities")
                , (Matcher)hasItem(hasEntry(is("name"), is("child1")))));
        Assert.assertThat(representation, hasEntry(is("childEntities")
                , (Matcher)hasItem(hasEntry(is("name"), is("child2")))));
    }

    @Test
    public void testLocalDateTime() {

        MockEntity businessObject = new MockEntity();
        businessObject.setName("TestEntity");
        final LocalDateTime dob = LocalDateTime.now();
        businessObject.setDob(dob);

        Map<String, Object> representation = underTest.createFromBO(businessObject);

        assertThat(representation, notNullValue());

        Assert.assertThat(representation, hasEntry(is("dob"), is(dob)));

    }

    @Test
    public void testOrikaList() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        final ClassMapBuilder<BeanWithList, BeanWithList> beanMapping = mapperFactory.classMap(BeanWithList.class, BeanWithList.class);

        
        beanMapping.fieldMap("srcBeanList", "srcBeanList", false)
            .exclude().add();
        beanMapping.fieldMap("destBeanList", "destBeanList", false)
            .exclude().add();
        beanMapping.fieldMap("srcBeanList", "destBeanList", false)
            .add();
        beanMapping
                .byDefault()
                .register();

        final MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        BeanWithList srcBean = new BeanWithList();

        final BeanWithMap srcChild = new BeanWithMap();
        srcChild.setId("child");
        srcBean.setSrcBeanList(Collections.singletonList(srcChild));
        srcBean.setId("parentBean");

        final BeanWithList destList = mapperFacade.map(srcBean, BeanWithList.class);
        
        Assert.assertThat(destList, both(notNullValue()).and(hasProperty("destBeanList", contains(both(isA(BeanWithMap.class)).and(hasProperty("id",is("child")))))));

    }

    @Test
    public void testOrikaMap() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .compilerStrategy(new EclipseJdtCompilerStrategy())
                .build();

        final ClassMapBuilder<BeanWithMap, BeanWithMap> beanMapping = mapperFactory.classMap(BeanWithMap.class, BeanWithMap.class);

        beanMapping.fieldMap("srcBeanList", "destBeanList", false).add();
        beanMapping.fieldMap("srcBeanList", "srcBeanList", false)
            .exclude().add();
        beanMapping.fieldMap("destBeanList", "destBeanList", false)
            .exclude().add();


        beanMapping
                .byDefault()
                .register();

        final MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        BeanWithMap srcBean = new BeanWithMap();
        srcBean.setId("parentBean");

        final BeanWithMap srcChild = new BeanWithMap();
        srcChild.setId("child");

        final HashMap<String, BeanWithMap> srcChildList = new HashMap<>();
        srcChildList.put(srcChild.getId(), srcChild);
        srcBean.setSrcBeanList(srcChildList);

        final BeanWithMap destList = mapperFacade.map(srcBean, BeanWithMap.class);

        Assert.assertThat(destList, both(notNullValue()).and(hasProperty("destBeanList", hasEntry(is("child"), hasProperty("id",is("child"))))));
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