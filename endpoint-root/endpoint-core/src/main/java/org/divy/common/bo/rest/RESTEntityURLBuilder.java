package org.divy.common.bo.rest;

import javax.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;

public interface RESTEntityURLBuilder<T, I extends Serializable> {
    URI buildEntityUri(T entity, UriInfo uriInfo);

    URI buildEntityUri(T entity);
}
