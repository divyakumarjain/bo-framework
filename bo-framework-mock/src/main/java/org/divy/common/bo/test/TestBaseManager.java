/**
 * 
 */
package org.divy.common.bo.test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IQuery;
import org.hamcrest.core.IsNull;
import org.junit.Test;

/**
 * @author divyakumar.a.jain@hp.com
 *
 * @param <ENTITY>
 * @param <ID>
 */
public abstract class TestBaseManager<ENTITY extends IBusinessObject<ID>, ID> {

	final protected ITestDataProvider<ENTITY, ID> testDataProvider;

	/**
	 * 
	 */
	public TestBaseManager(ITestDataProvider<ENTITY, ID> testDataProvider) {
		super();
		this.testDataProvider = testDataProvider;
	}

	@Test
	public void testCreate() {
		ENTITY entity = testDataProvider.getEntityInstance();
	
		entity = doCreateEntity(entity);

		assertThat("Entity should be returned after creation", entity,
				notNullValue());
	
		assertThat("Id for Entity should be generate after creation",
				entity.identity(),
				notNullValue());
		
		entity = doGetByKey(entity.identity());

		assertThat("Entity should be readable after creation", entity,notNullValue());
		
		extendedTestCreatedEntity(entity);
	
	}

	@Test
	public void testUpdate() {
		ENTITY entity = testDataProvider.getEntityInstance();
	
		entity = doCreateEntity(entity);
		
		entity = doGetByKey(entity.identity());
		
		testDataProvider.modifyEntityWithTestData(entity);

		doUpdateEntity(entity);
		
		ID id = entity.identity();
		
		entity = doGetByKey(id);
		
		extendedTestUpdatedEntity(entity);
	}

	@Test
	public void testDelete() {
		ENTITY entity1 = testDataProvider.getEntityInstance();
		ENTITY entity2 = testDataProvider.getEntityInstance();
	
		testDataProvider.fillTestDataSet1(entity1);
		testDataProvider.fillTestDataSet2(entity2);
	
		entity1 = doCreateEntity(entity1);
		entity2 = doCreateEntity(entity2);
	
		doDeleteEntity(entity1);
	
		entity1 = doGetByKey(entity1.identity());
		entity2 = doGetByKey(entity2.identity());
	
		assertThat("Entity should not be Found", entity1, IsNull.nullValue());
		assertThat("Entity should be Found", entity2, notNullValue());
	}

	@Test
	public void testSearch() {
		ENTITY entity1 = testDataProvider.getEntityInstance();
	
		testDataProvider.fillTestDataSet1(entity1);

		doCreateEntity(entity1);
	
		IQuery<ENTITY> searchQuery = createSearchQuery();
	
		List<ENTITY> searchedEntities = doSearchEntities(searchQuery);
	
		assertThat("Search result should not be null", searchedEntities,
				notNullValue());
		assertFalse("Search result should not be empty",
				searchedEntities.isEmpty());
	}

	/* CRUD Operation */
	protected abstract ENTITY doCreateEntity(ENTITY entity);
	protected abstract void doUpdateEntity(ENTITY entity);
	protected abstract ENTITY doGetByKey(ID id);
	protected abstract void doDeleteEntity(ENTITY entity);
	protected abstract List<ENTITY> doSearchEntities(IQuery<ENTITY> searchQuery);

	/* Extended Tests */
	protected abstract void extendedTestCreatedEntity(ENTITY entity);
	protected abstract void extendedTestUpdatedEntity(ENTITY entity);

	protected abstract IQuery<ENTITY> createSearchQuery();

}