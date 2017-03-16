package org.divy.common.bo.endpoint.hypermedia;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.LinkBuilderFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;

/**
 * The Abstract implementation for HATEOAS based Business Object management Endpoint
 *
 * @param <E> The Business Object Entity
 * @param <R> The HATEOAS representation of Business Object entity
 *
 */
public abstract class AbstractHATEOASJerseyEndpoint<E extends IBusinessObject<I>,
        R extends AbstractRepresentation,
        I extends Serializable>
        extends AbstractHATEOASEndpoint<E, R, I> {

    @Inject
    public AbstractHATEOASJerseyEndpoint(LinkBuilderFactory linkBuilderFactory) {
        super(linkBuilderFactory);
    }

    public abstract IBOManager<E, I> getManager();

    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    @Consumes({ MediaType.APPLICATION_JSON})
    @Override
    public final Response create(R businessObject,
                                 @Context UriInfo uriInfo) {
        return super.create(businessObject, uriInfo);
    }

    @PUT
    @Path("/{entityId}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Override
    public final Response update(@PathParam("entityId") I id, R businessObject,
                                 @Context UriInfo uriInfo) {

        return super.update(id, businessObject, uriInfo);
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    @Override
    public final Response delete(@PathParam("id") I id,
                                 @Context UriInfo uriInfo) {

        return super.delete(id, uriInfo);

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Override
    public final Response list(@Context UriInfo uriInfo) {
        return super.list(uriInfo);

    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/search")
    @Override
    public final Response search(Query query,
                                 @Context UriInfo uriInfo) {
        return super.search(query, uriInfo);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    @Override
    public final Response read(@PathParam("id") I id,
                               @Context UriInfo uriInfo) {
        return super.read(id, uriInfo);
    }

    @PUT
    @Path("/{id}/{relation}")
    @Override
    public Response updateRelation(@PathParam("id") I id,
                                   @PathParam("relation") String relation,
                                   @Context UriInfo uriInfo) {
        return super.updateRelation(id, relation, uriInfo);
    }

    @GET
    @Path("/{id}/{relation}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Override
    public Response readRelation(@PathParam("id") I id,
                                 @PathParam("relation")String relation,
                                 @Context UriInfo uriInfo) {

        return super.readRelation(id, relation, uriInfo);
    }

    /**
     * Creates Relationship between Business Object Entities.
     *
     * @param id Business Object Entity identity of source of the relationship/association
     * @param relation name of the association attribute of relationship
     * @param uriInfo URL which is used by client trigger post method to create relationship
     *
     * @return returns 201 status code for successful creation of relationship/association
     */
    @POST
    @Path("/{id}/{relation}")
    @Override
    public Response createRelation(@PathParam("id") I id,
                                   @PathParam("relation")String relation,
                                   @Context UriInfo uriInfo) {
        return super.createRelation(id, relation, uriInfo);
    }
}
