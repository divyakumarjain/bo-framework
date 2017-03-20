package org.divy.common.bo.endpoint.test;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBOCRUDBase;

import java.io.Serializable;


public abstract class AbstractBOEndpointTest<E extends IBusinessObject<I>, I extends Serializable> extends TestBOCRUDBase<E, I> {

    /**
     *  @param testDataProvider
     */
    public AbstractBOEndpointTest(ITestDataProvider<E> testDataProvider) {
        super(testDataProvider);

    }

    protected abstract Class<? extends BaseBOEndpoint<E, I>> getEndPointClass();
}
