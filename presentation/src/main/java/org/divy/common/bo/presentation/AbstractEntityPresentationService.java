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

public abstract class AbstractEntityPresentationService<E extends IBusinessObject<I>, VO, I extends Serializable> extends AbstractCRUDEndpoint<E, I> {

    final IBOMapper<E, VO> mapper;

    private IBOManager<E, I> manager;

    @Inject
    public AbstractEntityPresentationService(IBOMapper<E, VO> mapper, LinkBuilderFactoryImpl linkBuilderFactory) {
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

    protected VO doGetPresenter(I id) {
        E boEntity = doRead(id);
        return mapper.createFromBO(boEntity);
    }

    protected VO doCreatePresenter(VO presentationObject) {
        E entityToBeCreated = mapper.createBO(presentationObject);
        E createdBusinessObject = doCreate(entityToBeCreated);
        return mapper.createFromBO(createdBusinessObject);
    }

    protected VO doUpdatePresenter(VO presentationObject) {
        E entityToBeUpdated = mapper.createBO(presentationObject);
        E updatedBusinessObject = doUpdate(entityToBeUpdated.identity(), entityToBeUpdated);
        return mapper.createFromBO(updatedBusinessObject);
    }

    protected void doDeletePresenter(VO presentationObject) {
        manager.delete(mapper.createBO(presentationObject));
    }

    protected VO doDeletePresenter(I id) {
        E deletedBusinessObject = doDelete(id);
        return mapper.createFromBO(deletedBusinessObject);
    }

    protected Collection<VO> doListPresenter() {
        Collection<E> boList = doList();
        return mapper.createFromBO(boList);
    }

    protected Collection<VO> doSearchPresenter(Query query) {
        Collection<E> boList = doSearch(query);
        return mapper.createFromBO(boList);
    }
}
