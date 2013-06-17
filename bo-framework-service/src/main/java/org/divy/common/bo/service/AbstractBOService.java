/**
 * 
 */
package org.divy.common.bo.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;

/**
 * 
 * @author Divyakumar
 *
 */
public abstract class AbstractBOService<ENTITY extends IBusinessObject<ID>, ID extends Serializable> extends AbstractCRUDService<ENTITY, ID> {

	private IBORepository<ENTITY, ID> repository;

	public IBORepository<ENTITY, ID> getManager() {
		return repository;
	}

	public void setManager(IBORepository<ENTITY, ID> repository) {
		this.repository = repository;
	}

	public AbstractBOService(IBORepository<ENTITY, ID> repository) {
		this.repository = repository;
	}

	public AbstractBOService() {
	}

	protected ENTITY doCreate(ENTITY businessObject) {
		return repository.create(businessObject);
	}

	protected ENTITY doUpdate(ENTITY businessObject) {
		
		ENTITY updatedBo = repository.update(businessObject);
		
		return updatedBo;
	}

	protected void doDelete(ENTITY businessObject) {

		repository.delete(businessObject);

	}

	protected ENTITY doDelete(ID id) {
		
		return repository.deleteById(id);
		
	}

	protected List<ENTITY> doList() {
		
		List<ENTITY> boList = repository.list();

		List<ENTITY> resultList = new ArrayList<ENTITY>();

		resultList.addAll(boList);
		
		return resultList;

	}
	
	protected String getIdentity(ENTITY createdBo) {
		return createdBo.identity().toString();
	}

	protected ENTITY doGet(ID id) {
		return repository.get(id);


	}
}
