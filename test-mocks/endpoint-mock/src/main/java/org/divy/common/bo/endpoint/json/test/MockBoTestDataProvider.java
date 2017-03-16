package org.divy.common.bo.endpoint.json.test;

import org.divy.common.bo.query.AttributeQuery;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.query.operator.comparison.impl.EqualsComparisonImpl;
import org.divy.common.bo.test.ITestDataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class MockBoTestDataProvider implements
        ITestDataProvider<MockBoJerseyEndpoint.MockEntity> {

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.ITestDataProvider#modifyEntityWithTestData(java.lang.Object)
     */
    @Override
    public void modifyEntityWithTestData(MockBoJerseyEndpoint.MockEntity businessObject) {
        businessObject.setName("updatedData1");

        MockBoJerseyEndpoint.MockEntity childBusinessObject = getEntityInstance();

        childBusinessObject.setName("NewChildAddedDuringUpdate");

        childBusinessObject.setParentEntity(businessObject);

        businessObject.getChildEntities().add(childBusinessObject);
    }

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.ITestDataProvider#fillTestDataSet1(java.lang.Object)
     */
    @Override
    public void fillTestDataSet1(MockBoJerseyEndpoint.MockEntity businessObject) {

        businessObject.setName("data1");

        MockBoJerseyEndpoint.MockEntity childBusinessObject = new MockBoJerseyEndpoint.MockEntity();

        childBusinessObject.setName("child1");

        childBusinessObject.setParentEntity(businessObject);

        List<MockBoJerseyEndpoint.MockEntity> childLists = new ArrayList<>();

        childLists.add(childBusinessObject);

        businessObject.setChildEntities(childLists);

    }

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.ITestDataProvider#fillTestDataSet2(java.lang.Object)
     */
    @Override
    public void fillTestDataSet2(MockBoJerseyEndpoint.MockEntity businessObject) {
        businessObject.setName("data2");

        MockBoJerseyEndpoint.MockEntity childBusinessObject = new MockBoJerseyEndpoint.MockEntity();

        childBusinessObject.setName("child2");

        childBusinessObject.setParentEntity(businessObject);

        List<MockBoJerseyEndpoint.MockEntity> childLists = new ArrayList<>();

        childLists.add(childBusinessObject);

        businessObject.setChildEntities(childLists);
    }

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.ITestDataProvider#getEntityInstance()
     */
    @Override
    public MockBoJerseyEndpoint.MockEntity getEntityInstance() {
        return new MockBoJerseyEndpoint.MockEntity();
    }

    @Override
    public void initialize() {
        //No thing to initialize
    }


    @Override
    public Query createSearchQuery() {
        AttributeQuery userQuery = new AttributeQuery();

        userQuery.put("name",new EqualsComparisonImpl<>("data1"));

        return userQuery;
    }

}
