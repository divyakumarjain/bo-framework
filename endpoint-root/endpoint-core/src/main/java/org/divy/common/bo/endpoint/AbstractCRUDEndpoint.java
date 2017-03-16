package org.divy.common.bo.endpoint;

import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.LinkBuilder;
import org.divy.common.bo.rest.LinkBuilderFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;
import java.util.Collection;

public abstract class AbstractCRUDEndpoint<E, I extends Serializable> {
    protected LinkBuilderFactory linkBuilderFactory;

    public AbstractCRUDEndpoint(LinkBuilderFactory linkBuilderFactory) {
        this.linkBuilderFactory = linkBuilderFactory;
    }

    public  Response create(E businessObject, UriInfo uriInfo) {

        E createdBo = doCreate(businessObject);

        LinkBuilder linkBuilder = linkBuilderFactory.newBuilder();

        URI createLocation = linkBuilder
                .path(this.getClass())
                .path(this.getClass(),"read")
                .buildUri(identity(createdBo));

        return Response.created(createLocation).build();
    }

    public  Response update(I id, E businessObject, UriInfo uriInfo) {

        doUpdate(id, businessObject);

        return Response.noContent().build();
    }

    public  Response delete(I id, UriInfo uriInfo) {

        E entity = doDelete(id);

        if(entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.noContent().build();
        }

    }

    public  Response list(UriInfo uriInfo) {
        Collection<E> resultList = doList();
        return buildListResponse(resultList);

    }

    public  Response search( Query query,
                                 UriInfo uriInfo) {
        Collection<E> resultList = doSearch(query);
        return buildListResponse(resultList);
    }

    public  Response read( I id,
                               UriInfo uriInfo) {
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
