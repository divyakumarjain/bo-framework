package org.divy.common.bo.business.validation;

import org.divy.common.bo.BusinessObject;

import java.util.List;

@FunctionalInterface
public interface BOValidator<B extends BusinessObject<I>, I> {
    List<ValidationResult> validate(B businessObject);
}
