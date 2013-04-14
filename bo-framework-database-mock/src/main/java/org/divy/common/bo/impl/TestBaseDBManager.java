package org.divy.common.bo.impl;



import java.util.List;

import org.divy.common.bo.IBOManager;
import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IQuery;
import org.divy.common.bo.command.IDBCommandContext;
import org.divy.common.bo.context.DatabaseContext;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBaseManager;
import org.junit.Before;


public abstract class TestBaseDBManager<ENTITY extends IBusinessObject<ID>, ID> extends TestBaseManager<ENTITY, ID>
{

	/**
	 * @param testDataProvider
	 */
	public TestBaseDBManager(ITestDataProvider<ENTITY, ID> testDataProvider) {
		super(testDataProvider);
	}

	protected IBOManager<ENTITY,ID> boManager;
	protected IDBCommandContext context;

	@Before
	public void before() {
		context = new DatabaseContext(getPersistantUnitName());
	
		boManager = createManager();
	
	}

	@Override
	protected ENTITY doCreateEntity(ENTITY entity) {
		testDataProvider.fillTestDataSet1(entity);
	
		ENTITY createdEntity = boManager.create(entity);
		
		return createdEntity;
	}

	@Override
	protected ENTITY doGetByKey(ID id) {
		return boManager.get(id);
	}

	@Override
	protected void doUpdateEntity(ENTITY entity) {
		testDataProvider.modifyEntityWithTestData(entity);

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

	protected abstract String getPersistantUnitName();

	protected abstract IBOManager<ENTITY, ID> createManager();
}
