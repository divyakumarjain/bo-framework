package org.divy.common.bo.presentation;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.query.Query;
import org.divy.common.rest.LinkBuilderFactoryImpl;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractEntityPresentationService<E extends IBusinessObject<I>, V, I extends Serializable> extends AbstractCRUDEndpoint<E, I> {

    final IBOMapper<E, V> mapper;

    private IBOManager<E, I> manager;

    @Inject
    public AbstractEntityPresentationService(IBOMapper<E, V> mapper, LinkBuilderFactoryImpl linkBuilderFactory) {
        super(linkBuilderFactory);
        this.mapper = mapper;
    }

    public IBOManager<E, I> getManager() {
        return manager;
    }

    public void setManager(IBOManager<E, I> manager) {
        this.manager = manager;
    }


    @Override
    protected E doRead(I id) {

        return manager.get(id);
    }

    @Override
    protected E doCreate(E entity) {
        return manager.create(entity);
    }

    @Override
    protected E doUpdate(I id, E entity) {
        return manager.update(id, entity);
    }

    @Override
    protected void doDelete(E entity) {
        manager.delete(entity);
    }

    @Override
    protected E doDelete(I id) {
        return manager.deleteById(id);
    }

    @Override
    protected Collection<E> doList() {
        return manager.list();
    }

    @Override
    protected Collection<E> doSearch(Query query) {
        return manager.search(query);
    }

    protected V doGetPresenter(I id) {
        E boEntity = doRead(id);
        return mapper.createFromBO(boEntity);
    }

    protected V doCreatePresenter(V presentationObject) {
        E entityToBeCreated = mapper.createBO(presentationObject);
        E createdBusinessObject = doCreate(entityToBeCreated);
        return mapper.createFromBO(createdBusinessObject);
    }

    protected V doUpdatePresenter(V presentationObject) {
        E entityToBeUpdated = mapper.createBO(presentationObject);
        E updatedBusinessObject = doUpdate(entityToBeUpdated.identity(), entityToBeUpdated);
        return mapper.createFromBO(updatedBusinessObject);
    }

    protected void doDeletePresenter(V presentationObject) {
        manager.delete(mapper.createBO(presentationObject));
    }

    protected V doDeletePresenter(I id) {
        E deletedBusinessObject = doDelete(id);
        return mapper.createFromBO(deletedBusinessObject);
    }

    protected Collection<V> doListPresenter() {
        Collection<E> boList = doList();
        return mapper.createFromBO(boList);
    }

    protected Collection<V> doSearchPresenter(Query query) {
        Collection<E> boList = doSearch(query);
        return mapper.createFromBO(boList);
    }
}
