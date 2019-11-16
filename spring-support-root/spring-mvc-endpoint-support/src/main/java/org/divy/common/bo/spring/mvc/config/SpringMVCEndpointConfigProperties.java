package org.divy.common.bo.spring.mvc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "bo-framework.endpoint.mvc")
@Component
public class SpringMVCEndpointConfigProperties {

    private static final String HATOAS_ENDPOINT_PATH = "hatoas";
    private static final String ENDPOINT_PATH = "api";

    private String hatoasApiEndpointPath = HATOAS_ENDPOINT_PATH;
    private String apiEndpointPath = ENDPOINT_PATH;
    private boolean enableHateoasApi = false;

    public boolean isEnableHateoasApi() {
        return enableHateoasApi;
    }

    public void setEnableHateoasApi(boolean enableHateoasApi) {
        this.enableHateoasApi = enableHateoasApi;
    }

    public String getHateoasApiEndpointPath() {
        return hatoasApiEndpointPath;
    }

    public void setHateoasApiEndpointPath(String hatoasApiEndpointPath) {
        this.hatoasApiEndpointPath = hatoasApiEndpointPath;
    }

    public String getApiEndpointPath() {
        return apiEndpointPath;
    }

    public void setApiEndpointPath(String apiEndpointPath) {
        this.apiEndpointPath = apiEndpointPath;
    }
}
