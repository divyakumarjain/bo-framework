package org.divy.common.bo.endpoint.test;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.endpoint.BaseBOEndpoint;
import org.divy.common.bo.test.TestBOCRUDBase;
import org.divy.common.bo.test.TestDataProvider;

import java.io.Serializable;


public abstract class AbstractBOEndpointTest<E extends BusinessObject<I>, I extends Serializable, R> extends TestBOCRUDBase<E, I> {

    /**
     *  @param testDataProvider data provider for the test
     */
    AbstractBOEndpointTest(TestDataProvider<E> testDataProvider) {
        super(testDataProvider);

    }

    protected abstract Class<? extends BaseBOEndpoint<E, I, R>> getEndPointClass();
}
