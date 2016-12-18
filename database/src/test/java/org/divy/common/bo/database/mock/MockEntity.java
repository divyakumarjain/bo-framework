package org.divy.common.bo.database.mock;

import java.util.List;

import java.util.UUID;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.IBusinessObject;
import org.hibernate.annotations.GenericGenerator;

@Entity
@XmlRootElement
public class MockEntity extends AbstractBusinessObject {

    /*
     * (non-Javadoc)
     *
     * @see org.divy.common.bo.IBusinessObject#getIdentity()
     */
    public UUID identity() {
        return getUuid();
    }

    /**
     * update object with the copy
     *
     * @param entity
     */
    @Override
    public void update(IBusinessObject<UUID> entity) {
        if(entity instanceof MockEntity) {
            this.setName(((MockEntity) entity).getName());
            this.setParentEntity(((MockEntity) entity).getParentEntity());
            this.setChildEntities(((MockEntity) entity).getChildEntities());
        } else {
            throw new IllegalArgumentException("Expecting instance of Mock");
        }
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


    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.REMOVE }, targetEntity = MockEntity.class)
    @PrimaryKeyJoinColumn
    public List<MockEntity> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(List<MockEntity> childEntities) {
        this.childEntities = childEntities;
    }

    public void setIdentity(UUID identity) {
        setUuid(identity);
    }

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = MockEntity.class)
    @PrimaryKeyJoinColumn
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((childEntities == null) ? 0 : childEntities.hashCode());
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MockEntity other = (MockEntity) obj;
        if (childEntities == null) {
            if (other.childEntities != null)
                return false;
        } else if (!childEntities.equals(other.childEntities))
            return false;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

    public int getIntegerAttribute() {
        return integerAttribute;
    }

    public void setIntegerAttribute(int integerAttribute) {
        this.integerAttribute = integerAttribute;
    }
}
