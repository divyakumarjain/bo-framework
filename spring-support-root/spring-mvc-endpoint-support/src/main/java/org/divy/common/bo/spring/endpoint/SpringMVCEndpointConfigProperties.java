package org.divy.common.bo.spring.endpoint;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static org.divy.common.bo.spring.endpoint.SpringMVCEndpointConfigProperties.BO_FRAMEWORK_MVC_ENDPOINT_CONFIG_PREFIX;

@ConfigurationProperties(prefix = BO_FRAMEWORK_MVC_ENDPOINT_CONFIG_PREFIX)
@Component
public class SpringMVCEndpointConfigProperties {

    static final String BO_FRAMEWORK_MVC_ENDPOINT_CONFIG_PREFIX = "bo-framework.endpoint.mvc";

    private static final String HYPERMEDIA_ENDPOINT_PATH = "hypermedia";
    private static final String ENDPOINT_PATH = "api";

    private String hypermediaApiEndpointPath = HYPERMEDIA_ENDPOINT_PATH;
    private String apiEndpointPath = ENDPOINT_PATH;
    private boolean enableHateoasApi = false;

    public boolean isEnableHateoasApi() {
        return enableHateoasApi;
    }

    public void setEnableHateoasApi(boolean enableHateoasApi) {
        this.enableHateoasApi = enableHateoasApi;
    }

    public String getHateoasApiEndpointPath() {
        return hypermediaApiEndpointPath;
    }

    public void setHateoasApiEndpointPath(String hATEOASAPIEndpointPath) {
        this.hypermediaApiEndpointPath = hATEOASAPIEndpointPath;
    }

    public String getApiEndpointPath() {
        return apiEndpointPath;
    }

    public void setApiEndpointPath(String apiEndpointPath) {
        this.apiEndpointPath = apiEndpointPath;
    }
}
