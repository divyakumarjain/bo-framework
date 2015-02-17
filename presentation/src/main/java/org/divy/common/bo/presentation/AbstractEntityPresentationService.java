package org.divy.common.bo.presentation;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.defaults.DefaultBOMapper;
import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.endpoint.AbstractCRUDEndpoint;

public abstract class AbstractEntityPresentationService<ENTITY extends IBusinessObject<ID>,VO, ID extends Serializable> extends AbstractCRUDEndpoint<ENTITY,ID> {

    IBOMapper<ENTITY, VO> mapper;

    private IBOManager<ENTITY, ID> manager;

    public IBOManager<ENTITY, ID> getManager() {
        return manager;
    }

    public void setManager(IBOManager<ENTITY, ID> manager) {
        this.manager = manager;
    }

    public AbstractEntityPresentationService(Class<ENTITY> businessObjectType, Class<VO> otherObjectType){
        mapper = createMapper(businessObjectType,otherObjectType);
    }

    public AbstractEntityPresentationService(IBOMapper<ENTITY, VO> mapper) {
        this.mapper = mapper;
    }

    protected IBOMapper<ENTITY, VO> createMapper(Class<ENTITY> businessObjectType, Class<VO> otherObjectType) {
        return new DefaultBOMapper<>(businessObjectType, otherObjectType);
    }

    @Override
    protected ENTITY doRead(ID id) {
        ENTITY boEntity = manager.get(id);

        return boEntity;
    }

    @Override
    protected ENTITY doCreate(ENTITY entity) {
        ENTITY createdBusinessObject = manager.create(entity);
        return createdBusinessObject;
    }

    @Override
    protected ENTITY doUpdate(ENTITY entity) {
        ENTITY updatedBusinessObject = manager.update(entity);
        return updatedBusinessObject;
    }

    @Override
    protected void doDelete(ENTITY entity) {
        manager.delete(entity);
    }

    @Override
    protected ENTITY doDelete(ID id) {
        ENTITY deletedBusinessObject = manager.deleteById(id);
        return deletedBusinessObject;
    }

    @Override
    protected Collection<ENTITY> doList() {
        List<ENTITY> boList = manager.list();
        return boList;
    }

    @Override
    protected Collection<ENTITY> doSearch(IQuery query) {
        List<ENTITY> boList = manager.search(query);
        return boList;
    }

    protected VO doGetPresenter(ID id) {
        ENTITY boEntity = doRead(id);
        return mapper.createFromBO(boEntity);
    }

    protected VO doCreatePresenter(VO presentationObject) {
        ENTITY entityToBeCreated= mapper.createBO(presentationObject);
        ENTITY createdBusinessObject = doCreate(entityToBeCreated);
        return mapper.createFromBO(createdBusinessObject);
    }

    protected VO doUpdatePresenter(VO presentationObject) {
        ENTITY entityToBeUpdated = mapper.createBO(presentationObject);
        ENTITY updatedBusinessObject = doUpdate(entityToBeUpdated);
        return mapper.createFromBO(updatedBusinessObject);
    }

    protected void doDeletePresenter(VO presentationObject) {
        manager.delete(mapper.createBO(presentationObject));
    }

    protected VO doDeletePresenter(ID id) {
        ENTITY deletedBusinessObject = doDelete(id);
        return mapper.createFromBO(deletedBusinessObject);
    }

    protected Collection<VO> doListPresenter() {
        Collection<ENTITY> boList = doList();
        return mapper.createFromBO(boList);
    }

    protected Collection<VO> doSearchPresenter(IQuery query) {
        Collection<ENTITY> boList = doSearch(query);
        return mapper.createFromBO(boList);
    }
}
