package com.jhonatan.biblioteca.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CedulaValidator.class)
@Documented
public @interface Cedula {

    String message() default "La cédula ecuatoriana no es válida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}