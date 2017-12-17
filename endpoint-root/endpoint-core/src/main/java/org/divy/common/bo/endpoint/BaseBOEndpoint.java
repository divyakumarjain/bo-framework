package org.divy.common.bo.endpoint;


import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.BOManager;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.rest.response.ResponseEntityBuilderFactory;
import org.divy.common.bo.validation.BOValidationException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract Class for Endpoint for Business Object.
 *
 *
 * @param <E> The Entity or Business Object
 * @param <I> The Identity _type of Business Object
 */
public class BaseBOEndpoint<E extends BusinessObject<I>, I extends Serializable, R>
        extends AbstractCRUDEndpoint<E, I, R> {


    private BOManager<E, I> manager;

    /**
     * Constructs Endpoint for Business Object entity management
     *
     * @param manager the manger instance responsible for management for Business Object entity
     */
    public BaseBOEndpoint(BOManager<E, I> manager, ResponseEntityBuilderFactory<E, R> responseEntityBuilderFactory) {
        super(responseEntityBuilderFactory);
        this.manager = manager;
    }

    @Override
    protected Collection<E> doSearch(Query query) {
        return manager.search(query);
    }

    @Override
    protected E doCreate(E businessObject) throws BOValidationException {
        return manager.create(businessObject);
    }

    @Override
    protected E doUpdate(I id, E businessObject) {
        return manager.update(id, businessObject);
    }

    @Override
    protected E doDelete(I id) {

        return manager.deleteById(id);

    }

    @Override
    protected Collection<E> doList() {

        List<E> boList = manager.list();

        List<E> resultList = new ArrayList<>();

        resultList.addAll(boList);

        return resultList;

    }

    @Override
    protected String identity(E createdBo) {
        return createdBo.identity().toString();
    }

    @Override
    protected E doRead(I id) {
        return manager.get(id);
    }
}
