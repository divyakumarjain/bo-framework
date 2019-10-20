package org.divy.common.bo.spring.endpoint;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static org.divy.common.bo.spring.endpoint.JerseyEndpointConfigProperties.BO_FRAMEWORK_JERSEY_ENDPOINT_CONFIG_PREFIX;

@ConfigurationProperties(prefix = BO_FRAMEWORK_JERSEY_ENDPOINT_CONFIG_PREFIX)
@Component
public class JerseyEndpointConfigProperties {

    public static final String BO_FRAMEWORK_JERSEY_ENDPOINT_CONFIG_PREFIX = "bo-framework.endpoint.jersey";

    private static final String HATEOAS_ENDPOINT_PATH = "hatoas";
    private static final String ENDPOINT_PATH = "api";

    private String hateoasAPIEndpointPath = HATEOAS_ENDPOINT_PATH;
    private String apiEndpointPath = ENDPOINT_PATH;
    private boolean enableHateoasApi = false;

    public boolean isEnableHateoasApi() {
        return enableHateoasApi;
    }

    public void setEnableHateoasApi(boolean enableHateoasApi) {
        this.enableHateoasApi = enableHateoasApi;
    }

    public String getHateoasApiEndpointPath() {
        return hateoasAPIEndpointPath;
    }

    public void setHateoasApiEndpointPath(String hATEOASAPIEndpointPath) {
        this.hateoasAPIEndpointPath = hATEOASAPIEndpointPath;
    }

    public String getApiEndpointPath() {
        return apiEndpointPath;
    }

    public void setApiEndpointPath(String apiEndpointPath) {
        this.apiEndpointPath = apiEndpointPath;
    }
}
