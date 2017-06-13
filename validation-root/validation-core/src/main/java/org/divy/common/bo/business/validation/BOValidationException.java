package org.divy.common.bo.business.validation;


import org.divy.common.exception.UncheckedException;

import java.util.List;

public class BOValidationException extends UncheckedException {
    private final List<ValidationResult> validationResults;

    public BOValidationException(String message, String errorCode, List<ValidationResult> validationResults) {
        super(message, errorCode);
        this.validationResults = validationResults;
    }

    public List<ValidationResult> getValidationResults() {
        return validationResults;
    }
}
