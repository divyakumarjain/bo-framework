package org.divy.common.bo.validation;


import org.divy.common.exception.UncheckedException;

public class BOValidationException extends UncheckedException {
    private final ValidationResults<?,?> validationResults;

    public BOValidationException(String message, String errorCode, ValidationResults<?,?> validationResults) {
        super(message, errorCode);
        this.validationResults = validationResults;
    }

    public ValidationResults<?,?> getValidationResults() {
        return validationResults;
    }
}
