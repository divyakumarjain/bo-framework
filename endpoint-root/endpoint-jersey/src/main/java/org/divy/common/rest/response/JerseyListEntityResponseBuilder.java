package org.divy.common.rest.response;


import org.divy.common.bo.rest.response.ListEntityResponseBuilder;

import javax.ws.rs.core.Response;
import java.util.Collection;

class JerseyListEntityResponseBuilder<E> extends ListEntityResponseBuilder<Collection<E>, Response> {

    JerseyListEntityResponseBuilder(Collection<E> list) {
        super(list);
    }
    @Override
    public Response build() {
        if (entity == null || entity.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(entity).build();
        }
    }
}
