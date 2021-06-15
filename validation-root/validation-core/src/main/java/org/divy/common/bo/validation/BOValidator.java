package org.divy.common.bo.validation;

import org.divy.common.bo.repository.BusinessObject;

public interface BOValidator<B extends BusinessObject<I>, I> {
    ValidationResults validate(B businessObject);
    ValidationResults validate(B businessObject, Class<?> validationGroup);
    ValidationResults validateCreate(B businessObject);
    ValidationResults validateUpdate(B businessObject);
    ValidationResults validateDelete(B businessObject);
}
