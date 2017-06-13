package org.divy.common.bo.business.validation;

import java.util.HashSet;
import java.util.Set;

public class ValidationResults {
    Set<ValidationResult> results;

    public ValidationResults(Set<ValidationResult> results) {
        this.results = results;
    }

    public ValidationResults() {
        this.results = new HashSet<>();
    }

    public Set<ValidationResult> getResults() {
        return results;
    }

    public void addValidationResult(ValidationResult result) {
        this.results.add(result);
    }

    public void addValidationResults(Set<ValidationResult> results) {
        this.results.addAll(results);
    }

    public void addValidationResults(ValidationResults results) {
        this.results.addAll(results.getResults());
    }

    private class ValidationResult {
    }


}
