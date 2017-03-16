package org.divy.common.bo.endpoint.hypermedia;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.endpoint.hypermedia.association.AbstractAssociations;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.HATEOASMapper;
import org.divy.common.bo.rest.LinkBuilderFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class AbstractHATEOASEndpoint<E extends IBusinessObject<I>, R extends AbstractRepresentation, I extends Serializable>
        extends AbstractCRUDEndpoint<R, I> {

    public AbstractHATEOASEndpoint(LinkBuilderFactory linkBuilderFactory) {
        super(linkBuilderFactory);
    }

    public abstract IBOManager<E, I> getManager();

    public Response updateRelation(I id,
                                   String relation,
                                   UriInfo uriInfo) {
        //TODO Implement
        return Response.noContent().build();
    }

    public Response readRelation(I id,
                                 String relation,
                                 UriInfo uriInfo) {

        E entity = getManager().get(id);

        Optional<Object> entityRelation = this.getAssociations().getAssociation(relation).read(entity);

        if(entityRelation.isPresent()) {
            return Response.ok(entityRelation).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
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
    public Response createRelation(@NotNull I id,
                                   String relation,
                                   UriInfo uriInfo) {

        E businessObject = getManager().get(id);

        if (businessObject == null) {
            throw new NotFoundException("Could not find the entity");
        }

        URI createLocation = null;

        return Response.created(createLocation).build();
    }

    protected String identity(R representation) {
        return representation.identity().toString();
    }

    protected R doRead(I id) {
        E businessObject = getManager().get(id);

        if(businessObject == null) {
            throw new NotFoundException("Could not find the entity");
        }

        return mapFromBO(businessObject);
    }

    protected R doCreate(R representation) {
        E createdBusinessObject = getManager().create(mapToBO(representation));
        return mapFromBO(createdBusinessObject);
    }

    protected R doUpdate(I id, R representation) {
        return mapFromBO(getManager().update(id, mapToBO(representation)));
    }

    protected void doDelete(R representation) {
        getManager().delete(mapToBO(representation));
    }

    protected R doDelete(I id) {
        return mapFromBO(getManager().deleteById(id));
    }

    protected Collection<R> doList() {
        Collection<E> boList = getManager().list();

        Collection<E> resultList = new ArrayList<>();

        resultList.addAll(boList);

        return getRepresentationMapper().createRepresentationFromBO(resultList);
    }

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