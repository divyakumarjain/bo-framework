package org.divy.common.bo.rest;

import org.apache.commons.lang.StringUtils;
import org.divy.common.bo.http.HttpRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;


public class LinkBuilderFactoryImpl implements LinkBuilderFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkBuilderFactoryImpl.class);

    @Inject
    private HttpServletRequest request;

    private static final String HEADER_HOST = "Host";
    private static final String HEADER_X_ORIGINAL_PROTO = "X-Orig-Proto";
    private static final String HEADER_X_ORIGINAL_HOST = "X-Orig-Host";
    private static final String HEADER_X_ORIGINAL_BASE = "X-Orig-Base";
    private static final String DEFAULT_SCHEME = "http";
    private static final String DEFAULT_HOST = "localhost";

    @Override
    public LinkBuilder newBuilder() {
        String scheme = DEFAULT_SCHEME;
        String originalPath = "";

        if (request != null) {
            scheme = StringUtils.defaultIfBlank(StringUtils.defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_PROTO),
                    request.getScheme()),DEFAULT_SCHEME);
            originalPath = StringUtils.defaultIfBlank(request.getHeader(HEADER_X_ORIGINAL_BASE),
                    request.getContextPath());
        }

        return new LinkBuilder(scheme, getOriginalHost(), originalPath);
    }

    @Override
    public LinkBuilder newBuilder(UriInfo requestUriInfo) {
        String scheme = DEFAULT_SCHEME;
        String originalPath = StringUtils.EMPTY;

        final HttpServletRequest requestFromContext = HttpRequestContext.request();
        if (requestFromContext != null) {
            scheme = StringUtils.defaultIfBlank(requestFromContext.getHeader(HEADER_X_ORIGINAL_PROTO),
                    StringUtils.defaultIfBlank(requestUriInfo.getAbsolutePath().getScheme(),
                            requestFromContext.getScheme()));
            originalPath = StringUtils.defaultIfBlank(requestFromContext.getHeader(HEADER_X_ORIGINAL_BASE),
                    StringUtils.defaultIfBlank(requestUriInfo.getBaseUri().toString(),
                            requestFromContext.getContextPath()));
        }

        return new LinkBuilder(scheme, getOriginalHost(requestUriInfo), originalPath);
    }

    private String getOriginalHost(UriInfo requestUriInfo) {
        LOGGER.debug("Resolving Host for the URL {} ", requestUriInfo.getAbsolutePath().toString());
        return resolveHost(HttpRequestContext.request()).orElse(DEFAULT_HOST);
    }


    public String getOriginalHost() {
        return resolveHost(HttpRequestContext.request()).orElse(DEFAULT_HOST);
    }

    private Optional<String> resolveHost(HttpServletRequest currentRequest) {
        String host = null;
        if (currentRequest != null) {
            String originalHostHeader = currentRequest.getHeader(HEADER_X_ORIGINAL_HOST);
            if (StringUtils.isBlank(originalHostHeader)) {
                String hostHeader = currentRequest.getHeader(HEADER_HOST);
                if (StringUtils.isBlank(hostHeader)) {
                    String portSuffix = currentRequest.getServerPort() == LinkBuilder.DEFAULT_HTTP_PORT ? "" : ":" + currentRequest.getServerPort();
                    host = currentRequest.getServerName() + portSuffix;
                }
                else {
                    host = hostHeader;
                }
            }
            else {
                host = originalHostHeader;
            }
        }
        return Optional.ofNullable(host);
    }
}
