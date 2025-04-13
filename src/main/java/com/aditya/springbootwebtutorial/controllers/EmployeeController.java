package com.aditya.springbootwebtutorial.controllers;


import com.aditya.springbootwebtutorial.dto.EmployeeDTO;
import com.aditya.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.aditya.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id ){
        // "employeeId" in path should match with the argument or specify in @PathVariable
//        return new EmployeeDTO(id, "abc", "abc.gmail.com", 24, LocalDate.of(2025, 4, 12), true);

        //just for testing i am dealing with entity we should always us dto in controller.
//        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
//        if (employeeDTO == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(employeeDTO);

        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
//        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).orElse(ResponseEntity.notFound().build());
//        return employeeDTO.map((employeeDTO1 -> ResponseEntity.ok(employeeDTO1)))
//                .orElseThrow(()-> new NoSuchElementException("Employee not found"));
        return employeeDTO.map((employeeDTO1 -> ResponseEntity.ok(employeeDTO1)))
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    // all the exception occur with NoSuchElementException are handled but the below handler
    /*
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception){
        return new ResponseEntity<>("Employee was not found", HttpStatus.NOT_FOUND);
    }
    */

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                             @RequestParam(required = false, name = "sort") String sortBy){
        // to make query fields optional use required
        // "age" name should match with the query param "?age=27" in the url endpoint
//        return "Hi " + age + sortBy;
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // to pass complex data we need to use @RequestBody
    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
        // all request are get by default from browser so to test post use postman
//        return "Hello new Employee Created";
        EmployeeDTO  employeeDTO = employeeService.createNewEmployee(inputEmployee);
        // new ResponseEntity when we want to pass a custome http status code
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,
                                          @PathVariable Long employeeId){
        // change whole data put mapping is used
        // if you provide few employee fields in put mapping then rest field will get updated to null as it will update the whole instance
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if (gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
