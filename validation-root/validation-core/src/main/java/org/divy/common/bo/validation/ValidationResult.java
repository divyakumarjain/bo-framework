package org.divy.common.bo.validation;

import org.divy.common.bo.repository.BusinessObject;

import jakarta.validation.ConstraintViolation;
import java.util.Objects;

public class ValidationResult<B extends BusinessObject<I>, I> {

    String message;

    public ValidationResult( ConstraintViolation<B> violation)
    {
        this.message = violation.getMessage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (ValidationResult<B,I>) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
