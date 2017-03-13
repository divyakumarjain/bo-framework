package org.divy.common.bo.endpoint.json.test;

import org.divy.common.bo.AbstractBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.rest.LinkBuilderFactory;

import javax.inject.Inject;
import javax.persistence.*;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

/**
 *
 *
 */
@Path("/mock")
public class MockBoEndpoint extends AbstractBOEndpoint<MockBoEndpoint.MockEntity, UUID> {
    @Inject
    public MockBoEndpoint(IBOManager<MockEntity, UUID> manager, LinkBuilderFactory linkBuilderFactory) {
        super(manager, linkBuilderFactory);
    }

    @Entity
    @XmlRootElement
    static public class MockEntity extends AbstractBusinessObject {

        public MockEntity() {
        }

        public MockEntity(UUID uuid) {
            super(uuid);
        }

        /*
         * (non-Javadoc)
         *
         * @see org.divy.common.bo.IBusinessObject#getIdentity()
         */
        public UUID identity() {
            return getUuid();
        }

        private String name;

        private int integerAttribute;

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


        @OneToMany(cascade = { CascadeType.ALL}, targetEntity = MockEntity.class)
        @PrimaryKeyJoinColumn
        public List<MockEntity> getChildEntities() {
            return childEntities;
        }

        public void setChildEntities(List<MockEntity> childEntities) {
            this.childEntities = childEntities;
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
}
