/**
 * 
 */
package org.divy.common.bo.endpoint.json.test;

import javax.ws.rs.Path;

import org.divy.common.bo.business.defaults.DefaultBOManager;
import org.divy.common.bo.database.mock.MockBODBRepository;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;
import org.divy.common.rest.RESTEntityURLBuilder;
import org.mockito.Mock;

/**
 * @author Divyakumar
 *
 */
@Path("/mock")
public class MockBoEndpoint extends AbstractBOEndpoint<MockEntity, String> {

    static {
        RESTEntityURLBuilder.addEntityEndPointMap(MockEntity.class,MockBoEndpoint.class);
    }


    public MockBoEndpoint() {
        super();
    }
}
