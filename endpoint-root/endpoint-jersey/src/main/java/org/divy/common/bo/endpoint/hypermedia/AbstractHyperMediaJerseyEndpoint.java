package org.divy.common.bo.endpoint.hypermedia;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.validation.BOValidationException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * The Abstract implementation for HATEOAS based Business Object management Endpoint
 *
 * @param <B> The Business Object Entity
 * @param <E> The HATEOAS representation of Business Object entity
 *
 */
public abstract class AbstractHyperMediaJerseyEndpoint<B extends BusinessObject<I>,
        E extends JerseyRepresentation,
        I extends Serializable>
        extends AbstractHyperMediaEndpoint<B, E, I, Response> {

    @Inject
    public AbstractHyperMediaJerseyEndpoint(ResponseEntityBuilderFactory responseEntityBuilderFactory) {
        super(responseEntityBuilderFactory);
    }

    @Override
    public final Response create(E businessObject) {
        return super.create(businessObject);
    }

    @Override
    public final Response update(I id, E businessObject) {

        return super.update(id, businessObject);
    }

    @Override
    public final Response delete(I id) {

        return super.delete(id);

    }

    @Override
    public final Response list() {
        return super.list();

    }

    @Override
    public final Response search(Query query) {
        return super.search(query);
    }

    @Override
    public final Response read(I id) {
        return super.read(id);
    }

    @PUT
    @Path("/{id}/{relation}")
    @Override
    public Response updateRelation(@PathParam("id") I id,
                                   @PathParam("relation") String relation) {
        return super.updateRelation(id, relation);
    }

    @GET
    @Path("/{id}/{relation}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response readRelation(@PathParam("id") I id,
                                 @PathParam("relation")String relation) {

        return super.readRelation(id, relation);
    }

    /**
     * Creates Relationship between Business Object Entities.
     *
     * @param id Business Object Entity identity of source of the relationship/association
     * @param relation name of the association attribute of relationship
     *
     * @return returns 201 status code for successful creation of relationship/association
     */
    @POST
    @Path("/{id}/{relation}")
    @Override
    public Response createRelation(@PathParam("id") I id,
                                   @PathParam("relation")String relation) {
        return super.createRelation(id, relation);
    }
}
