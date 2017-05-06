package org.divy.common.bo.endpoint.test;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBOCRUDBase;

import java.io.Serializable;


public abstract class AbstractBOEndpointTest<E extends IBusinessObject<I>, I extends Serializable, R> extends TestBOCRUDBase<E, I> {

    /**
     *  @param testDataProvider data provider for the test
     */
    AbstractBOEndpointTest(ITestDataProvider<E> testDataProvider) {
        super(testDataProvider);

    }

    protected abstract Class<? extends BaseBOEndpoint<E, I, R>> getEndPointClass();
}
