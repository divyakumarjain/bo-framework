package org.divy.common.bo.business.validation;

import org.divy.common.bo.BusinessObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BOValidatorChain<B extends BusinessObject<I>, I> implements BOValidator<B, I> {

    final List<BOValidator> validators;

    public BOValidatorChain(List<BOValidator> validators) {
        this.validators = new ArrayList<>(validators);
    }

    public void addValidator(BOValidator<B,I> validator) {
        this.validators.add(validator);
    }

    @Override
    public List<ValidationResult> validate(B businessObject) {
        return validators.stream()
                .map((BOValidator boValidator) -> (List<ValidationResult>)boValidator.validate(businessObject))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
