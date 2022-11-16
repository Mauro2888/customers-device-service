package com.customer.service.utils;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ListSizeMaxConstraint.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListSizeMax {
    String message() default "List size must be less than or equal to 2";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
