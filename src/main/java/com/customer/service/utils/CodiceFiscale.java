package com.customer.service.utils;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CodiceFiscaleValiation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodiceFiscale {
    String message() default "Codice Fiscale not valid";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
