package org.divy.common.bo.business.validation.jsr303;

import org.divy.common.bo.BusinessObject;
import org.divy.common.bo.business.validation.BOValidator;
import org.divy.common.bo.business.validation.ValidationResult;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

public class JSR303Validator implements BOValidator {

    Validator validator;

    public JSR303Validator(Validator validator) {
        this.validator = validator;
    }
    @Override
    public List<ValidationResult> validate(BusinessObject businessObject) {
        return validator.validate(businessObject)
                .stream()
                .map(ValidationResult::new)
                .collect(Collectors.toList());
    }
}
