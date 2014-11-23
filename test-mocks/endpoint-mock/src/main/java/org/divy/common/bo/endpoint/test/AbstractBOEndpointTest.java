package org.divy.common.bo.endpoint.test;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBOCRUDBase;

/**
 * Created by divyjain on 11/30/2014.
 */
public abstract class AbstractBOEndpointTest<ENTITY extends IBusinessObject<ID>, ID> extends TestBOCRUDBase<ENTITY,ID>{

    protected static final String MOCK_BASE_SERVER_PATH = "http://mockserver:mockport";

    /**
     *  @param testDataProvider
     */
    public AbstractBOEndpointTest(ITestDataProvider<ENTITY, ID> testDataProvider) {
        super(testDataProvider);

    }

    protected abstract Class<? extends AbstractBOEndpoint> getEndPointClass();
}
