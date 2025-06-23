package org.divy.common.bo.validation;

import org.divy.common.bo.repository.BusinessObject;

import jakarta.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ValidationResults<B extends BusinessObject<I>, I> implements Serializable {
    Collection<ValidationResult<B, I>> results;

    public ValidationResults() {
        this.results = new HashSet<>();
    }

    public Collection<ValidationResult<B,I>> getResults() {
        return results;
    }

    public void addValidationResult(ValidationResult<B,I> result) {
        this.results.add(result);
    }

    public void addValidationResults(Collection<ValidationResult<B,I>> results) {
        this.results.addAll(results);
    }

    public void addConstraintViolation(Collection<ConstraintViolation<B>> results) {
        this.results.addAll(results.stream().map(ValidationResult::new).collect(Collectors.toCollection(()-> new ArrayList<>(results.size()))));
    }

    public boolean isEmpty() {
        return results.isEmpty();
    }
}
