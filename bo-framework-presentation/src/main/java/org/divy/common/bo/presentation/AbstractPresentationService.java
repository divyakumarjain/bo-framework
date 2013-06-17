package org.divy.common.bo.presentation;

import java.io.Serializable;
import java.util.List;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.business.IBOManager;
import org.divy.common.bo.mapper.IBOMapper;
import org.divy.common.bo.mapper.impl.DefaultBOMapper;
import org.divy.common.bo.service.AbstractCRUDService;

public abstract class AbstractPresentationService<ENTITY extends IBusinessObject<ID>,VO, ID extends Serializable> extends AbstractCRUDService<VO,ID> {
	
	IBOMapper<ENTITY, VO> mapper;
	
	private IBOManager<ENTITY, ID> manager;

	public IBOManager<ENTITY, ID> getManager() {
		return manager;
	}
	
	public void setManager(IBOManager<ENTITY, ID> manager) {
		this.manager = manager;
	}

	public AbstractPresentationService(Class<ENTITY> businessObjectType, Class<VO> otherObjectType){
		mapper = createMapper(businessObjectType,otherObjectType);
	}

	public AbstractPresentationService(IBOMapper<ENTITY, VO> mapper) {
		this.mapper = mapper;
	}

	protected IBOMapper<ENTITY, VO> createMapper(Class<ENTITY> businessObjectType, Class<VO> otherObjectType) {
		return new DefaultBOMapper<>(businessObjectType, otherObjectType);
	}
	
	@Override
	protected VO doGet(ID id) {
		ENTITY boEntity = manager.get(id);
		
		return mapper.createFromBO(boEntity);
	}

	@Override
	protected VO doCreate(VO presentationObject) {
		ENTITY createdBusinessObject = manager.create(mapper.createBO(presentationObject));
		return mapper.createFromBO(createdBusinessObject);
	}

	@Override
	protected VO doUpdate(VO presentationObject) {
		ENTITY updatedBusinessObject = manager.update(mapper.createBO(presentationObject));
		return mapper.createFromBO(updatedBusinessObject);
	}

	@Override
	protected void doDelete(VO presentationObject) {
		manager.delete(mapper.createBO(presentationObject));
	}

	@Override
	protected VO doDelete(ID id) {
		ENTITY deletedBusinessObject = manager.deleteById(id);
		return mapper.createFromBO(deletedBusinessObject);
	}

	@Override
	protected List<VO> doList() {
		List<ENTITY> boList = manager.list();
		return mapper.createFromBO(boList);
	}
}
