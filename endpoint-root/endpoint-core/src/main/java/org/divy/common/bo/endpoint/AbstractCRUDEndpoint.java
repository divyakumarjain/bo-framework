package org.divy.common.bo.endpoint;

import org.divy.common.bo.business.validation.BOValidationException;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractCRUDEndpoint<E, I extends Serializable, R> {

    protected ResponseEntityBuilderFactory<E, R> responseEntityBuilderFactory;

    public AbstractCRUDEndpoint(ResponseEntityBuilderFactory<E, R> responseEntityBuilderFactory) {
        this.responseEntityBuilderFactory = responseEntityBuilderFactory;
    }

    public  R create(@NotNull E businessObject) throws BOValidationException {
        E createdBo = doCreate(businessObject);

        return responseEntityBuilderFactory.create(createdBo).build();
    }

    public  R update(@NotNull I id,@NotNull E businessObject) {
        doUpdate(id, businessObject);

        return responseEntityBuilderFactory.update().build();
    }

    public  R delete(@NotNull I id) {
        doDelete(id);

        return responseEntityBuilderFactory.delete().build();
    }

    public  R list() {
        Collection<E> resultList = doList();

        return responseEntityBuilderFactory.list(resultList).build();

    }

    public  R search(@NotNull Query query) {
        Collection<E> resultList = doSearch(query);
        return responseEntityBuilderFactory.list(resultList).build();
    }

    public  R read(@NotNull I id) {
        E entity = doRead(id);

        return responseEntityBuilderFactory.read(entity).build();
    }

    protected abstract String identity(E createdBo);

    protected abstract E doRead(I id);

    protected abstract E doCreate(E businessObject) throws BOValidationException;

    protected abstract E doUpdate(I id, E businessObject);

    protected abstract E doDelete(I id);

    protected abstract Collection<E> doList();

    protected abstract Collection<E> doSearch(Query query);
}
