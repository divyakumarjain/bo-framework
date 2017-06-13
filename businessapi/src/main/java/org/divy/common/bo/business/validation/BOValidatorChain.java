package org.divy.common.bo.business.validation;

import org.divy.common.bo.BusinessObject;

import java.util.ArrayList;
import java.util.List;

public class BOValidatorChain<B extends BusinessObject<I>, I> implements BOValidator<B, I> {

    final List<BOValidator> validators;

    public BOValidatorChain(List<BOValidator> validators) {
        this.validators = new ArrayList<>(validators);
    }

    public void addValidator(BOValidator<B,I> validator) {
        this.validators.add(validator);
    }

    @Override
    public ValidationResults validate(B businessObject) {
        return validators.stream()
                .map(boValidator -> boValidator.validate(businessObject))
                .reduce(new ValidationResults(), (validationResults, results) -> {validationResults.addValidationResults(results); return validationResults;});
    }
}
