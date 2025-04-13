package com.aditya.springbootwebtutorial.services;


import com.aditya.springbootwebtutorial.dto.EmployeeDTO;
import com.aditya.springbootwebtutorial.entities.EmployeeEntity;
import com.aditya.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.aditya.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * @author adityagupta
 * @date 13/04/25
 */
@Service
public class EmployeeService {

    // in service you can have custom logic
    // check for employee if he is admin
    // logging


    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
//        ModelMapper mapper = new ModelMapper();
        // we dont want to initialize model mapper in each method so we will add in configs pacakge
//        return modelMapper.map(employeeEntity, EmployeeDTO.class);
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity, EmployeeDTO.class));
          return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {

       EmployeeEntity employeeEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
       EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
       return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        // what if that particular empId is not present in db
        // ask product manager for clarity what can be done, should we save it to db as user is sending a new empDTO details
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        //user might not have provided the employee id in dto
        employeeEntity.setId(employeeId);
        // save will work as hashmap if present update, else create a new
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        // Reflection - directly go object any object and update the fields directly
        updates.forEach( (field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            // making field public so that it can be updated
            fieldToBeUpdated.setAccessible(true);
            // we are updating the local object of employee entity which is declared at line 82
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    public void isExistsByEmployeeId(Long employeeId){
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id: "+ employeeId);
    }
}
