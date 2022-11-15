package org.divy.common.bo.spring.mvc.graphql.response;


import org.divy.common.bo.rest.RESTEntityURLBuilder;
import org.divy.common.bo.rest.response.CreateEntityResponseBuilder;
import org.springframework.http.ResponseEntity;


public class SpringMVCCreateEntityResponseBuilder<T> extends CreateEntityResponseBuilder<T, ResponseEntity<T>> {


    public SpringMVCCreateEntityResponseBuilder(RESTEntityURLBuilder<T> entityURLBuilder) {
        super(entityURLBuilder);
    }

    @Override
    public ResponseEntity<T> build() {
        return ResponseEntity.created(createLocation()).build();
    }
}
