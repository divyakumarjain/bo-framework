package org.divy.common.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.divy.common.http.HttpRequestContext;

public class LinkBuilderFactory {
    static final String HEADER_HOST = "Host";
    static final String HEADER_X_ORIGINAL_PROTO = "X-Orig-Proto";
    static final String HEADER_X_ORIGINAL_HOST = "X-Orig-Host";
    static final String HEADER_X_ORIGINAL_BASE = "X-Orig-Base";
    static final String DEFAULT_SCHEME = "http";
    static final String DEFAULT_HOST = "localhost";

    public LinkBuilder newBuilder() {
        String scheme = DEFAULT_SCHEME;
        String originalPath = StringUtils.EMPTY;

        final HttpServletRequest request = HttpRequestContext.request();
        if (request != null) {
            scheme = StringUtils.defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_PROTO),
                    request.getScheme());
            originalPath = StringUtils.defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_BASE),
                    request.getContextPath());
        }

        return new LinkBuilder(scheme, getOriginalHost(), originalPath);
    }

    public LinkBuilder newBuilder(UriInfo requestUriInfo) {
        String scheme = DEFAULT_SCHEME;
        String originalPath = StringUtils.EMPTY;

        final HttpServletRequest request = HttpRequestContext.request();
        if (request != null) {
            scheme = StringUtils.defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_PROTO),
                    StringUtils.defaultIfBlank(requestUriInfo.getAbsolutePath().getScheme(),
                            request.getScheme()));
            originalPath = StringUtils.defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_BASE),
                    StringUtils.defaultIfBlank(requestUriInfo.getBaseUri().toString(),
                            request.getContextPath()));
        }

        return new LinkBuilder(scheme, getOriginalHost(requestUriInfo), originalPath);
    }

    private String getOriginalHost(UriInfo requestUriInfo) {
        String host = DEFAULT_HOST;

        final HttpServletRequest request = HttpRequestContext.request();
        if (request != null) {
            String originalHostHeader = request.getHeader(HEADER_X_ORIGINAL_HOST);
            if (StringUtils.isBlank(originalHostHeader)) {
                String hostHeader = request.getHeader(HEADER_HOST);
                if (StringUtils.isBlank(hostHeader)) {
                    String portSuffix = request.getServerPort() == LinkBuilder.DEFAULT_HTTP_PORT ? "" : ":" + request.getServerPort();
                    host = request.getServerName() + portSuffix;
                }
                else {
                    host = hostHeader;
                }
            }
            else {
                host = originalHostHeader;
            }
        }

        return host;
    }

    public String getOriginalHost() {
        String host = DEFAULT_HOST;

        final HttpServletRequest request = HttpRequestContext.request();
        if (request != null) {
            String originalHostHeader = request.getHeader(HEADER_X_ORIGINAL_HOST);
            if (StringUtils.isBlank(originalHostHeader)) {
                String hostHeader = request.getHeader(HEADER_HOST);
                if (StringUtils.isBlank(hostHeader)) {
                    String portSuffix = request.getServerPort() == LinkBuilder.DEFAULT_HTTP_PORT ? "" : ":" + request.getServerPort();
                    host = request.getServerName() + portSuffix;
                }
                else {
                    host = hostHeader;
                }
            }
            else {
                host = originalHostHeader;
            }
        }

        return host;
    }
}
