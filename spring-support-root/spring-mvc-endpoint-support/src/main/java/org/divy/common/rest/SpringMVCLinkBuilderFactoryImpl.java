package org.divy.common.rest;

import org.apache.commons.lang.StringUtils;
import org.divy.common.bo.http.HttpRequestContext;
import org.divy.common.bo.rest.LinkBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class SpringMVCLinkBuilderFactoryImpl implements LinkBuilderFactory {

    @Autowired
    private HttpServletRequest request;

    private static final String HEADER_HOST = "Host";
    private static final String HEADER_X_ORIGINAL_PROTO = "X-Orig-Proto";
    private static final String HEADER_X_ORIGINAL_HOST = "X-Orig-Host";
    private static final String HEADER_X_ORIGINAL_BASE = "X-Orig-Base";
    private static final String DEFAULT_SCHEME = "http";
    private static final String DEFAULT_HOST = "localhost";

    public SpringMVCLinkBuilderFactoryImpl() {
        this(null);
    }

    public SpringMVCLinkBuilderFactoryImpl(HttpServletRequest request) {
        this.request = request;
    }


    @Override
    public SpringLinkBuilderImpl newBuilder() {
        String scheme = DEFAULT_SCHEME;
        String originalPath = "";

        if (request != null) {
            scheme = StringUtils.defaultIfBlank(StringUtils.defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_PROTO),
                    request.getScheme()),DEFAULT_SCHEME);
            originalPath = StringUtils.defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_BASE),
                    request.getContextPath());
        }

        return new SpringLinkBuilderImpl(scheme, getOriginalHost(), originalPath);
    }

    private Optional<String> getRequestHeader(String headerName) {
        Optional<String> headerValue = getRequestHeader(this.request, headerName);
        if(!headerValue.isPresent()) {
            return getRequestHeader(HttpRequestContext.request(), headerName);
        }
        return headerValue;
    }

    private Optional<String> getRequestHeader(HttpServletRequest request, String headerName) {
        if(request != null) {
            String headerValue;
            headerValue = request.getHeader(headerName);
            return Optional.ofNullable(headerValue);
        } else {
            return Optional.empty();
        }
    }


    private String getOriginalHost() {
        return resolveHost().orElse(DEFAULT_HOST);
    }

    private Optional<String> resolveHost() {

        return Optional.ofNullable(getRequestHeader(HEADER_X_ORIGINAL_HOST)
                    .orElseGet(() -> getRequestHeader(HEADER_HOST).orElse(null)));
    }
}
