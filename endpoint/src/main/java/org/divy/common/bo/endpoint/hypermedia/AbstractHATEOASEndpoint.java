/**
 * 
 */
package org.divy.common.bo.endpoint.hypermedia;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.association.AbstractAssociations;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.query.IQuery;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilder;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * 
 * @author Divyakumar
 *
 */
public abstract class AbstractHATEOASEndpoint <ENTITY extends IBusinessObject<UUID>,
		REPRESENTATION extends AbstractRepresentation,
		UUID extends Serializable>
        extends AbstractCRUDEndpoint<REPRESENTATION, UUID> {

    public AbstractHATEOASEndpoint() {
    }

    public abstract IBOManager<ENTITY, UUID> getManager();

	@Override
	protected String identity(REPRESENTATION representation) {
		return representation.identity().toString();
	}

	@Override
	protected REPRESENTATION doRead(UUID UUID) {
        ENTITY businessObject = getManager().get(UUID);
        
        if(businessObject == null) {
            throw new NotFoundException("Could not find the entity");
        }
        
        return getRepresentationMapper().createFromBO(businessObject);
	}

	@Override
	protected REPRESENTATION doCreate(REPRESENTATION representation) {
		return getRepresentationMapper().createFromBO(getManager().create(getRepresentationMapper().createBO(representation)));
	}

	@Override
	protected REPRESENTATION doUpdate( REPRESENTATION representation) {
		return getRepresentationMapper().createFromBO(getManager().update(getRepresentationMapper().createBO(representation)));
	}

	@Override
	protected void doDelete(REPRESENTATION representation) {
        getManager().delete(getRepresentationMapper().createBO(representation));
	}

	@Override
	protected REPRESENTATION doDelete(UUID UUID) {
		return getRepresentationMapper().createFromBO(getManager().deleteById(UUID));
	}

	@Override
	protected Collection<REPRESENTATION> doList() {
        Collection<ENTITY> boList = getManager().list();

        Collection<ENTITY> resultList = new ArrayList<>();

        resultList.addAll(boList);

        return getRepresentationMapper().createFromBO(resultList);
	}

	@Override
	protected Collection<REPRESENTATION> doSearch(IQuery query) {
        Collection<ENTITY> boList = getManager().search(query);

        Collection<ENTITY> resultList = new ArrayList<>();

		resultList.addAll(boList);

		return getRepresentationMapper().createFromBO(resultList);
	}


    @PUT
    @Path("/{id}/{relation}")
    public Response updateRelation(@NotNull @PathParam("id") UUID id,
                                   @PathParam("relation")String relation,
                                   @Context UriInfo uriInfo) {
        //TODO Implement
        return Response.noContent().build();
    }
    
    @GET
    @Path("/{id}/{relation}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response readRelation(@NotNull @PathParam("id") UUID id,
            @PathParam("relation")String relation,
            @Context UriInfo uriInfo) {

    	ENTITY entity = getManager().get(id);
        
    	Object entityRelation = null;
        try {
			entityRelation = this.getAssociations().getAssociation(relation).getReader().read(entity);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if(entityRelation!=null) {
            return Response.ok(entityRelation).build();
        } else {
            return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/{relation}")
    public Response createRelation(@NotNull @PathParam("id") UUID id,
                                   @PathParam("relation")String relation,
                                   @Context UriInfo uriInfo) {

        Object createdBo = null;
        URI createLocation = null;

        //TODO Implement
        //createdBo = doCreate(businessObject);

        LinkBuilder linkBuilder = linkBuilderFactory.newBuilder();

//        createLocation = linkBuilder
//                .path(this.getClass())
//                .path(this.getClass(),"read")
//                .buildUri(identity(createdBo));

        return Response.created(createLocation).build();
    }

    public abstract HATEOASMapper<ENTITY, REPRESENTATION> getRepresentationMapper();

	public abstract AbstractAssociations<ENTITY> getAssociations();
}
