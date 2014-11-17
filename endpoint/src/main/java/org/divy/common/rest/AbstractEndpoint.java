package org.divy.common.rest;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.rest.builder.ResponseEntityBuilderFactory;


public class AbstractEndpoint<T extends IBusinessObject<K>,K extends Serializable> {

    private IBOManager<T, K> manager;

    public IBOManager<T, K> getManager() {
        return manager;
    }

    public void setManager(IBOManager<T, K> manager) {
        this.manager = manager;
    }

    @Inject
    ResponseEntityBuilderFactory entityBuilder;

    @POST
    public Response create(T t) {
        final T savedT = getManager().update(t);
        return entityBuilder.create(savedT)
                .build();
    }

    @Path(value = "/{id}")
    @PUT
    public Response update(@PathParam("id") K id,T t) {
        final T savedT = manager.get(id);
        savedT.update(t);
        manager.update(savedT);
        return entityBuilder.update()
                .build();
    }

    @Path("/{id}")
    @GET
    public Response read(@PathParam("id") K id) {
        final T entity = manager.get(id);
        return ResponseEntityBuilderFactory.read(entity)
                .build();
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") K id) {
        final T entity = manager.get(id);
        manager.delete(entity);
        return ResponseEntityBuilderFactory.delete()
                .build();
    }

    @GET
    public Response list() {
        final List<T> ts = manager.list();
        return ResponseEntityBuilderFactory.<T>list(ts)
                .build();
    }
}
