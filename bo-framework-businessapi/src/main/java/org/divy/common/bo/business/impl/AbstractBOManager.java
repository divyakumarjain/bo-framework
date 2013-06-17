package org.divy.common.bo.business.impl;

import java.util.List;

import org.divy.common.bo.IBORepository;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IQuery;
import org.divy.common.bo.business.IBOManager;

public class AbstractBOManager<ENTITY extends IBusinessObject<ID>, ID> implements IBOManager<ENTITY,ID>{
	
	IBORepository<ENTITY, ID> repository;
	
	public AbstractBOManager(IBORepository<ENTITY, ID> repository){
		this.repository = repository;
	}
	
	public AbstractBOManager(){
		this.repository = createRepository();
	}
	
	protected IBORepository<ENTITY, ID> createRepository() {
		return null;
	}

	@Override
	public ENTITY create(ENTITY businessObject) {
		return repository.create(businessObject);
	}

	@Override
	public ENTITY update(ENTITY businessObject) {
		return repository.update(businessObject);
	}

	@Override
	public ENTITY delete(ENTITY businessObject) {
		return repository.delete(businessObject);
	}

	@Override
	public List<ENTITY> list() {
		return repository.list();
	}

	@Override
	public List<ENTITY> search(IQuery<ENTITY> businessObjectQuery) {
		return repository.search(businessObjectQuery);
	}

	@Override
	public ENTITY deleteById(ID id) {
		return repository.deleteById(id);
	}

	@Override
	public ENTITY get(ID identity) {
		return repository.get(identity);
	}

}
