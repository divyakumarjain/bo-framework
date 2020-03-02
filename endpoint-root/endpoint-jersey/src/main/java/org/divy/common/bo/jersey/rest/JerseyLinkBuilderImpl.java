package org.divy.common.bo.jersey.rest;

import org.divy.common.bo.rest.LinkBuilder;

import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.UriBuilder;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.function.Supplier;

public class JerseyLinkBuilderImpl implements LinkBuilder<Link> {

    private static final int DEFAULT_LOCAL_PORT = 8080;

    private final UriBuilder uriBuilder;

    public JerseyLinkBuilderImpl(String scheme, String host, String basePath) {
        if (isBlank(scheme)) {
            throw new IllegalArgumentException("Scheme must not be null or blank");
        }

        if (isBlank(host)) {
            throw new IllegalArgumentException("Host must not be null or blank");
        }

        final String fixedBasePath = defaultIfBlank(basePath, () -> "" );

        uriBuilder = UriBuilder.fromPath(fixedBasePath);

        int colonPosInHost = host.indexOf(':');
        String hostWithoutPort = host.substring(0, colonPosInHost >= 0 ? colonPosInHost : host.length());

        uriBuilder.scheme(scheme).host(hostWithoutPort);

        if (colonPosInHost >= 0) {
            int port = Integer.parseInt(host.substring(colonPosInHost + 1));
            uriBuilder.port(port);
        } else if ("localhost".equals(host)) {
            uriBuilder.port(DEFAULT_LOCAL_PORT);
        }
    }

    private boolean isBlank( String value )
    {
        int strLen;
        if (value != null && (strLen = value.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    private String defaultIfBlank(String value, Supplier<String> supplier) {
        if(isBlank( value ))
            return supplier.get();
        else
            return value;
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
