package org.divy.common.rest;

import javax.ws.rs.core.UriInfo;


public interface LinkBuilderFactory {
    LinkBuilder newBuilder();
    LinkBuilder newBuilder(UriInfo requestUriInfo);
}
