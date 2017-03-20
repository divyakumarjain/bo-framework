package org.divy.common.bo.rest.builder;

import javax.ws.rs.core.Response;
import java.util.Collection;

class ListEntityResponseBuilder<E, I> extends ResponseEntityBuilder<Collection<E>> {

    public ListEntityResponseBuilder(Collection<E> list) {
        setEntity(list);
    }
    @Override
    public Response build() {
        Response.Status statusCode  = getStatusCode();

        if(statusCode==null) {
            if (entity == null || entity.isEmpty()) {
                setStatusCode(Response.Status.NO_CONTENT);
            } else {
                setStatusCode(Response.Status.OK);
            }
        }
        return super.build();
    }
}
