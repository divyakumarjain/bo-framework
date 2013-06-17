/**
 * 
 */
package org.divy.common.bo.service.test;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.divy.common.bo.IQuery;
import org.divy.common.bo.service.HibernateBOMapperProvider;
import org.divy.common.bo.test.ITestDataProvider;
import org.divy.common.bo.test.TestBOCRUDBase;
import org.junit.After;
import org.junit.Before;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainer;

/**
 * @author Divyakumar
 *
 */
public abstract class AbstractBOServiceTest<ENTITY, ID>
		extends TestBOCRUDBase<ENTITY, ID> {

	/**
	 * @param testDataProvider
	 */
	public AbstractBOServiceTest(ITestDataProvider<ENTITY, ID> testDataProvider) {
		super(testDataProvider);
		jerseyTest = new JerseyTestProxy();
	}

	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.TestBaseManager#doCreateEntity(org.divy.common.bo.IBusinessObject)
	 */
	@Override
	protected ENTITY doCreateEntity(ENTITY entity) {
		return getEntityPath().post(getEntityClass(), entity);
	}

	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.TestBaseManager#doModifyEntity(org.divy.common.bo.IBusinessObject)
	 */
	@Override
	protected void doUpdateEntity(ENTITY entity) {
		getEntityPath().put(getEntityClass(), entity);
	}

	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.TestBaseManager#doGetById(java.lang.Object)
	 */
	@Override
	protected ENTITY doGetByKey(ID id) {
		try{
			return getEntityKeyPath(id).get(getEntityClass());
		}catch(UniformInterfaceException ex){
			if(ex.getResponse().getStatus() == Status.NOT_FOUND.getStatusCode()){
				return null;
			}
			throw ex;
		}
	}

	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.TestBaseManager#doDeleteEntity(org.divy.common.bo.IBusinessObject)
	 */
	@Override
	protected void doDeleteEntity(ENTITY entity) {
		getEntityKeyPath(getIdentifier(entity)).delete();
	}

	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.TestBaseManager#doSearchEntities(org.divy.common.bo.IQuery)
	 */
	@Override
	protected List<ENTITY> doSearchEntities(IQuery<ENTITY> searchQuery) {
		return getEntityPath().get(getEntityListClass());
	}


	/* (non-Javadoc)
	 * @see org.divy.common.bo.test.TestBaseManager#createSearchQuery()
	 */
	@Override
	protected IQuery<ENTITY> createSearchQuery() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    
	private void cleanup() {
		List<ENTITY> boList = doSearchEntities(null);

		for (Iterator<ENTITY> iterator = boList.iterator(); iterator.hasNext();) {

			ENTITY mockEntity = iterator.next();

			try {
				doDeleteEntity(mockEntity);
			} catch (Exception ex) {
			}
		}
	}

    /**
     * Create a web resource whose URI refers to the base URI the Web
     * application is deployed at.
     *
     * @return the created web resource
     */
    protected WebResource resource() {
        return jerseyTest.resource();
    }

	protected abstract Builder getEntityKeyPath(ID uuid);

	protected abstract Builder getEntityPath();

	protected abstract GenericType<ENTITY> getEntityClass();

	protected abstract GenericType<List<ENTITY>> getEntityListClass();
	
	protected abstract String getTestClassPackage();

	private JerseyTest jerseyTest;
	
	
	class JerseyTestProxy extends JerseyTest{

		@Override
		protected Client getClient(TestContainer tc, AppDescriptor ad) {
			Client client = super.getClient(tc, ad);
			client.addFilter(new LoggingFilter());
			return client;
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
			clientConfig.getClasses().add(HibernateBOMapperProvider.class);

			WebAppDescriptor ad = new WebAppDescriptor.Builder(
					"com.fasterxml.jackson.jaxrs.json" + getTestClassPackage())
					.contextListenerClass(getGuiceServletConfig())
					.filterClass(GuiceFilter.class)
					.clientConfig(clientConfig).build();

			return ad;
		}
	}
	
	/**
     * Set up the test by invoking {@link TestContainer#start() } on
     * the test container obtained from the test container factory.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    	jerseyTest.setUp();
    }

    /**
     * Tear down the test by invoking {@link TestContainer#stop() } on
     * the test container obtained from the test container factory.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
		cleanup();
    	jerseyTest.tearDown();
    }

	protected abstract Class<? extends AbstractGuiceServletConfig> getGuiceServletConfig();

}
