package org.divy.common.bo.service.json;


import java.util.List;

import javax.ws.rs.core.MediaType;

import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.service.json.test.AbstractBOServiceTest;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource.Builder;

public class MockBOServiceTest extends
		AbstractBOServiceTest<MockEntity, String> {

	public MockBOServiceTest() throws Exception {
		super(new MockBoTestDataProvider());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.divy.common.bo.service.json.test.AbstractBOServiceTest#
	 * getTestClassPackage()
	 */
	@Override
	protected String getTestClassPackage() {
		return "org.divy.common.bo.service.json";
	}

	@Override
	protected Builder getEntityKeyPath(String key) {
		return resource().path("mock")
								   .path(key)
								   .accept(MediaType.APPLICATION_JSON_TYPE)
								   .type(MediaType.APPLICATION_JSON_TYPE);
	}

	@Override
	public Builder getEntityPath() {
		return resource()
				.path("mock")
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE);
	}

	@Override
	protected GenericType<MockEntity> getEntityClass() {
		return new GenericType<MockEntity>() {
		};
	}

	@Override
	protected GenericType<List<MockEntity>> getEntityListClass() {
		return new GenericType<List<MockEntity>>() {
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.divy.common.bo.test.TestBaseManager#extendedTestCreatedEntity(org
	 * .divy.common.bo.IBusinessObject)
	 */
	@Override
	protected void extendedTestCreatedEntity(MockEntity entity) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.divy.common.bo.test.TestBaseManager#extendedTestUpdatedEntity(org
	 * .divy.common.bo.IBusinessObject)
	 */
	@Override
	protected void extendedTestUpdatedEntity(MockEntity entity) {
		// TODO Auto-generated method stub

	}
}