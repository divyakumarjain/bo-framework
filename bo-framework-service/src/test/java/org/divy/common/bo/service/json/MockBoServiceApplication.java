/**
 * 
 */
package org.divy.common.bo.service.json;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.jersey.api.core.ResourceConfig;

/**
 * @author divyakumar.a.jain@hp.com
 *
 */
public class MockBoServiceApplication extends ResourceConfig {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(MockBoService.class);
		s.add(HibernateBOMapperProvider.class);
		return s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.jersey.api.core.ResourceConfig#getFeatures()
	 */
	@Override
	public Map<String, Boolean> getFeatures() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.jersey.api.core.ResourceConfig#getFeature(java.lang.String)
	 */
	@Override
	public boolean getFeature(String featureName) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.jersey.api.core.ResourceConfig#getProperties()
	 */
	@Override
	public Map<String, Object> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sun.jersey.api.core.ResourceConfig#getProperty(java.lang.String)
	 */
	@Override
	public Object getProperty(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	// public MockBoServiceApplication() {
	// super();
	// addClasses(MockBoService.class);
	// }

}
