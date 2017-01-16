/**
 * 
 */
package org.divy.common.bo.database.mock;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

import org.divy.common.bo.query.Query;
import org.divy.common.bo.query.operator.comparison.impl.EqualsComparisonImpl;
import org.divy.common.bo.query.AttributeQuery;
import org.divy.common.bo.test.ITestDataProvider;

/**
 *
 *
 */
public class MockBoTestDataProvider implements
        ITestDataProvider<MockEntity> {

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.ITestDataProvider#modifyEntityWithTestData(java.lang.Object)
     */
    @Override
    public void modifyEntityWithTestData(MockEntity businessObject) {
        businessObject.setName("updatedData1");

        MockEntity childBusinessObject = getEntityInstance();

        childBusinessObject.setName("child2");

        childBusinessObject.setParentEntity(businessObject);

        businessObject.getChildEntities().add(childBusinessObject);
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

    @Override
    public void initialize() {
        //Nothing to initialize
    }


    @Override
    public Query createSearchQuery() {
        AttributeQuery userQuery = new AttributeQuery();

        userQuery.put("name",new EqualsComparisonImpl<String>("data1"));

        return userQuery;
    }

}
