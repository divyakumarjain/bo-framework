package org.divy.common.bo.rest;

import java.net.URI;

public interface LinkBuilder<L> {
    L buildLinkFor(String rel, Class<?> resource);
    L buildLinkFor(String rel, Class<?> resource, String methodName, Object... param);

    URI buildURI(Class<?> resource);
    URI buildURI(Class<?> resource, String methodName, Object... param);
}
