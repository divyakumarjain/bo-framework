package org.divy.common.rest.response;


import org.divy.common.bo.rest.RESTEntityURLBuilder;
import org.divy.common.bo.rest.response.CreateEntityResponseBuilder;
import org.springframework.http.ResponseEntity;


public class SpringMVCCreateEntityResponseBuilder<T> extends CreateEntityResponseBuilder<T, ResponseEntity> {


    public SpringMVCCreateEntityResponseBuilder(RESTEntityURLBuilder<T> entityURLBuilder) {
        super(entityURLBuilder);
    }

    @Override
    public ResponseEntity build() {
        return ResponseEntity.created(createLocation()).build();
    }
}
