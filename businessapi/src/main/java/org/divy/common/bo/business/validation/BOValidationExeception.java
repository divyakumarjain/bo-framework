package org.divy.common.bo.business.validation;


import org.divy.common.exception.CheckedException;

public class BOValidationExeception extends CheckedException {
    private final ValidationResults validationResults;

    public BOValidationExeception(String message, String errorCode, ValidationResults validationResults) {
        super(message, errorCode);
        this.validationResults = validationResults;
    }

    public ValidationResults getValidationResults() {
        return validationResults;
    }
}
