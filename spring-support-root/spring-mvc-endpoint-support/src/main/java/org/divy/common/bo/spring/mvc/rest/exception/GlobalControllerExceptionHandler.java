package org.divy.common.bo.spring.rest.exception;

import org.divy.common.exception.BadRequestException;
import org.divy.common.exception.NotAuthorizedException;
import org.divy.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler
{
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(BadRequestException.class)
    public void handleBadRequestException() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 401
    @ExceptionHandler(NotAuthorizedException.class)
    public void handleNotAuthorizedException() {
        // Nothing to do
    }
}
