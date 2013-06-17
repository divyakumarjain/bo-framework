package org.divy.common.bo.service.json;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.service.json.test.MockBoTestDataProvider;
import org.divy.common.bo.service.test.AbstractBOServiceTest;
import org.divy.common.bo.service.test.AbstractGuiceServletConfig;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource.Builder;

public class MockBOServiceTest extends
		AbstractBOServiceTest<MockEntity, String> {

	public MockBOServiceTest() throws Exception {
		super(new MockBoTestDataProvider());
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
		
		assertThat("Child Mock objects list should not be null",entity.getChildEntities(), not(nullValue()));
		assertThat("Child Mock object list should be one",entity.getChildEntities().size(), equalTo(1));
		assertThat("Child Mock object should not be null",entity.getChildEntities().get(0), not(nullValue()));

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
		assertThat("Child Mock objects list should not be null",entity.getChildEntities(), not(nullValue()));
		assertThat("Child Mock object list should be one",entity.getChildEntities().size(), equalTo(1));
		assertThat("Child Mock object should not be null",entity.getChildEntities().get(0), not(nullValue()));
	}
	
	@Override
	protected String getTestClassPackage() {
		return "org.divy.common.bo.service.json.test";
	}

	@Override
	protected Class<? extends AbstractGuiceServletConfig> getGuiceServletConfig() {
		// TODO Auto-generated method stub
		return MockBOGuiceServletConfig.class;
	}

	@Override
	protected String getIdentifier(MockEntity entity) {
		return entity.identity();
	}
}