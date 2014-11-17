/**
 * 
 */
package org.divy.common.bo.service.json.test;

import javax.ws.rs.Path;

import org.divy.common.bo.business.defaults.DefaultBOManager;
import org.divy.common.bo.database.mock.MockBODBRepository;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.service.AbstractBOEndpoint;

/**
 * @author Divyakumar
 *
 */
@Path("/mock")
public class MockBoEndpoint extends AbstractBOEndpoint<MockEntity, String> {

    public MockBoEndpoint() {

        super(new DefaultBOManager<MockEntity,String>(new MockBODBRepository()));
    }
}
