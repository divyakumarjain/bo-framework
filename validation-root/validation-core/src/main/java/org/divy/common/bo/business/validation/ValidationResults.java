package org.divy.common.bo.business.validation;

import org.divy.common.bo.BusinessObject;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class ValidationResults {
    Collection<ValidationResult> results;

    public ValidationResults() {
        this.results = new HashSet<>();
    }

    public Collection<ValidationResult> getResults() {
        return results;
    }

    public void addValidationResult(ValidationResult result) {
        this.results.add(result);
    }

    public void addValidationResults(Collection<ValidationResult> results) {
        this.results.addAll(results);
    }

    public void addConstraintViolation(Collection<ConstraintViolation<BusinessObject>> results) {
        this.results.addAll(results.stream().map(ValidationResult::new).collect(Collectors.toCollection(()-> new ArrayList<>(results.size()))));
    }

    public boolean isEmpty() {
        return results.isEmpty();
    }

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


}
