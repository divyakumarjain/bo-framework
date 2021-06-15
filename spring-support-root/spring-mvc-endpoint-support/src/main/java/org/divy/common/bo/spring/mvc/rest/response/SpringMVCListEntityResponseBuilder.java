package org.divy.common.bo.spring.mvc.rest.response;


import org.divy.common.bo.rest.response.ListEntityResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

class SpringMVCListEntityResponseBuilder<T> extends ListEntityResponseBuilder<Collection<T>, ResponseEntity<Collection<T>>> {

    public SpringMVCListEntityResponseBuilder(Collection<T> list) {
        super(list);
    }
    @Override
    public ResponseEntity<Collection<T>> build() {
        if (entity == null || entity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        }
    }
}
