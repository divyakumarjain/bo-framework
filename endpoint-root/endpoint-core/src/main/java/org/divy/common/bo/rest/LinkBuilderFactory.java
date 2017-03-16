package org.divy.common.bo.rest;

import javax.ws.rs.core.UriInfo;


public interface LinkBuilderFactory {
    LinkBuilder newBuilder();
    LinkBuilder newBuilder(UriInfo requestUriInfo);
}
