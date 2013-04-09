/**
 * 
 */
package org.divy.common.bo.impl;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.IQuery;
import org.divy.common.bo.command.IDBCommandContext;
import org.hamcrest.core.IsNull;
import org.junit.Test;

/**
 * @author divyakumar.a.jain@hp.com
 *
 * @param <ENTITY>
 * @param <ID>
 */
public abstract class TestBaseManager<ENTITY extends IBusinessObject<ID>, ID> {

	protected IDBCommandContext context;

	/**
	 * 
	 */
	public TestBaseManager() {
		super();
	}

	@Test
	public void testCreate() {
		ENTITY entity = getEntityInstance();
	
		ID id = doCreateEntity(entity);
	
		assertThat("Id for Entity should be generate after creation", id,
				notNullValue());
		//assertThat("Id for Entity not be empty", id,not(isEmptyString()));
		
		entity = doGetById(getId(entity));

		id = getId(entity);
	
		assertThat("Entity should be readable after creation", entity,notNullValue());
		assertThat("Entity should be readable after creation", id,notNullValue());
		
		extendedTestCreatedEntity(entity);
	
	}

	@Test
	public void testUpdate() {
		ENTITY entity = getEntityInstance();
	
		ID id = doCreateEntity(entity);
		
		entity = doGetById(id);
		
		doModifyEntity(entity);
		
		id = getId(entity);
		
		entity = doGetById(id);
		
		extendedTestUpdatedEntity(entity);
	}

	@Test
	public void testDelete() {
		ENTITY entity1 = getEntityInstance();
		ENTITY entity2 = getEntityInstance();
	
		fillTestDataSet1(entity1);
		fillTestDataSet2(entity2);
		
	
	
		ID id1 = doCreateEntity(entity1);
		ID id2 = doCreateEntity(entity2);
	
		entity1 = doGetById(id1);
		entity2 = doGetById(id2);
	
	
		doDeleteEntity(entity1);
	
		entity1 = doGetById(id1);
		entity2 = doGetById(id2);
	
		assertThat("Entity should not be Found", entity1, IsNull.nullValue());
		assertThat("Entity should be Found", entity2, notNullValue());
	}

	@Test
	public void testSearch() {
		ENTITY entity1 = getEntityInstance();
	
		fillTestDataSet1(entity1);

		doCreateEntity(entity1);
	
		IQuery<ENTITY> searchQuery = createSearchQuery();
	
		List<ENTITY> searchedEntities = doSearchEntities(searchQuery);
	
		assertThat("Search result should not be null", searchedEntities,
				notNullValue());
		assertFalse("Search result should not be empty",
				searchedEntities.isEmpty());
	}

	/* CRUD Operation */
	protected abstract ID doCreateEntity(ENTITY entity);
	protected abstract void doModifyEntity(ENTITY entity);
	protected abstract ENTITY doGetById(ID id);
	protected abstract void doDeleteEntity(ENTITY entity);
	protected abstract List<ENTITY> doSearchEntities(IQuery<ENTITY> searchQuery);


	/* Methods for Test Data generation */
	protected abstract void modifyEntityWithTestData(ENTITY entity);
	protected abstract void fillTestDataSet1(ENTITY entity);
	protected abstract void fillTestDataSet2(ENTITY entity);

	/* Extended Tests */
	protected abstract void extendedTestCreatedEntity(ENTITY entity);
	protected abstract void extendedTestUpdatedEntity(ENTITY entity);

	/* Test Utility */
	protected abstract ID getId(ENTITY entity);
	protected abstract IQuery<ENTITY> createSearchQuery();
	protected abstract ENTITY getEntityInstance();

}