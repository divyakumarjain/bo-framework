package org.divy.common.bo.service.json;


import org.divy.common.bo.service.json.test.MockBoEndpoint;
import org.divy.common.bo.service.test.AbstractGuiceServletConfig;
import org.divy.common.bo.service.test.AbstractGuiceServletModule;
import org.divy.common.query.service.SearchQueryModule;

import com.google.inject.Module;

public class MockBOGuiceServletConfig extends AbstractGuiceServletConfig {

    @Override
    protected Module[] getServletModule() {
        return new Module[] {

            new AbstractGuiceServletModule() {
                protected void configureTestObjects(){
                    bind(MockBoEndpoint.class);
                    bind(SearchQueryModule.class);
                }
            }
        };
    }
}
