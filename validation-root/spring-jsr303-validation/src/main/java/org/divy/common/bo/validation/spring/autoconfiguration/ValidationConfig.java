package org.divy.common.bo.validation.spring.autoconfiguration;

import org.divy.common.bo.repository.BusinessObject;
import org.divy.common.bo.validation.BOValidator;
import org.divy.common.bo.validation.BOValidatorChain;
import org.divy.common.bo.validation.jsr303.JSR303Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.Validator;
import java.util.ArrayList;

@Configuration
public class ValidationConfig {

    @Bean
    public <B extends BusinessObject<I>,I> JSR303Validator<B,I> jsr303validator(Validator jsr303Validator) {
        return new JSR303Validator<>(jsr303Validator);
    }

    @Bean
    public <B extends BusinessObject<I>,I> BOValidatorChain<B, I> boValidator(JSR303Validator<B,I> jsr303validator) {
        var validators = new ArrayList<BOValidator<B,I>>();
        validators.add(jsr303validator);
        return new BOValidatorChain<>(validators);
    }
}
