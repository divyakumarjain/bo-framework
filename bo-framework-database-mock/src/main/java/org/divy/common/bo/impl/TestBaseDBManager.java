package org.divy.common.bo.impl;



import java.util.List;

import org.divy.common.bo.IBOManager;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IQuery;
import org.divy.common.bo.context.DatabaseContext;
import org.junit.Before;


public abstract class TestBaseDBManager<ENTITY extends IBusinessObject<ID>, ID> extends TestBaseManager<ENTITY, ID>
{

	protected IBOManager<ENTITY,ID> boManager;

	@Before
	public void before() {
		context = new DatabaseContext(getPersistantUnitName());
	
		boManager = createManager();
	
	}

	/**
	 * @param entity
	 * @return
	 */
	@Override
	protected ID doCreateEntity(ENTITY entity) {
		fillTestDataSet1(entity);
	
		boManager.create(entity);
		
		ID id = getId(entity);
		return id;
	}

	protected abstract String getPersistantUnitName();

	protected abstract IBOManager<ENTITY, ID> createManager();

	@Override
	protected ENTITY doGetById(ID id) {
		ENTITY entity = boManager.get(id);
		return entity;
	}

	@Override
	protected void doModifyEntity(ENTITY entity) {
		modifyEntityWithTestData(entity);

		boManager.update(entity);
	}

	@Override
	protected void doDeleteEntity(ENTITY entity1) {
		boManager.delete(entity1);
	}

	@Override
	protected List<ENTITY> doSearchEntities(IQuery<ENTITY> searchQuery) {
		return boManager.search(searchQuery);
	}

	
}
