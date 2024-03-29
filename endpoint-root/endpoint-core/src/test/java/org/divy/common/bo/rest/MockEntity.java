package org.divy.common.bo.rest;

import org.divy.common.bo.repository.BusinessObject;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class MockEntity implements BusinessObject<UUID> {

    private OffsetDateTime createTimestamp;
    private OffsetDateTime lastUpdateTimestamp;
    private String type;

    public MockEntity() {
    }

    public MockEntity(UUID uuid) {

        this.uuid = uuid;
    }

    @Override
    public String _type() {
        return type;
    }

    public UUID identity() {
        return getUuid();
    }

    private String name;

    private int integerAttribute;

    private UUID uuid;

    private MockEntity parentEntity;

    private List<MockEntity> childEntities;


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
}
