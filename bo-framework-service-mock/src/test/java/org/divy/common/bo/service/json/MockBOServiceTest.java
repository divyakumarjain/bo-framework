package org.divy.common.bo.service.json;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.divy.common.bo.database.mock.MockEntity;
import org.divy.common.bo.service.json.test.AbstractBOServiceTest;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainer;

/**
 * The class <code>AbstractBOServiceTest</code> contains tests for the class
 * {@link <code>AbstractBOService</code>}
 * 
 */

public class MockBOServiceTest extends AbstractBOServiceTest {

	public MockBOServiceTest() throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.jersey.test.framework.JerseyTest#configure()
	 */
	@Override
	protected AppDescriptor configure() {
		
		ClientConfig clientConfig = new DefaultClientConfig();
		
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		
		WebAppDescriptor ad = new WebAppDescriptor.Builder("com.fasterxml.jackson.jaxrs.json;org.divy.common.bo.service.json")
													.clientConfig(clientConfig)
//													.initParam(com.sun.jersey.api.container.filter.LoggingFilter.FEATURE_LOGGING_DISABLE_ENTITY, "false")
													.build();

		return ad;
	}


	@Override
	protected Client getClient(TestContainer tc, AppDescriptor ad) {
		Client client = super.getClient(tc, ad);
		client.addFilter(new LoggingFilter());
		return client;
	}

	@Override
	protected void doDeleteEntity(MockEntity businessObject) {
		getEntityKeyPath(businessObject.getUuid()).delete();
	}

	@Override
	protected List<MockEntity> doGetAllEntity() {
		return getEntityPath().get(new GenericType<List<MockEntity>>() {});
	}

	@Override
	protected MockEntity doCreateEntity(MockEntity businessObject) {
		return getEntityPath().post(MockEntity.class,businessObject);
	}
	
	@Override
	protected MockEntity doGetByKey(String key) {
		return getEntityKeyPath(key).get(MockEntity.class);
	}

	/**
	 * @param key
	 * @return
	 */
	protected Builder getEntityKeyPath(String key) {
		return resource().path("mock")
								   .path(key)
								   .accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE);
	}
	
	@Override
	protected MockEntity doUpdateEntity(MockEntity businessObject) {
		return getEntityPath().put(MockEntity.class, businessObject);
	}

	protected Builder getEntityPath() {
		return resource()
				.path("mock")
				.accept(MediaType.APPLICATION_JSON_TYPE,
						MediaType.APPLICATION_XML_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE);
	}
}