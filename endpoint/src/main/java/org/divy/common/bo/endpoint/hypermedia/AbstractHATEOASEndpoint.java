/**
 * 
 */
package org.divy.common.bo.endpoint.hypermedia;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.endpoint.association.AbstractAssociations;
import org.divy.common.bo.query.Query;
import org.divy.common.rest.HATEOASMapper;
import org.divy.common.rest.LinkBuilder;
import org.divy.common.rest.LinkBuilderFactoryImpl;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * The Abstract implementation for HATEOAS based Business Object management Endpoint
 *
 * @param <E> The Business Object Entity
 * @param <R> The HATEOAS representation of Business Object entity
 *
 */
public abstract class AbstractHATEOASEndpoint<E extends IBusinessObject<I>,
        R extends AbstractRepresentation,
        I extends Serializable>
        extends AbstractCRUDEndpoint<R, I> {

    public abstract IBOManager<E, I> getManager();

    @Inject
    public AbstractHATEOASEndpoint(LinkBuilderFactoryImpl linkBuilderFactory) {
        super(linkBuilderFactory);
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

        Optional<Object> entityRelation = this.getAssociations().getAssociation(relation).read(entity);

        if(entityRelation.isPresent()) {
            return Response.ok(entityRelation).build();
        } else {
            return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
        }
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

        return mapFromBO(businessObject);
    }

    @Override
    protected R doCreate(R representation) {
        E createdBusinessObject = getManager().create(mapToBO(representation));
        return mapFromBO(createdBusinessObject);
    }

    @Override
    protected R doUpdate(I id, R representation) {
        return mapFromBO(getManager().update(id, mapToBO(representation)));
    }

    @Override
    protected void doDelete(R representation) {
        getManager().delete(mapToBO(representation));
    }

    @Override
    protected R doDelete(I id) {
        return mapFromBO(getManager().deleteById(id));
    }

    @Override
    protected Collection<R> doList() {
        Collection<E> boList = getManager().list();

        Collection<E> resultList = new ArrayList<>();

        resultList.addAll(boList);

        return getRepresentationMapper().createRepresentationFromBO(resultList);
    }

    @Override
    protected Collection<R> doSearch(Query query) {
        Collection<E> boList = getManager().search(query);

        Collection<E> resultList = new ArrayList<>();

        resultList.addAll(boList);

        return getRepresentationMapper().createRepresentationFromBO(resultList);
    }

    private E mapToBO(R representation) {
        return getRepresentationMapper().createBOFromRepresentation(representation);
    }

    private R mapFromBO(E createdBusinessObject) {
        return getRepresentationMapper().createRepresentationFromBO(createdBusinessObject);
    }

    public abstract HATEOASMapper<E, R> getRepresentationMapper();

    public abstract AbstractAssociations<E> getAssociations();
}
