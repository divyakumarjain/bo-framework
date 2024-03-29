package org.divy.common.bo.jersey.rest;

import org.apache.commons.lang.StringUtils;
import org.divy.common.bo.rest.LinkBuilder;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import java.lang.reflect.Method;
import java.net.URI;

public class JerseyLinkBuilderImpl implements LinkBuilder<Link> {

    private static final int DEFAULT_LOCAL_PORT = 8080;

    private final UriBuilder uriBuilder;

    public JerseyLinkBuilderImpl(String scheme, String host, String basePath) {
        if (StringUtils.isBlank(scheme)) {
            throw new IllegalArgumentException("Scheme must not be null or blank");
        }

        if (StringUtils.isBlank(host)) {
            throw new IllegalArgumentException("Host must not be null or blank");
        }

        final var fixedBasePath = StringUtils.defaultIfBlank(basePath, StringUtils.EMPTY);

        uriBuilder = UriBuilder.fromPath(fixedBasePath);

        var colonPosInHost = host.indexOf(':');
        var hostWithoutPort = host.substring(0, colonPosInHost >= 0 ? colonPosInHost : host.length());

        uriBuilder.scheme(scheme).host(hostWithoutPort);

        if (colonPosInHost >= 0) {
            var port = Integer.parseInt(host.substring(colonPosInHost + 1));
            uriBuilder.port(port);
        } else if ("localhost".equals(host)) {
            uriBuilder.port(DEFAULT_LOCAL_PORT);
        }
    }


    @Override
    public Link buildLinkFor(String rel, Class<?> resource) {
        return Link.fromUriBuilder(uriBuilder.path(resource)).rel(rel).build();
    }

    @Override
    public Link buildLinkFor(String rel, Class<?> resource, String methodName, Object... param) {
        resolvePath(resource, methodName);
        return Link.fromUriBuilder(uriBuilder).rel(rel).build(param);
    }

    @Override
    public URI buildURI(Class<?> resource) {
        return uriBuilder.path(resource).build();
    }

    @Override
    public URI buildURI(Class<?> resource, String methodName, Object... param) {
        resolvePath(resource, methodName);
        return uriBuilder.build(param);
    }

    private void resolvePath(Class<?> resource, String methodName) {
        uriBuilder.path(resource);
        uriBuilder.path(resolveMethod(resource, methodName));
    }

    private Method resolveMethod(Class<?> resource, String methodName) {
        for(Method method: resource.getMethods()) {
            if(method.getName().equals(methodName))
                return method;
        }

        throw new IllegalArgumentException("Could not find method with name " + methodName + " in "+ resource);
    }
}
