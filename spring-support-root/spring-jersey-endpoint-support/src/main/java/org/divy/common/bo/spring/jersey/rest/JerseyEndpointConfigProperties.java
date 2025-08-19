package org.divy.common.bo.spring.jersey.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "bo-framework.endpoint.jersey")
@Component
public class JerseyEndpointConfigProperties {
    private static final String HATEOAS_ENDPOINT_PATH = "hatoas";
    private static final String ENDPOINT_PATH = "api";

    private String hateoasApiEndpointPath = HATEOAS_ENDPOINT_PATH;
    private String apiEndpointPath = ENDPOINT_PATH;
    private boolean enableHateoasApi = false;

    public boolean isEnableHateoasApi() {
        return enableHateoasApi;
    }

    public void setEnableHateoasApi(boolean enableHateoasApi) {
        this.enableHateoasApi = enableHateoasApi;
    }

    public String getHateoasApiEndpointPath() {
        return hateoasApiEndpointPath;
    }

    public void setHateoasApiEndpointPath(String hateoasApiEndpointPath) {
        this.hateoasApiEndpointPath = hateoasApiEndpointPath;
    }

    public String getApiEndpointPath() {
        return apiEndpointPath;
    }

    public void setApiEndpointPath(String apiEndpointPath) {
        this.apiEndpointPath = apiEndpointPath;
    }
}
