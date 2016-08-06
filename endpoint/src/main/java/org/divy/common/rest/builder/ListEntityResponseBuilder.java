package org.divy.common.rest.builder;

import org.divy.common.bo.IBusinessObject;

import javax.ws.rs.core.Response;
import java.util.List;

class ListEntityResponseBuilder<E extends IBusinessObject<I>, I> extends ResponseEntityBuilder<List<E>> {

    public ListEntityResponseBuilder(List<E> list) {
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
