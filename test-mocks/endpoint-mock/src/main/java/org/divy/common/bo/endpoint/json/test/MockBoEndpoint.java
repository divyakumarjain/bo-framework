/**
 * 
 */
package org.divy.common.bo.endpoint.json.test;

import java.util.UUID;
import javax.ws.rs.Path;

import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.endpoint.AbstractBOEndpoint;

/**
 * @author Divyakumar
 *
 */
@Path("/mock")
public class MockBoEndpoint extends AbstractBOEndpoint<MockEntity, UUID> {

    public MockBoEndpoint() {
        super();
    }
}
