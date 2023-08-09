package org.divy.common.bo.jersey.rest.hatoas;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.endpoint.hatoas.AbstractHATOASEndpoint;
import org.divy.common.bo.endpoint.hatoas.association.AssociationsHandler;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;

/**
 * The Abstract implementation for HATEOAS based Business Object management Endpoint
 *
 * @param <B> The Business Object Entity
 * @param <E> The HATEOAS representation of Business Object entity
 */
public abstract class AbstractHATOASJerseyEndpoint<B extends BusinessObject<I>,
        E extends JerseyRepresentation<I>,
        I extends Serializable>
        extends AbstractHATOASEndpoint<B, E, I, Response, Link>
{

    @Inject
    protected AbstractHATOASJerseyEndpoint(ResponseEntityBuilderFactory<E, Response> responseEntityBuilderFactory, AssociationsHandler<B,I, Link> associationsHandler)
    {
        super(responseEntityBuilderFactory,associationsHandler);
    }

    @Override
    public final Response create(E businessObject)
    {
        return super.create(businessObject);
    }

    @Override
    public final Response update(I id, E businessObject)
    {

        return super.update(id, businessObject);
    }

    @Override
    public final Response delete(I id)
    {

        return super.delete(id);

    }

    @Override
    public final Response list()
    {
        return super.list();

    }

    @Override
    public final Response search(Query query)
    {
        return super.search(query);
    }

    @Override
    public final Response read(I id)
    {
        return super.read(id);
    }

    @PUT
    @Path("/{id}/{relation}")
    @Override
    public Response updateRelation(@PathParam("id") I id,
                                   @PathParam("relation") String relation)
    {
        return super.updateRelation(id, relation);
    }

    @GET
    @Path("/{id}/{relation}")
    @Produces({MediaType.APPLICATION_JSON})
    @Override
    public Response readRelation(@PathParam("id") I id,
                                 @PathParam("relation") String relation)
    {

        return super.readRelation(id, relation);
    }

    /**
     * Creates Relationship between Business Object Entities.
     *
     * @param id       Business Object Entity identity of source of the relationship/association
     * @param relation name of the association attribute of relationship
     * @return returns 201 status code for successful creation of relationship/association
     */
    @POST
    @Path("/{id}/{relation}")
    @Override
    public Response createRelation(@PathParam("id") I id
          ,@PathParam("relation") String relation
          , E representation)
    {
        return super.createRelation(id, relation, representation);
    }
}
