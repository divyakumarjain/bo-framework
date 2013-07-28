/**
 * 
 */
package org.divy.common.bo.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.List;

import org.divy.common.bo.query.IQuery;
import org.divy.common.bo.query.defaults.Query;
import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Test;

/**
 * @author Divyakumar
 *
 * @param <ENTITY>
 * @param <ID>
 */
public abstract class TestBOCRUDBase<ENTITY, ID> {

	final protected ITestDataProvider<ENTITY, ID> testDataProvider;

	/**
	 * 
	 */
	public TestBOCRUDBase(ITestDataProvider<ENTITY, ID> testDataProvider) {
		super();
		this.testDataProvider = testDataProvider;
	}

	@Test
	public void testCreate() {
		ENTITY entity = testDataProvider.getEntityInstance();
		
		testDataProvider.fillTestDataSet1(entity);
	
		entity = doCreateEntity(entity);

		assertThat("Entity should be returned after creation", entity,
				notNullValue());
	
		assertThat("Id for Entity should be generate after creation",
				getIdentifier(entity),
				notNullValue());
		
		entity = doGetByKey(getIdentifier(entity));

		assertThat("Entity should be readable after creation", entity,notNullValue());
		
		extendedTestCreatedEntity(entity);
	
	}

	abstract protected ID getIdentifier(ENTITY entity);

	@Test
	public void testUpdate() {
		ENTITY entity = testDataProvider.getEntityInstance();
		
		testDataProvider.fillTestDataSet2(entity);
	
		entity = doCreateEntity(entity);
		
		entity = doGetByKey(getIdentifier(entity));
		
		testDataProvider.modifyEntityWithTestData(entity);

		doUpdateEntity(entity);
		
		ID id = getIdentifier(entity);
		
		ENTITY updatedEntity = doGetByKey(id);
		
		assertThat("Updated Entity should be same",updatedEntity,equalTo(entity));
		
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
	
		entity1 = doGetByKey(getIdentifier(entity1));
		entity2 = doGetByKey(getIdentifier(entity2));
	
		assertThat("Entity should not be Found", entity1, IsNull.nullValue());
		assertThat("Entity should be Found", entity2, notNullValue());
	}

	@Test
	public void testSearch() {
		ENTITY entity1 = testDataProvider.getEntityInstance();
	
		testDataProvider.fillTestDataSet1(entity1);

		doCreateEntity(entity1);
		
		ENTITY entity2 = testDataProvider.getEntityInstance();
		
		testDataProvider.fillTestDataSet2(entity2);

		doCreateEntity(entity2);
	
		IQuery searchQuery = testDataProvider.createSearchQuery();
	
		List<ENTITY> searchedEntities = doSearchEntities(searchQuery);
	
		assertThat("Search result should not be null", searchedEntities,
				notNullValue());
		
		assertFalse("Search result should not be empty",
				searchedEntities.isEmpty());

		assertThat("Search result should be single", searchedEntities.size(),
				equalTo(1));
		
	}
	
	/* Clean up */
	@After
	public void cleanup() throws Exception{
		List<ENTITY> searchedEntities = doSearchEntities(new Query());

		for (Iterator<ENTITY> iterator = searchedEntities.iterator(); iterator.hasNext();) {
			
			try {
				
				ENTITY entity = iterator.next();
				doDeleteEntity(entity);
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/* CRUD Operation */
	protected abstract ENTITY doCreateEntity(ENTITY entity);
	protected abstract void doUpdateEntity(ENTITY entity);
	protected abstract ENTITY doGetByKey(ID id);
	protected abstract void doDeleteEntity(ENTITY entity);
	protected abstract List<ENTITY> doSearchEntities(IQuery searchQuery);

	/* Extended Tests */
	protected abstract void extendedTestCreatedEntity(ENTITY entity);
	protected abstract void extendedTestUpdatedEntity(ENTITY entity);

}