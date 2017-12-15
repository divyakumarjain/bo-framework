package org.divy.common.bo.spring.endpoint;

import org.divy.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler
{
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 409
    @ExceptionHandler(NotFoundException.class)
    public void handleConflict() {
        // Nothing to do
    }
}
