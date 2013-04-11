/**
 * 
 */
package org.divy.common.bo.service.json;

import java.net.URI;
import java.util.ArrayList;
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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.divy.common.bo.IBOManager;
import org.divy.common.bo.IBusinessObject;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public abstract class AbstractBOService<ENTITY extends IBusinessObject<ID>, ID> {

	private IBOManager<ENTITY, ID> manager;

	public IBOManager<ENTITY, ID> getManager() {
		return manager;
	}

	public void setManager(IBOManager<ENTITY, ID> manager) {
		this.manager = manager;
	}

	@Context
	UriInfo uriInfo;

	public AbstractBOService(IBOManager<ENTITY, ID> manager) {
		this.manager = manager;
	}

	public AbstractBOService() {
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response create(ENTITY businessObject) {
		ENTITY createdBo = manager.create(businessObject);

		URI createdlocation = createURI(createdBo);

		return Response.created(createdlocation).entity(createdBo).build();
	}


	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(ENTITY businessObject) {
		ENTITY updatedBo = manager.update(businessObject);

		URI updatedlocation = createURI(updatedBo);

		return Response.created(updatedlocation).entity(updatedBo).build();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(ENTITY businessObject) {
		
		manager.delete(businessObject);

		return Response.noContent().build();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/{id}")
	public Response delete(@PathParam("id") ID id) {

		ENTITY deletedBo = manager.deleteById(id);
		
		if(deletedBo==null)
			return Response.status(404).build();
		
		return Response.noContent().build();
	}

	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Response search(IQuery<ENTITY> businessObjectQuery) {
	// List<ENTITY> boList = manager.search(businessObjectQuery);
	//
	// return Response.ok().entity(boList).build();
	// }

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response list() {
		List<ENTITY> boList = manager.list();

		List<ENTITY> resultList = new ArrayList<ENTITY>();

		resultList.addAll(boList);

		GenericEntity<List<ENTITY>> entity = new GenericEntity<List<ENTITY>>(
				resultList) {
		};

		return Response.ok().entity(entity).build();
	}


	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/{id}")
	public Response get(@PathParam("id") ID id) {
		ENTITY entity = manager.get(id);

		URI foundlocation = createURI(entity);

		return Response.ok(foundlocation).entity(entity).build();
	}

	/**
	 * @param createdBo
	 * @return
	 */
	private URI createURI(ENTITY createdBo) {
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI createdlocation = ub.path(createdBo.identity().toString())
				.build();
		return createdlocation;
	}
}
