package org.divy.common.bo.presentation;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.defaults.DefaultBOMapper;
import org.divy.common.bo.query.IQuery;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class AbstractEntityPresentationService<E extends IBusinessObject<I>, VO, I extends Serializable> extends AbstractCRUDEndpoint<E, I> {

    IBOMapper<E, VO> mapper;

    private IBOManager<E, I> manager;

    public AbstractEntityPresentationService(Class<E> businessObjectType, Class<VO> otherObjectType) {
        mapper = createMapper(businessObjectType, otherObjectType);
    }

    public AbstractEntityPresentationService(IBOMapper<E, VO> mapper) {
        this.mapper = mapper;
    }

    public IBOManager<E, I> getManager() {
        return manager;
    }

    public void setManager(IBOManager<E, I> manager) {
        this.manager = manager;
    }

    protected IBOMapper<E, VO> createMapper(Class<E> businessObjectType, Class<VO> otherObjectType) {
        return new DefaultBOMapper<>(businessObjectType, otherObjectType);
    }

    @Override
    protected E doRead(I id) {
        E boEntity = manager.get(id);

        return boEntity;
    }

    @Override
    protected E doCreate(E entity) {
        E createdBusinessObject = manager.create(entity);
        return createdBusinessObject;
    }

    @Override
    protected E doUpdate(E entity) {
        E updatedBusinessObject = manager.update(entity);
        return updatedBusinessObject;
    }

    @Override
    protected void doDelete(E entity) {
        manager.delete(entity);
    }

    @Override
    protected E doDelete(I id) {
        E deletedBusinessObject = manager.deleteById(id);
        return deletedBusinessObject;
    }

    @Override
    protected Collection<E> doList() {
        List<E> boList = manager.list();
        return boList;
    }

    @Override
    protected Collection<E> doSearch(IQuery query) {
        List<E> boList = manager.search(query);
        return boList;
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
        E updatedBusinessObject = doUpdate(entityToBeUpdated);
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

    protected Collection<VO> doSearchPresenter(IQuery query) {
        Collection<E> boList = doSearch(query);
        return mapper.createFromBO(boList);
    }
}
