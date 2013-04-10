/**
 * 
 */
package org.divy.common.bo.service.json;

import javax.ws.rs.Path;

import org.divy.common.bo.database.mock.MockBODBManager;
import org.divy.common.bo.database.mock.MockEntity;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
@Path("/mock")
public class MockBoService extends AbstractBOService<MockEntity, String> {

	/**
	 * @param manager
	 */
	public MockBoService() {

		super(new MockBODBManager());
	}

}
