package com.aditya.springbootwebtutorial.annotations;


/*
 * @author adityagupta
 * @date 13/04/25
 */

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {

    // in ConstraintValidator<here pass the annotaion class, type>
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // s is the String that user is passing
        List<String> roles = List.of("USER", "ADMIN");
        return roles.contains(s);
    }
}
