package org.divy.common.bo.mapper.defaults;

import java.time.OffsetDateTime;
import java.util.List;

public class MockEntity {
    private String name;
    private OffsetDateTime dob;
    private MockEntity parentEntity;
    private List<MockEntity> childEntities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getDob() {
        return dob;
    }

    public void setDob(OffsetDateTime dob) {
        this.dob = dob;
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
}
