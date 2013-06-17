/**
 * 
 */
package org.divy.common.bo.service.json.test;

import javax.ws.rs.Path;

import org.divy.common.bo.database.mock.MockBODBRepository;
import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.service.AbstractBOService;

/**
 * @author Divyakumar
 *
 */
@Path("/mock")
public class MockBoService extends AbstractBOService<MockEntity, String> {

	public MockBoService() {

		super(new MockBODBRepository());
	}

}
