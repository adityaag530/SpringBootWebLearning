package com.aditya.springbootwebtutorial.repositories;


/*
 * @author adityagupta
 * @date 12/04/25
 */

import com.aditya.springbootwebtutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    // here before hibernate we need to create method with queries with own implementation
    // but now spring data jpa define the crud operation and complex queries using JPQL
    // we can define custom methods also which will automatically handled by jpa

}
