/**
 * 
 */
package org.divy.common.bo.service.json.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.divy.common.bo.database.mock.MockEntity;
import org.junit.After;
import org.junit.Test;

import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public abstract class AbstractBOServiceTest extends JerseyTest {

	/**
	 * @throws TestContainerException
	 */
	public AbstractBOServiceTest() throws TestContainerException {
		super();
	}

	/**
	 * @param testContainerFactory
	 */
	public AbstractBOServiceTest(TestContainerFactory testContainerFactory) {
		super(testContainerFactory);
	}

	/**
	 * @param ad
	 * @throws TestContainerException
	 */
	public AbstractBOServiceTest(AppDescriptor ad) throws TestContainerException {
		super(ad);
	}

	/**
	 * @param packages
	 * @throws TestContainerException
	 */
	public AbstractBOServiceTest(String... packages) throws TestContainerException {
		super(packages);
	}

	@Test
	public void testCreate() {
	
		MockEntity businessObject = new MockEntity();
		MockEntity childBusinessObject = new MockEntity();
	
		childBusinessObject.setParentEntity(businessObject);
	
		List<MockEntity> childLists = new ArrayList<MockEntity>();
	
		childLists.add(childBusinessObject);
	
		businessObject.setChildEntities(childLists);
		
		MockEntity response = doCreateEntity(businessObject);
		
		assertThat("Response should not be null", response, not(nullValue()));
	}

	@Test
	public void testUpdate() {
	
		MockEntity businessObject = new MockEntity();
		
		List<MockEntity> childLists = new ArrayList<MockEntity>();
	
		MockEntity childBusinessObject = new MockEntity();
	
		childBusinessObject.setParentEntity(businessObject);
	
		childLists.add(childBusinessObject);
	
		businessObject.setChildEntities(childLists);
	
		businessObject = doCreateEntity(businessObject);
	
		businessObject = doGetByKey(businessObject.getUuid());
	
		businessObject = doUpdateEntity(businessObject);
	
	}

	@Test
	public void testDelete() {
	
		MockEntity businessObject = new MockEntity();
	
		MockEntity childBusinessObject = new MockEntity();
		childBusinessObject.setParentEntity(businessObject);
		List<MockEntity> childLists = new ArrayList<MockEntity>();
		childLists.add(childBusinessObject);
		businessObject.setChildEntities(childLists);
	
		businessObject = doCreateEntity(businessObject);
	
		doDeleteEntity(businessObject);
		
		List<MockEntity> boList = doGetAllEntity();
	
		assertThat(boList.size(), equalTo(0));
	}
	
	@After
	public void cleanup()
	{
		List<MockEntity> boList = doGetAllEntity();
		
		for (Iterator<MockEntity> iterator = boList.iterator(); iterator.hasNext();) {

			MockEntity mockEntity = iterator.next();

			try{
				doDeleteEntity(mockEntity);
			}
			catch(Exception ex)
			{}
		}
	}

	protected abstract MockEntity doCreateEntity(MockEntity businessObject);

	protected abstract void doDeleteEntity(MockEntity businessObject);

	protected abstract List<MockEntity> doGetAllEntity();

	protected abstract MockEntity doGetByKey(String key);

	protected abstract MockEntity doUpdateEntity(MockEntity businessObject);

}