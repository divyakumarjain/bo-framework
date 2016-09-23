/**
 * 
 */
package org.divy.common.bo.endpoint;


import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.query.Query;
import org.divy.common.rest.LinkBuilderFactory;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract Class for Endpoint for Business Object.
 *
 * @author Divyakumar
 * @param <E> The Entity or Business Object
 * @param <I> The Identity type of Business Object
 */
public abstract class AbstractBOEndpoint<E extends IBusinessObject<I>, I extends Serializable>
        extends AbstractCRUDEndpoint<E, I> {


    private IBOManager<E, I> manager;

    /**
     * Constructs Endpoint for Business Object entity management
     *
     * @param manager the manger instance responsible for management for Business Object entity
     */
    @Inject
    public AbstractBOEndpoint(IBOManager<E, I> manager, LinkBuilderFactory linkBuilderFactory) {
        super(linkBuilderFactory);
        this.manager = manager;
    }


    public IBOManager<E, I> getManager() {
        return manager;
    }

    public void setManager(IBOManager<E, I> manager) {
        this.manager = manager;
    }

    @Override
    protected Collection<E> doSearch(Query query) {
        return manager.search(query);
    }

    @Override
    protected E doCreate(E businessObject) {
        return manager.create(businessObject);
    }

    @Override
    protected E doUpdate(E businessObject) {
        return manager.update(businessObject);
    }

    @Override
    protected void doDelete(E businessObject) {

        manager.delete(businessObject);

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
