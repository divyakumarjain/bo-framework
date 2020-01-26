package org.divy.common.bo.spring.mvc.rest.response;

import org.divy.common.bo.rest.response.UpdateEntityResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class SpringMVCUpdateEntityResponseBuilder<T> extends UpdateEntityResponseBuilder<T, ResponseEntity> {
    SpringMVCUpdateEntityResponseBuilder() {
        super();
    }

    @Override
    public ResponseEntity<T> build() {
        if(entity==null || entity instanceof Collection && ((Collection) entity).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.<T>status(HttpStatus.OK).body(entity);
        }
    }
}
