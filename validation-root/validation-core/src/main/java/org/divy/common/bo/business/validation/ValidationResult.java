package org.divy.common.bo.business.validation;

import org.divy.common.bo.BusinessObject;

import javax.validation.ConstraintViolation;
import java.util.Objects;

public class ValidationResult {

    String message;

    public ValidationResult(ConstraintViolation<BusinessObject> violation)
    {
        this.message = violation.getMessage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationResult that = (ValidationResult) o;
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
