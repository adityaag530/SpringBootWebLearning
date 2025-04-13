package com.aditya.springbootwebtutorial.annotations;


/*
 * @author adityagupta
 * @date 13/04/25
 */

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)// when to work
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {

    String message() default "Role can be either USER or ADMIN only";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
