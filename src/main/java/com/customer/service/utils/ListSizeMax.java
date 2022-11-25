package com.customer.service.utils;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ListSizeMaxConstraint.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListSizeMax {
    String message() default "List size error";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
