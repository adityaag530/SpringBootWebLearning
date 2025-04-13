package com.aditya.springbootwebtutorial.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/*
 * @author adityagupta
 * @date 12/04/25
 */

@Entity // this will tell spring data jpa/hibernate that this is to be converted to table
@Table(name = "employees") // specify a custom table name
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeEntity {

    @Id // user don't have to provide id it will auto increment
    @GeneratedValue(strategy = GenerationType.AUTO) // tell hibernate to auto increment the id value
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;



}
