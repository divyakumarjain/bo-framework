package org.divy.common.bo.endpoint;

import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.builder.ResponseEntityBuilderFactory;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractCRUDEndpoint<E, I extends Serializable> {
    private ResponseEntityBuilderFactory<E, I> responseEntityBuilderFactory;

    public AbstractCRUDEndpoint(ResponseEntityBuilderFactory responseEntityBuilderFactory) {
        this.responseEntityBuilderFactory = responseEntityBuilderFactory;
    }

    public  Response create(@NotNull E businessObject, UriInfo uriInfo) {
        E createdBo = doCreate(businessObject);

        return responseEntityBuilderFactory.create(createdBo).build();
    }

    public  Response update(@NotNull I id,@NotNull E businessObject, UriInfo uriInfo) {
        doUpdate(id, businessObject);

        return responseEntityBuilderFactory.update().build();
    }

    public  Response delete(@NotNull I id, UriInfo uriInfo) {
        doDelete(id);

        return responseEntityBuilderFactory.delete().build();
    }

    public  Response list(UriInfo uriInfo) {
        Collection<E> resultList = doList();

        return responseEntityBuilderFactory.list(resultList).build();

    }

    public  Response search(@NotNull Query query,
                                 UriInfo uriInfo) {
        Collection<E> resultList = doSearch(query);
        return responseEntityBuilderFactory.list(resultList).build();
    }

    public  Response read(@NotNull I id,
                               UriInfo uriInfo) {
        E entity = doRead(id);

        return responseEntityBuilderFactory.read(entity).build();
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
