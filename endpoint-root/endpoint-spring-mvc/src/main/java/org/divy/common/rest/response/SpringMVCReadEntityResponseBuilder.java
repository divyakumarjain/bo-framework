package org.divy.common.rest.response;

import org.divy.common.bo.rest.response.ReadEntityResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Collection;

public class SpringMVCReadEntityResponseBuilder<T> extends ReadEntityResponseBuilder<T, ResponseEntity> {

    public SpringMVCReadEntityResponseBuilder(T entity) {
        super(entity);
    }

    public SpringMVCReadEntityResponseBuilder() {
        super();
    }

    @Override
    public ResponseEntity build() {
            if(entity==null || entity instanceof Collection && ((Collection) entity).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(entity);
            }
    }
}
