package org.divy.common.bo.rest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.message.internal.JerseyLink;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;


public class LinkBuilder {

    static final int DEFAULT_LOCAL_PORT = 8080;
    static final int DEFAULT_HTTP_PORT = 80;

    private final UriBuilder uriBuilder;

    /**
     * Construct a SimpleLinkBuilder with the supplied scheme, host, and base path.
     *
     * @param scheme The scheme to use in the URI link.
     * @param host The host to use in the URI link.
     * @param basePath The base bath to use in the URI link. May be {@code null}.
     * @throws IllegalArgumentException If the scheme or host is {@code null}, empty, or blank.
     */
    public LinkBuilder(String scheme, String host, String basePath) {
        if (StringUtils.isBlank(scheme)) {
            throw new IllegalArgumentException("Scheme must not be null or blank");
        }

        if (StringUtils.isBlank(host)) {
            throw new IllegalArgumentException("Host must not be null or blank");
        }

        final String fixedBasePath = StringUtils.defaultIfBlank(basePath, StringUtils.EMPTY);

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

    /**e
     * Append the path from a Path-annotated class to the existing path. When constructing the final path, a '/'
     * separator will be inserted between the existing path and the supplied path if necessary.
     *
     * @param resource a resource whose {@link javax.ws.rs.Path} value will be used to obtain the path to append.
     * @return the updated LinkBuilder
     *
     * @throws IllegalArgumentException if resource is null, or if resource is not annotated with {@link
     * javax.ws.rs.Path}
     */

    public LinkBuilder path(Class<?> resource) {
        uriBuilder.path(resource);
        return this;
    }

    /**
     * Append the path from a Path-annotated method to the existing path. When constructing the final path, a '/'
     * separator will be inserted between the existing path and the supplied path if necessary. This method is a
     * convenience shortcut to <code>path(Method)</code>, it can only be used in cases where there is a single method
     * with the specified name that is annotated with {@link javax.ws.rs.Path}.
     *
     * @param resource the resource containing the method
     * @param method the name of the method whose {@link javax.ws.rs.Path} value will be used to obtain the path to
     * append
     * @return the updated LinkBuilder
     *
     * @throws IllegalArgumentException if resource or method is null, or there is more than or less than one variant of
     * the method annotated with {@link javax.ws.rs.Path}
     */

    public LinkBuilder path(Class<?> resource, String method) {
        uriBuilder.path(resource, method);
        return this;
    }

    /**
     * Sets a relative URI for the associated resource.
     *
     * @param path a URI path that will be used to buildMapper the link.
     * @return the updated LinkBuilder
     *
     * @throws IllegalArgumentException if path is null
     */
    public LinkBuilder path(String path) {
        if (path == null) {
            throw new IllegalArgumentException("path param must not be null");
        }
        uriBuilder.path(path);
        return this;
    }

    /**
     * Append a query parameter to the existing set of query parameters. If multiple values are supplied the parameter
     * will be added once per value.
     *
     * @param name the query parameter name
     * @param values the query parameter value(s), each object will be converted to a {@code String} using its {@code
     * toString()} method.
     * @return the updated LinkBuilder
     *
     * @throws IllegalArgumentException if name or values is null
     */

    public LinkBuilder queryParam(String name, Object... values) {
        if (name == null || ArrayUtils.isEmpty(values) || ArrayUtils.contains(values, null)) {
            throw new IllegalArgumentException("All query param input must be non-null.");
        }

        uriBuilder.queryParam(name, values);
        return this;
    }

    /**
     * Build a string representation of a URI. Values are converted to <code>String</code> using their
     * <code>toString</code> method and are then encoded to match the rules of the URI component to which they pertain.
     * All '%' characters in the stringified values will be encoded. The state of the builder is unaffected; this method
     * may be called multiple times on the same builder instance.
     *
     * @param values a list of URI template parameter values
     * @return the string version of a URI built from the LinkBuilder
     *
     * @throws IllegalArgumentException if there are any URI template parameters without a supplied value, or if a value
     * is null.
     */
    public String build(Object... values) {
        return buildUri(values).toString();
    }

    /**
     * Build a URI, using the supplied values in order to replace any URI template parameters. Values are converted to
     * <code>String</code> using their <code>toString</code> method and are then encoded to match the rules of the URI
     * component to which they pertain. All '%' characters in the stringified values will be encoded. The state of the
     * builder is unaffected; this method may be called multiple times on the same builder instance. <p>All instances of
     * the same template parameter will be replaced by the same value that corresponds to the position of the first
     * instance of the template parameter. e.g. the template "{a}/{b}/{a}" with values {"x", "y", "z"} will result in
     * the the URI "x/y/x", <i>not</i> "x/y/z".
     *
     * @param values a list of URI template parameter values
     * @return the URI built from the LinkBuilder
     *
     * @throws IllegalArgumentException if there are any URI template parameters without a supplied value, or if a value
     * is null.
     */
    public URI buildUri(Object... values) {
        return uriBuilder.build(values);
    }

    public Link buildLink(String rel, Object... values) {
        return JerseyLink.fromUri(buildUri(values)).rel(rel).build();
    }
}
