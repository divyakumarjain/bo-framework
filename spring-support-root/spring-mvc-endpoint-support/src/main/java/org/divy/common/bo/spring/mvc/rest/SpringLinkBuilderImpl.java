package org.divy.common.bo.spring.mvc.rest;

import org.divy.common.bo.rest.LinkBuilder;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Method;
import java.net.URI;


public class SpringLinkBuilderImpl implements LinkBuilder<Link> {

    private static final int DEFAULT_LOCAL_PORT = 8080;

    /**
     * Construct a SimpleLinkBuilder with the supplied scheme, host, and base buildLinkFor.
     *
     * @param scheme The scheme to use in the URI link.
     * @param host The host to use in the URI link.
     * @param basePath The base bath to use in the URI link. May be {@code null}.
     * @throws IllegalArgumentException If the scheme or host is {@code null}, empty, or blank.
     */
    public SpringLinkBuilderImpl(String scheme, String host, String basePath) {
        if (isBlank(scheme)) {
            throw new IllegalArgumentException("Scheme must not be null or blank");
        }

        if (isBlank(host)) {
            throw new IllegalArgumentException("Host must not be null or blank");
        }

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        int colonPosInHost = host.indexOf(':');
        String hostWithoutPort = host.substring(0, colonPosInHost >= 0 ? colonPosInHost : host.length());

        uriComponentsBuilder.scheme(scheme).host(hostWithoutPort);

        if (colonPosInHost >= 0) {
            int port = Integer.parseInt(host.substring(colonPosInHost + 1));
            uriComponentsBuilder.port(port);
        } else if ("localhost".equals(host)) {
            uriComponentsBuilder.port(DEFAULT_LOCAL_PORT);
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

    @Override
    public Link buildLinkFor(String rel, Class<?> resource) {
        return WebMvcLinkBuilder.linkTo(resource).withRel(rel);
    }

    @Override
    public Link buildLinkFor(String rel, Class<?> resource, String methodName, Object... param) {
        return WebMvcLinkBuilder.linkTo(resolveMethod(resource, methodName)).withRel(rel).expand(param);
    }

    @Override
    public URI buildURI(Class<?> resource) {
        return WebMvcLinkBuilder.linkTo(resource).toUri();
    }

    @Override
    public URI buildURI(Class<?> resource, String methodName, Object... param) {
        return WebMvcLinkBuilder.linkTo(resolveMethod(resource, methodName), param).toUri();
    }

    private Method resolveMethod(Class<?> resource, String methodName) {
        for(Method method: resource.getMethods()) {
            if(method.getName().equals(methodName))
                return method;
        }

        throw new IllegalArgumentException("Could not find method with name " + methodName + " in "+ resource);
    }
}
