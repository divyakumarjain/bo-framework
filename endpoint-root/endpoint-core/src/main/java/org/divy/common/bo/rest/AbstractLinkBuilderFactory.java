package org.divy.common.bo.rest;

import org.divy.common.bo.http.HttpRequestContext;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

public abstract class AbstractLinkBuilderFactory<L> implements LinkBuilderFactory<L> {
    protected static final String HEADER_HOST = "Host";
    protected static final String HEADER_X_ORIGINAL_PROTO = "X-Orig-Proto";
    protected static final String HEADER_X_ORIGINAL_HOST = "X-Orig-Host";
    protected static final String HEADER_X_ORIGINAL_BASE = "X-Orig-Base";
    protected static final String DEFAULT_SCHEME = "http";
    protected static final String DEFAULT_HOST = "localhost";

    protected HttpServletRequest request;

    protected AbstractLinkBuilderFactory(HttpServletRequest request)
    {
        this.request = request;
    }

    protected Optional<String> getRequestHeader(HttpServletRequest request, String headerName) {
        if(request != null) {
            String headerValue;
            headerValue = request.getHeader(headerName);
            return Optional.ofNullable(headerValue);
        } else {
            return Optional.empty();
        }
    }

    protected Optional<String> resolveHost() {
        return Optional.ofNullable(getRequestHeader(HEADER_X_ORIGINAL_HOST)
                    .orElseGet(() -> getRequestHeader(HEADER_HOST).orElse(null)));
    }

    protected Optional<String> getRequestHeader(String headerName) {
        Optional<String> headerValue = getRequestHeader(this.request, headerName);
        if(!headerValue.isPresent()) {
            return getRequestHeader(HttpRequestContext.request(), headerName);
        }
        return headerValue;
    }

    protected String getOriginalHost() {
        return resolveHost().orElse(DEFAULT_HOST);
    }

    protected String resolveOriginalPath()
    {
        String originalPath;
        originalPath = defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_BASE),
                request.getContextPath());
        return originalPath;
    }

    private String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    private boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    protected String resolveSchema()
    {
        String scheme;
        scheme = defaultIfBlank(defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_PROTO),
                request.getScheme()),DEFAULT_SCHEME);
        return scheme;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Inject
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
