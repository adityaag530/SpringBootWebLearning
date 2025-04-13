package com.aditya.springbootwebtutorial.services;


import com.aditya.springbootwebtutorial.dto.EmployeeDTO;
import com.aditya.springbootwebtutorial.entities.EmployeeEntity;
import com.aditya.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
//        ModelMapper mapper = new ModelMapper();
        // we dont want to initialize model mapper in each method so we will add in configs pacakge
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
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
}
