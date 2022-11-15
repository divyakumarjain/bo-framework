package org.divy.common.bo.spring.mvc.graphql.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "bo-framework.endpoint.mvc.graphql")
@Component
public class SpringMVCEndpointConfigProperties {
    private static final String ENDPOINT_PATH = "graphql";

    private String apiEndpointPath = ENDPOINT_PATH;
    public String getApiEndpointPath() {
        return apiEndpointPath;
    }

    public void setApiEndpointPath(String apiEndpointPath) {
        this.apiEndpointPath = apiEndpointPath;
    }
}
