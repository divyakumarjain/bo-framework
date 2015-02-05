package org.divy.common.bo.endpoint.test;

import java.io.Serializable;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBOCRUDBase;


public abstract class AbstractBOEndpointTest<ENTITY extends IBusinessObject<ID>, ID extends Serializable> extends TestBOCRUDBase<ENTITY,ID> {

    /**
     *  @param testDataProvider
     */
    public AbstractBOEndpointTest(ITestDataProvider<ENTITY, ID> testDataProvider) {
        super(testDataProvider);

    }

    protected abstract Class<? extends AbstractBOEndpoint<ENTITY, ID>> getEndPointClass();
}
