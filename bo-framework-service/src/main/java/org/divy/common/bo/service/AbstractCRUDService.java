package org.divy.common.bo.service;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public abstract class AbstractCRUDService<ENTITY, ID extends Serializable> {
	
	@Context UriInfo uriInfo;

	public AbstractCRUDService() {
		super();
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.APPLICATION_JSON})
	public final Response create(ENTITY businessObject) {
		
		ENTITY createdBo = doCreate(businessObject); 

		URI createdlocation = createURI(createdBo);

		return Response.created(createdlocation).entity(createdBo).build();
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public final Response update(ENTITY businessObject) {
		
		ENTITY updatedBo =  doUpdate(businessObject);
		
		URI updatedlocation = createURI(updatedBo);
	
		return Response.created(updatedlocation).entity(updatedBo).build();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public final Response delete(ENTITY businessObject) {
		
		doDelete(businessObject);
		
		return Response.noContent().build();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public final Response delete(@PathParam("id") ID id) {
	
		ENTITY deletedBo = doDelete(id);
		
		if (deletedBo == null) {
			return Response.status(Status.NO_CONTENT).build();
		}
		
		return Response.noContent().build();
	}


	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public final Response list() {
		
		 List<ENTITY> resultList = doList();
		
		GenericEntity<List<ENTITY>> entity = new GenericEntity<List<ENTITY>>(
				resultList) {
		};
	
		return Response.ok().entity(entity).build();
	}


	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public final Response get(@PathParam("id") ID id) {
		ENTITY entity =  doGet(id);
		
		if (entity != null) {
	
			URI foundlocation = createURI(entity);
			return Response.ok(foundlocation).entity(entity).build();
	
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}


	/**
	 * @param createdBo
	 * @return
	 */
	protected URI createURI(ENTITY createdBo) {
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI createdlocation = ub.path(getIdentity(createdBo))
				.build();
		return createdlocation;
	}

	protected abstract String getIdentity(ENTITY createdBo);
	protected abstract ENTITY doGet(ID id);
	protected abstract ENTITY doCreate(ENTITY businessObject);
	protected abstract ENTITY doUpdate(ENTITY businessObject);
	protected abstract void doDelete(ENTITY businessObject);
	protected abstract ENTITY doDelete(ID id);
	protected abstract List<ENTITY> doList();

}