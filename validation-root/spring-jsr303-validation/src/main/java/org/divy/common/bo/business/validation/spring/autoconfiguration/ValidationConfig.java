package org.divy.common.bo.business.validation.spring.autoconfiguration;

import org.divy.common.bo.business.validation.BOValidator;
import org.divy.common.bo.business.validation.BOValidatorChain;
import org.divy.common.bo.business.validation.jsr303.JSR303Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validator;
import java.util.ArrayList;

@Configuration
public class ValidationConfig {

    @Bean
    public JSR303Validator jsr303validator(Validator jsr303Validator) {
        return new JSR303Validator(jsr303Validator);
    }

    @Bean
    public BOValidatorChain<?, ?> boValidator(JSR303Validator jsr303validator) {
        ArrayList<BOValidator> validators = new ArrayList<>();
        validators.add(jsr303validator);
        return new BOValidatorChain<>(validators);
    }
}
