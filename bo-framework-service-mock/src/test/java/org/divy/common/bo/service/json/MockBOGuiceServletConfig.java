package org.divy.common.bo.service.json;


import org.divy.common.bo.service.json.test.MockBoService;
import org.divy.common.bo.service.test.AbstractGuiceServletConfig;
import org.divy.common.bo.service.test.AbstractGuiceServletModule;

import com.google.inject.Module;

public class MockBOGuiceServletConfig extends AbstractGuiceServletConfig {

	@Override
	protected Module[] getServletModule() {
		return new Module[] {
				
			new AbstractGuiceServletModule() {
				protected void configureTestObjects(){
					bind(MockBoService.class);
				}
			}
		};
	}
}
