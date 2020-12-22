package org.divy.common.bo.endpoint.test.json;

import org.divy.common.bo.database.AbstractBusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

@Path("mock")
public class MockBoJerseyEndpoint extends BaseBOEndpoint<MockBoJerseyEndpoint.MockEntity, UUID, Response> {

    MockBoJerseyEndpoint(BOManager<MockEntity, UUID> manager, ResponseEntityBuilderFactory responseEntityBuilderFactory) {
        super(manager, responseEntityBuilderFactory);
    }

    @Consumes({"application/json"})
    @GET
    @Produces({"application/json"})
    @Path("/{id}")
    public Response readMethod(@PathParam("id") UUID id) {
        return super.read(id);
    }

    @Produces({"application/json"})
    @Consumes({"application/json"})
    @Path("/{id}")
    @Override
    @DELETE
    public Response delete(@PathParam("id") UUID id) {
        return super.delete(id);
    }

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    @GET
    public Response list() {
        return super.list();
    }

    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Override
    @POST
    public Response create(MockBoJerseyEndpoint.MockEntity entity)
    {
        return super.create(entity);
    }

    @Override
    @Consumes({"application/json"})
    @Path("/{id}")
    @PUT
    @Produces({"application/json"})
    public Response update(@PathParam("id") UUID id, MockBoJerseyEndpoint.MockEntity entity) {
        return super.update(id, entity);
    }

    @Consumes({"application/json"})
    @Override
    @Produces({"application/json"})
    @POST
    @Path("/search")
    public Response search(Query query) {
        return super.search(query);
    }

    @XmlRootElement
    public static class MockEntity extends AbstractBusinessObject {

        private String name;

        private int integerAttribute;

        private MockEntity parentEntity;

        private List<MockEntity> childEntities;

        MockEntity() {
            //Noop Constructor
        }

        /*
         * (non-Javadoc)
         *
         * @see org.divy.common.bo.repository.BusinessObject#getIdentity()
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
                return other.uuid == null;
            } else return uuid.equals(other.uuid);
        }

        public int getIntegerAttribute() {
            return integerAttribute;
        }

        public void setIntegerAttribute(int integerAttribute) {
            this.integerAttribute = integerAttribute;
        }
    }
}
