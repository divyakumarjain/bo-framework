/**
 * 
 */
package org.divy.common.bo.endpoint.hypermedia;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.business.defaults.DefaultBOManager;
import org.divy.common.bo.defaults.DefaultDatabaseRepository;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.query.IQuery;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilder;
import org.divy.common.rest.builder.ReadEntityResponseBuilder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * 
 * @author Divyakumar
 *
 */
public abstract class AbstractHATEOASEndpoint <ENTITY extends IBusinessObject<UUID>,
		REPRESENTATION extends AbstractRepresentation,
		UUID extends Serializable>
        extends AbstractCRUDEndpoint<REPRESENTATION, UUID> {

    @Inject
    private IBOManager<ENTITY, UUID> manager;

	public AbstractHATEOASEndpoint(IBOManager<ENTITY, UUID> manager) {
        this.manager = manager;
    }

    public AbstractHATEOASEndpoint(IBORepository<ENTITY,UUID> repository) {
        this(new DefaultBOManager<>( repository));
    }

    public AbstractHATEOASEndpoint(String persistentUnitName,Class<ENTITY> entityClass) {
        this(new DefaultDatabaseRepository<ENTITY,UUID>(persistentUnitName, entityClass));
    }

    public AbstractHATEOASEndpoint() {
    }

    public IBOManager<ENTITY, UUID> getManager() {
        return manager;
    }

    public void setManager(IBOManager<ENTITY, UUID> manager) {
        this.manager = manager;
    }

	@Override
	protected String identity(REPRESENTATION representation) {
		return representation.identity().toString();
	}

	@Override
	protected REPRESENTATION doRead(UUID UUID) {
		return getRepresentationMapper().createFromBO(manager.get(UUID));
	}

	@Override
	protected REPRESENTATION doCreate(REPRESENTATION representation) {
		return getRepresentationMapper().createFromBO(manager.create(getRepresentationMapper().createBO(representation)));
	}

	@Override
	protected REPRESENTATION doUpdate( REPRESENTATION representation) {
		return getRepresentationMapper().createFromBO(manager.update(getRepresentationMapper().createBO(representation)));
	}

	@Override
	protected void doDelete(REPRESENTATION representation) {
		manager.delete(getRepresentationMapper().createBO(representation));
	}

	@Override
	protected REPRESENTATION doDelete(UUID UUID) {
		return getRepresentationMapper().createFromBO(manager.deleteById(UUID));
	}

	@Override
	protected List<REPRESENTATION> doList() {
        List<ENTITY> boList = manager.list();

        List<ENTITY> resultList = new ArrayList<>();

        resultList.addAll(boList);

        return getRepresentationMapper().createFromBO(resultList);
	}

	@Override
	protected List<REPRESENTATION> doSearch(IQuery query) {
		List<ENTITY> boList = manager.search(query);

		List<ENTITY> resultList = new ArrayList<>();

		resultList.addAll(boList);

		return getRepresentationMapper().createFromBO(resultList);
	}
    
    @GET
    @Path("/{relation}")
    public Response readRelation(@PathParam("relation")String relation) {
        Object entity = null;
        //TODO Implement
        //entity = doReadRelation();
        if(entity!=null) {
            return Response.ok(entity).build();
        } else {
            return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{relation}")
    public Response createRelation(@PathParam("relation")String relation) {

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

    @PUT
    @Path("/{relation}")
    public Response updateRelation(@PathParam("relation")String relation) {
        //TODO Implement
        return Response.noContent().build();
    }

	public abstract HATEOASMapper<ENTITY, REPRESENTATION> getRepresentationMapper();
}
