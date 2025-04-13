package com.aditya.springbootwebtutorial.dto;


/*
 * @author adityagupta
 * @date 12/04/25
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    // jackson tries to serialize java to json and back to java
    // isActive is converted to json string and confused with is Active
    // we can use JsonProperty to rename it to something
    @JsonProperty("isActive")
    private Boolean isActive;

}
