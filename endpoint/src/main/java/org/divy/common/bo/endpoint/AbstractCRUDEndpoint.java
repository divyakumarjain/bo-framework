package org.divy.common.bo.endpoint;

import org.divy.common.bo.query.Query;
import org.divy.common.rest.LinkBuilder;
import org.divy.common.rest.LinkBuilderFactory;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;
import java.util.Collection;
public abstract class AbstractCRUDEndpoint<E, I extends Serializable> {


    protected LinkBuilderFactory linkBuilderFactory;

    @Inject
    public AbstractCRUDEndpoint(LinkBuilderFactory linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    @Consumes({ MediaType.APPLICATION_JSON})
    public final Response create(@NotNull E businessObject,
                                 @Context UriInfo uriInfo) {

        E createdBo = doCreate(businessObject);
        
        LinkBuilder linkBuilder = linkBuilderFactory.newBuilder();
        
        URI createLocation = linkBuilder
                .path(this.getClass())
                .path(this.getClass(),"read")
                .buildUri(identity(createdBo));

        return Response.created(createLocation).build();
    }

    @PUT
    @Path("/{entityId}")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public final Response update(@NotNull @PathParam("entityId") I id, @NotNull E businessObject,
                                 @Context UriInfo uriInfo) {

        doUpdate(id, businessObject);

        return Response.noContent().build();
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public final Response delete(@NotNull @PathParam("id") I id,
                                 @Context UriInfo uriInfo) {

        E entity = doDelete(id);

        if(entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.noContent().build();
        }
        
    }


    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public final Response list(@Context UriInfo uriInfo) {
        Collection<E> resultList = doList();
        return buildListResponse(resultList);

    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/search")
    public final Response search(@NotNull Query query,
                                 @Context UriInfo uriInfo) {
        Collection<E> resultList = doSearch(query);
        return buildListResponse(resultList);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public final Response read(@NotNull @PathParam("id") I id,
                               @Context UriInfo uriInfo) {
        E entity = doRead(id);

        return buildReadResponse(entity);
    }

    protected Response buildListResponse(Collection<E> resultList) {
        if (resultList == null || resultList.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok(resultList).build();
        }
    }


    protected Response buildReadResponse(E entity) {
        if(entity!=null) {
            return Response.ok(entity).build();            
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    public LinkBuilderFactory getLinkBuilderFactory() {
        return linkBuilderFactory;
    }

    public void setLinkBuilderFactory(LinkBuilderFactory linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    protected abstract String identity(E createdBo);

    protected abstract E doRead(I id);

    protected abstract E doCreate(E businessObject);

    protected abstract E doUpdate(I id, E businessObject);

    protected abstract void doDelete(E businessObject);

    protected abstract E doDelete(I id);

    protected abstract Collection<E> doList();

    protected abstract Collection<E> doSearch(Query query);

}