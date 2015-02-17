package org.divy.common.bo.endpoint;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.query.defaults.Query;
import org.divy.common.rest.LinkBuilder;
import org.divy.common.rest.LinkBuilderFactory;

public abstract class AbstractCRUDEndpoint<ENTITY, ID extends Serializable> {

	@Inject
	protected LinkBuilderFactory linkBuilderFactory;

	public AbstractCRUDEndpoint() {
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    @Consumes({ MediaType.APPLICATION_JSON})
    public final Response create(@NotNull ENTITY businessObject,
                                 @Context UriInfo uriInfo) {

        ENTITY createdBo = doCreate(businessObject);
        
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
    public final Response update(@NotNull @PathParam("entityId")ID key,@NotNull ENTITY businessObject,
                                 @Context UriInfo uriInfo) {

        doUpdate(businessObject);

        return Response.noContent().build();
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public final Response delete(@NotNull @PathParam("id") ID id,
                                 @Context UriInfo uriInfo) {

        ENTITY entity = doDelete(id);

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
        Collection<ENTITY> resultList = doList();
        return buildListResponse(resultList);

    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/search")
    public final Response search(@NotNull Query query,
                                 @Context UriInfo uriInfo) {
        Collection<ENTITY> resultList = doSearch(query);
        return buildListResponse(resultList);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public final Response read(@NotNull @PathParam("id") ID id,
                              @Context UriInfo uriInfo) {
        ENTITY entity =  doRead(id);

        return buildReadResponse(entity);
    }
    
    protected Response buildListResponse(Collection<ENTITY> resultList) {
        if(resultList==null || resultList.size()<1) {
            return Response.noContent().build();
        } else {
            return Response.ok(resultList).build();
        }
    }


	protected Response buildReadResponse(ENTITY entity) {
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

    protected abstract String identity(ENTITY createdBo);
    protected abstract ENTITY doRead(ID id);
    protected abstract ENTITY doCreate(ENTITY businessObject);
    protected abstract ENTITY doUpdate(ENTITY businessObject);
    protected abstract void doDelete(ENTITY businessObject);
    protected abstract ENTITY doDelete(ID id);
    protected abstract Collection<ENTITY> doList();
    protected abstract Collection<ENTITY> doSearch(IQuery query);

}