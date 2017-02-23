package org.divy.common.bo.database.mock;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import org.divy.common.bo.IBusinessObject;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.IBusinessObject;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@JsonAutoDetect(fieldVisibility=Visibility.NONE,getterVisibility=Visibility.NONE)
public class MockEntity extends AbstractBusinessObject {

    private String name;
    private MockEntity parentEntity;
    // @JsonManagedReference
    private List<MockEntity> childEntities;

    private LocalDateTime dob;

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

    /*
     * (non-Javadoc)
     *
     * @see org.divy.common.bo.IBusinessObject#identity()
     */
    @Override
    public UUID identity() {
        return getUuid();
    }

    @PrimaryKeyJoinColumn
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, targetEntity = MockEntity.class)
    @JsonProperty
    public List<MockEntity> getChildEntities() {

        if(childEntities==null) {
            childEntities = new ArrayList<MockEntity>();
        }
        return childEntities;
    }

    public void setChildEntities(List<MockEntity> childEntities) {
        this.childEntities = childEntities;
    }

    /**
     * @return the parentEntity
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = MockEntity.class, fetch = FetchType.LAZY)
    @ManyToOne
    @PrimaryKeyJoinColumn
    // @JsonBackReference
    @JsonIdentityReference
    @JsonProperty
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

    /**
     * @return the name
     */
    @JsonProperty
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MockEntity that = (MockEntity) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getParentEntity(), that.getParentEntity()) &&
                Objects.equals(getChildEntities(), that.getChildEntities()) &&
                Objects.equals(getDob(), that.getDob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getParentEntity(), getChildEntities(), getDob());
    }
}
