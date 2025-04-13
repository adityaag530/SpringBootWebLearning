package com.aditya.springbootwebtutorial.controllers;


import com.aditya.springbootwebtutorial.dto.EmployeeDTO;
import com.aditya.springbootwebtutorial.entities.EmployeeEntity;
import com.aditya.springbootwebtutorial.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @author adityagupta
 * @date 12/04/25
 */
//@RestController - you can go inside Rest controller and see what other annotation it is made of
//@Controller - you have to explicitly use @RequestBody for json data/ it is to return views

@RestController // all will return json/xml
@RequestMapping("/employees") // parent mapping for all the child endpoint
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /*
    @GetMapping(path = "/secret")
    public String getMySuperSecretMessage(){
        return "Secret Message: asdjfaljtioanald";
    }
    */

    // Dynamic URLs Path
    // @PathVariable - /employees/123 - when parameter is an essential part of url path that identifies a resource
    // @ResuestParam - /employees?id=123 - when parameter is optional and used for filtering, sorting or modification to the request

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id ){
        // "employeeId" in path should match with the argument or specify in @PathVariable
//        return new EmployeeDTO(id, "abc", "abc.gmail.com", 24, LocalDate.of(2025, 4, 12), true);

        //just for testing i am dealing with entity we should always us dto in controller.
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                             @RequestParam(required = false, name = "sort") String sortBy){
        // to make query fields optional use required
        // "age" name should match with the query param "?age=27" in the url endpoint
//        return "Hi " + age + sortBy;
        return employeeService.getAllEmployees();
    }

    // to pass complex data we need to use @RequestBody
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
        // all request are get by default from browser so to test post use postman
//        return "Hello new Employee Created";

        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmployee(){
        return "Hello from put";
    }

}
