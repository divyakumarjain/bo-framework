/**
 * 
 */
package org.divy.common.bo.service.json;

import java.util.ArrayList;
import java.util.List;

import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.test.ITestDataProvider;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class MockBoTestDataProvider implements
		ITestDataProvider<MockEntity, String> {

	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.ITestDataProvider#modifyEntityWithTestData(java.lang.Object)
	 */
	@Override
	public void modifyEntityWithTestData(MockEntity businessObject) {
		businessObject.setName("updatedData1");
	}

	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.ITestDataProvider#fillTestDataSet1(java.lang.Object)
	 */
	@Override
	public void fillTestDataSet1(MockEntity businessObject) {

		businessObject.setName("data1");

		MockEntity childBusinessObject = new MockEntity();

		childBusinessObject.setName("child1");

		childBusinessObject.setParentEntity(businessObject);

		List<MockEntity> childLists = new ArrayList<MockEntity>();

		childLists.add(childBusinessObject);

		businessObject.setChildEntities(childLists);
		
	}

	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.ITestDataProvider#fillTestDataSet2(java.lang.Object)
	 */
	@Override
	public void fillTestDataSet2(MockEntity businessObject) {
		businessObject.setName("data2");

		MockEntity childBusinessObject = new MockEntity();

		childBusinessObject.setName("child2");

		childBusinessObject.setParentEntity(businessObject);

		List<MockEntity> childLists = new ArrayList<MockEntity>();

		childLists.add(childBusinessObject);

		businessObject.setChildEntities(childLists);
	}

	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.ITestDataProvider#getEntityInstance()
	 */
	@Override
	public MockEntity getEntityInstance() {
		return new MockEntity();
	}

}
