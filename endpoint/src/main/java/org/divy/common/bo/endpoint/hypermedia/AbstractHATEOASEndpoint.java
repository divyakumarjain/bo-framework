/**
 * 
 */
package org.divy.common.bo.endpoint.hypermedia;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.endpoint.association.AbstractAssociations;
import org.divy.common.bo.query.IQuery;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilder;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The Abstract implementation for HATEOAS based Business Object management Endpoint  
 * @author Divyakumar
 * @param <E> The Business Object Entity
 * @param <R> The HATEOAS representation of Business Object entity
 *
 */
public abstract class AbstractHATEOASEndpoint<E extends IBusinessObject<I>,
        R extends AbstractRepresentation,
        I extends Serializable>
        extends AbstractCRUDEndpoint<R, I> {

    public abstract IBOManager<E, I> getManager();

	@Override
    protected String identity(R representation) {
        return representation.identity().toString();
	}

	@Override
    protected R doRead(I id) {
        E businessObject = getManager().get(id);
        
        if(businessObject == null) {
            throw new NotFoundException("Could not find the entity");
        }
        
        return getRepresentationMapper().createFromBO(businessObject);
	}

	@Override
    protected R doCreate(R representation) {
        return getRepresentationMapper().createFromBO(getManager().create(getRepresentationMapper().createBO(representation)));
	}

	@Override
    protected R doUpdate(R representation) {
        return getRepresentationMapper().createFromBO(getManager().update(getRepresentationMapper().createBO(representation)));
	}

	@Override
    protected void doDelete(R representation) {
        getManager().delete(getRepresentationMapper().createBO(representation));
	}

	@Override
    protected R doDelete(I id) {
        return getRepresentationMapper().createFromBO(getManager().deleteById(id));
    }

	@Override
    protected Collection<R> doList() {
        Collection<E> boList = getManager().list();

        Collection<E> resultList = new ArrayList<>();

        resultList.addAll(boList);

        return getRepresentationMapper().createFromBO(resultList);
	}

	@Override
    protected Collection<R> doSearch(IQuery query) {
        Collection<E> boList = getManager().search(query);

        Collection<E> resultList = new ArrayList<>();

		resultList.addAll(boList);

		return getRepresentationMapper().createFromBO(resultList);
	}


    @PUT
    @Path("/{id}/{relation}")
    public Response updateRelation(@NotNull @PathParam("id") I id,
                                   @NotNull @PathParam("relation") String relation,
                                   @Context UriInfo uriInfo) {
        //TODO Implement
        return Response.noContent().build();
    }
    
    @GET
    @Path("/{id}/{relation}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response readRelation(@NotNull @PathParam("id") I id,
                                 @PathParam("relation")String relation,
                                 @Context UriInfo uriInfo) {

        E entity = getManager().get(id);

        Object entityRelation = null;
        try {
            entityRelation = this.getAssociations().getAssociation(relation).read(entity);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new InternalServerErrorException(e);
		}

        if(entityRelation!=null) {
            return Response.ok(entityRelation).build();
        } else {
            return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/{relation}")
    public Response createRelation(@NotNull @PathParam("id") I id,
                                   @PathParam("relation")String relation,
                                   @Context UriInfo uriInfo) {

        E businessObject = getManager().get(id);

        if (businessObject == null) {
            throw new NotFoundException("Could not find the entity");
        }

//        this.getAssociations().getAssociation(relation).
        
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

    public abstract HATEOASMapper<E, R> getRepresentationMapper();

    public abstract AbstractAssociations<E> getAssociations();
}
