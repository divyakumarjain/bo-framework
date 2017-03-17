package org.divy.common.bo.endpoint.json.test;

import org.divy.common.bo.AbstractBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractBOJerseyEndpoint;
import org.divy.common.bo.rest.LinkBuilderFactory;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

/**
 *
 *
 */
@Path("/mock")
public class MockBoJerseyEndpoint extends AbstractBOJerseyEndpoint<MockBoJerseyEndpoint.MockEntity, UUID> {
    @Inject
    public MockBoJerseyEndpoint(IBOManager<MockEntity, UUID> manager, LinkBuilderFactory linkBuilderFactory) {
        super(manager, linkBuilderFactory);
    }

    @XmlRootElement
    public static class MockEntity extends AbstractBusinessObject {

        private String name;

        private int integerAttribute;

        private MockEntity parentEntity;

        private List<MockEntity> childEntities;

        public MockEntity() {
            //Noop Constructor
        }

        public MockEntity(UUID uuid) {
            super(uuid);
        }

        /*
         * (non-Javadoc)
         *
         * @see org.divy.common.bo.IBusinessObject#getIdentity()
         */
        @Override
        public UUID identity() {
            return getUuid();
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
