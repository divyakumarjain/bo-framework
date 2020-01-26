package org.divy.common.bo.spring.mvc.rest.response;

import org.divy.common.bo.rest.response.AbstractResponseEntityBuilder;
import org.divy.common.bo.rest.response.DeleteEntityResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public class SpringMVCDeleteEntityResponseBuilder<T> extends AbstractResponseEntityBuilder<T, ResponseEntity>
        implements DeleteEntityResponseBuilder<ResponseEntity> {

    SpringMVCDeleteEntityResponseBuilder(T entity) {
        super(entity);
    }

    SpringMVCDeleteEntityResponseBuilder() {
        super();
    }

    @Override
    public ResponseEntity build() {
        if(entity==null || entity instanceof Collection && ((Collection) entity).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        }
    }

}
