package org.divy.common.bo.validation;

import org.divy.common.bo.repository.BusinessObject;

import java.util.ArrayList;
import java.util.List;

public class BOValidatorChain<B extends BusinessObject<I>, I> extends AbstractBOValidator<B, I> {

    private final List<org.divy.common.bo.validation.BOValidator<B,I>> validators;

    public BOValidatorChain(List<org.divy.common.bo.validation.BOValidator<B,I>> validators) {
        this.validators = new ArrayList<>(validators);
    }

    public void addValidator(org.divy.common.bo.validation.BOValidator<B,I> validator) {
        this.validators.add(validator);
    }

    @Override
    public ValidationResults validate(B businessObject) {
        return validators.stream()
                .map(boValidator -> boValidator.validate(businessObject))
                .reduce(new ValidationResults(), (result1, result2) -> {
                    result1.addValidationResults(result2.getResults());
                    return  result1;
                });
    }

    @Override
    public ValidationResults validate(B businessObject, Class<?> validationGroup)
    {
        return validators.stream()
                .map(boValidator -> boValidator.validate(businessObject,validationGroup))
                .reduce(new ValidationResults(), (result1, result2) -> {
                    result1.addValidationResults(result2.getResults());
                    return  result1;
                });
    }
}
