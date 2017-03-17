package org.divy.common.bo.endpoint;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.LinkBuilderFactory;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;

public abstract class AbstractBOJerseyEndpoint<E extends IBusinessObject<I>, I extends Serializable>
        extends AbstractBOEndpoint<E, I> {


    @Inject
    public AbstractBOJerseyEndpoint(IBOManager<E, I> manager, LinkBuilderFactory linkBuilderFactory) {
        super(manager, linkBuilderFactory);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    @Consumes({ MediaType.APPLICATION_JSON})
    @Override
    public final Response create(@NotNull E businessObject,
                                 @Context UriInfo uriInfo) {
        return super.create(businessObject, uriInfo);
    }

    @PUT
    @Path("/{entityId}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Override
    public final Response update(@NotNull @PathParam("entityId") I id, @NotNull E businessObject,
                                 @Context UriInfo uriInfo) {

        return super.update(id, businessObject, uriInfo);
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    @Override
    public final Response delete(@NotNull @PathParam("id") I id,
                                 @Context UriInfo uriInfo) {

        return super.delete(id, uriInfo);

    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/search")
    @Override
    public final Response search(@NotNull Query query,
                                 @Context UriInfo uriInfo) {
        return super.search(query, uriInfo);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Override
    public final Response list(@Context UriInfo uriInfo) {
        return super.list(uriInfo);

    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    @Override
    public final Response read(@NotNull @PathParam("id") I id,
                               @Context UriInfo uriInfo) {
        return super.read(id, uriInfo);
    }
}
