package org.divy.common.rest.builder;

import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.Response;

import org.divy.common.bo.IBusinessObject;

class ListEntityResponseBuilder<ENTITY extends IBusinessObject<ID>,ID> extends ResponseEntityBuilder<List<ENTITY>> {

    public ListEntityResponseBuilder(List<ENTITY> list) {
        setEntity(list);
    }
    @Override
    public Response build() {
        Response.Status statusCode  = getStatusCode();

        if(statusCode==null) {
            if(entity==null || entity instanceof Collection && ((Collection<ENTITY>) entity).size()<1) {
                setStatusCode(Response.Status.NO_CONTENT);
            } else {
                setStatusCode(Response.Status.OK);
            }
        }
        return super.build();
    }
}
