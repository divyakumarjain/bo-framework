package org.divy.common.bo.mapper.keyvaluemap;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.mapper.builder.MapperBuilder;
import org.divy.common.bo.mapper.builder.TypeMapperBuilderContext;
import org.divy.common.bo.mapper.builder.options.type.MapperBuilderOptions;
import org.divy.common.bo.metadata.MetaDataProvider;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KeyValuePairMapperImplTest {

    private final MetaDataProvider metaDataProvider = mock(MetaDataProvider.class);
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

    static public class MockEntity implements BusinessObject<UUID> {

        protected OffsetDateTime createTimestamp;
        protected OffsetDateTime lastUpdateTimestamp;
        private String type;
        private String name;
        private int integerAttribute;
        private UUID uuid;
        private MockEntity parentEntity;
        private List<MockEntity> childEntities;
        private MockEmbeddedEntity embeddedEntity;

        public MockEntity() {
        }

        public MockEntity(UUID uuid) {

            this.uuid = uuid;
        }

        /*
         * (non-Javadoc)
         *
         * @see org.divy.common.bo.BusinessObject#getIdentity()
         */
        public UUID identity() {
            return getUuid();
        }

        @Override
        public String _type() {
            return type;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }


        public List<MockEntity> getChildEntities() {
            return childEntities;
        }

        public void setChildEntities(List<MockEntity> childEntities) {
            this.childEntities = childEntities;
        }

        public MockEntity getParentEntity() {
            return parentEntity;
        }

        /**
         * @param parentEntity
         *            the parentEntity to set
         */
        public void setParentEntity(MockEntity parentEntity) {
            this.parentEntity = parentEntity;
        }

        @Override
        public String toString() {
            return "MockEntity [uuid=" + uuid + ", childEntities=" + childEntities
                    + "]";
        }

        public int getIntegerAttribute() {
            return integerAttribute;
        }

        public void setIntegerAttribute(int integerAttribute) {
            this.integerAttribute = integerAttribute;
        }


        public UUID getUuid() {
            return uuid;
        }

        void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

        public OffsetDateTime getCreateTimestamp() {
            return createTimestamp;
        }

        public OffsetDateTime  getLastUpdateTimestamp() {
            return lastUpdateTimestamp;
        }

        void setCreateTimestamp(OffsetDateTime createTimestamp) {
            this.createTimestamp = createTimestamp;
        }

        void setLastUpdateTimestamp(OffsetDateTime lastUpdateTimestamp) {
            this.lastUpdateTimestamp = lastUpdateTimestamp;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MockEntity)) {
                return false;
            }

            MockEntity that = (MockEntity) o;

            return uuid != null ? uuid.equals(that.uuid) : that.uuid == null;
        }

        @Override
        public int hashCode() {
            return uuid != null ? uuid.hashCode() : 0;
        }

        public MockEmbeddedEntity getEmbeddedEntity() {
            return embeddedEntity;
        }

        public void setEmbeddedEntity(MockEmbeddedEntity embeddedEntity) {
            this.embeddedEntity = embeddedEntity;
        }
    }

    static class MockEmbeddedEntity {
        String embeddedValue;

        public String getEmbeddedValue() {
            return embeddedValue;
        }

        public void setEmbeddedValue(String embeddedValue) {
            this.embeddedValue = embeddedValue;
        }
    }
}