package org.divy.common.bo.endpoint;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.query.defaults.Query;
import org.divy.common.rest.builder.ResponseEntityBuilderFactory;

public abstract class AbstractCRUDEndpoint<ENTITY extends IBusinessObject<ID>, ID extends Serializable> {

    public ResponseEntityBuilderFactory<ENTITY> getResponseEntityBuilderFactory() {
        return responseEntityBuilderFactory;
    }

    public void setResponseEntityBuilderFactory(ResponseEntityBuilderFactory<ENTITY> responseEntityBuilderFactory) {
        this.responseEntityBuilderFactory = responseEntityBuilderFactory;
    }

    @Inject
    ResponseEntityBuilderFactory<ENTITY> responseEntityBuilderFactory;

    public AbstractCRUDEndpoint() {
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON})
    @Consumes({ MediaType.APPLICATION_JSON})
    public final Response create(ENTITY businessObject,
                                 @Context UriInfo uriInfo) {

        ENTITY createdBo = doCreate(businessObject);

        return responseEntityBuilderFactory.create(createdBo).build(uriInfo);
    }

    @PUT
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public final Response update(ENTITY businessObject,
                                 @Context UriInfo uriInfo) {

        ENTITY updatedBo =  doUpdate(businessObject);

        return responseEntityBuilderFactory.update(updatedBo).build(uriInfo);
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public final Response delete(ENTITY businessObject,
                                 @Context UriInfo uriInfo) {

        doDelete(businessObject);

        return responseEntityBuilderFactory.delete(businessObject).build(uriInfo);
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public final Response delete(@PathParam("id") ID id,
                                 @Context UriInfo uriInfo) {

        ENTITY deletedBo = doDelete(id);

        return responseEntityBuilderFactory.delete(deletedBo).build(uriInfo);
    }


    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public final Response list(@Context UriInfo uriInfo) {
        List<ENTITY> resultList = doList();
        return responseEntityBuilderFactory.list(resultList).build(uriInfo);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/search")
    public final Response search(Query query,
                                 @Context UriInfo uriInfo) {
        List<ENTITY> resultList = doSearch(query);
        return responseEntityBuilderFactory.list(resultList).build(uriInfo);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public final Response get(@PathParam("id") ID id,
                              @Context UriInfo uriInfo) {
        ENTITY entity =  doGet(id);

       return responseEntityBuilderFactory.read(entity).build(uriInfo);
    }

    protected abstract String getIdentity(ENTITY createdBo);
    protected abstract ENTITY doGet(ID id);
    protected abstract ENTITY doCreate(ENTITY businessObject);
    protected abstract ENTITY doUpdate(ENTITY businessObject);
    protected abstract void doDelete(ENTITY businessObject);
    protected abstract ENTITY doDelete(ID id);
    protected abstract List<ENTITY> doList();
    protected abstract List<ENTITY> doSearch(IQuery query);

}