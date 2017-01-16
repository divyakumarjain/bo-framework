package org.divy.common.bo.mapper.defaults;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MockEntity {
    private String name;
    private MockEntity parentEntity;
    private List<MockEntity> childEntities;
    private UUID id;

    public MockEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MockEntity getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(MockEntity parentEntity) {
        this.parentEntity = parentEntity;
    }

    public List<MockEntity> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(List<MockEntity> childEntities) {
        this.childEntities = childEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockEntity that = (MockEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(parentEntity, that.parentEntity) &&
                Objects.equals(childEntities, that.childEntities) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parentEntity, childEntities, id);
    }

    @Override
    public String
    toString() {
        return "MockEntity{" +
                "name='" + name + '\'' +
                ", parentEntity=" + parentEntity +
                ", childEntities=" + childEntities +
                ", id=" + id +
                '}';
    }
}
