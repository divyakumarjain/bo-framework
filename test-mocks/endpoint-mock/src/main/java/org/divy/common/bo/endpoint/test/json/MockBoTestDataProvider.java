package org.divy.common.bo.endpoint.test.json;

import org.divy.common.bo.query.AttributeQuery;
import org.divy.common.bo.query.Query;
import org.divy.common.bo.query.operator.comparison.OperatorFactory;
import org.divy.common.bo.test.TestDataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class MockBoTestDataProvider implements
        TestDataProvider<MockBoJerseyEndpoint.MockEntity> {

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.TestDataProvider#modifyEntityWithTestData(java.lang.Object)
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
     * @see org.divy.common.bo.test.TestDataProvider#fillTestDataSet1(java.lang.Object)
     */
    @Override
    public void fillTestDataSet1(MockBoJerseyEndpoint.MockEntity businessObject) {

        businessObject.setName("data1");

        var childBusinessObject = new MockBoJerseyEndpoint.MockEntity();

        childBusinessObject.setName("child1");

        childBusinessObject.setParentEntity(businessObject);

        List<MockBoJerseyEndpoint.MockEntity> childLists = new ArrayList<>();

        childLists.add(childBusinessObject);

        businessObject.setChildEntities(childLists);

    }

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.TestDataProvider#fillTestDataSet2(java.lang.Object)
     */
    @Override
    public void fillTestDataSet2(MockBoJerseyEndpoint.MockEntity businessObject) {
        businessObject.setName("data2");

        var childBusinessObject = new MockBoJerseyEndpoint.MockEntity();

        childBusinessObject.setName("child2");

        childBusinessObject.setParentEntity(businessObject);

        List<MockBoJerseyEndpoint.MockEntity> childLists = new ArrayList<>();

        childLists.add(childBusinessObject);

        businessObject.setChildEntities(childLists);
    }

    /* (non-Javadoc)
     * @see org.divy.common.bo.test.TestDataProvider#getEntityInstance()
     */
    @Override
    public MockBoJerseyEndpoint.MockEntity getEntityInstance() {
        return new MockBoJerseyEndpoint.MockEntity();
    }



    @Override
    public Query createSearchQuery() {
        var userQuery = new AttributeQuery();

        userQuery.put("name", OperatorFactory.equalsComparison("data1"));

        return userQuery;
    }

}
