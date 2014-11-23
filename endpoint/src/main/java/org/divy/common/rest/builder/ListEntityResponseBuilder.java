package org.divy.common.rest.builder;

import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.Response;

import org.divy.common.bo.IBusinessObject;
import org.divy.common.rest.RESTEntityURLBuilder;

class ListEntityResponseBuilder<T extends IBusinessObject> extends ResponseEntityBuilder<List<T>> {

    public ListEntityResponseBuilder(List<T> list) {
        setEntity(list);
    }
    @Override
    public Response build() {
        Response.Status statusCode  = getStatusCode();

        if(statusCode==null) {
            if(entity==null || entity instanceof Collection && ((Collection) entity).size()<1) {
                setStatusCode(Response.Status.NO_CONTENT);
            } else {
                setStatusCode(Response.Status.OK);
            }
        }
        return super.build();
    }
}
