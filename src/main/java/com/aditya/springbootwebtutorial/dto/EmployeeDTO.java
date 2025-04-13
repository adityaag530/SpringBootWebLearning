package com.aditya.springbootwebtutorial.dto;


/*
 * @author adityagupta
 * @date 12/04/25
 */

import com.aditya.springbootwebtutorial.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;
    // you also need to add @Valid to the controller to check validation there
    //@NotNull(message = "Required filed in Employee: name")// adding validation to name
    //@NotEmpty(message = "Required filed must not be empty or of zero length e.g ''") // used with collection, string and arrays
    @NotBlank(message = "Required filed should not have only black spaces with length more that 0")//should be mostly used with strings
    @Size(min = 3, max = 10, message = "Number of characters in name should be in range[3,10]")
    private String name;

    @Email(message = "Email is not correct")
    @NotBlank(message = "Email of the employee cannot be blank")
    private String email;

    @Max(value=80, message = "Age cannot be greater than 80")
    @Min(value = 25, message = "Age cannot be less than 25")
    @NotNull(message = "Age of the employee cannot be blank")
    private Integer age;

    //@Pattern(regexp = "^(ADMIN|USER)$", message = "Role can be either USER or ADMIN only")
    @NotBlank(message = "Role of the employee cannot be blank")
    @EmployeeRoleValidation
    private String role; //ADMIN, USER

    @Positive(message = "Salary of Employee should be positive")
    @NotNull(message = "Salary of Employee should not be null")
    @Digits(integer = 6, fraction = 2, message = "Salary can be in form xxxxxx.xx")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.99")
    private Double salary;

    @PastOrPresent(message = "Date of Joining should be of future")//to check date is past or present are allowed
    private LocalDate dateOfJoining;

    // jackson tries to serialize java to json and back to java
    // isActive is converted to json string and confused with is Active
    // we can use JsonProperty to rename it to something
    @JsonProperty("isActive")
    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;


}
