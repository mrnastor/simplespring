package com.javahris.springmvc.service;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;

import com.javahris.springmvc.model.Employee;

 
public interface EmployeeService {
	
	@PostAuthorize("returnObject.email == authentication.name or hasRole('ADMIN')")
    Employee findById(int id);
    
    @PostAuthorize("#employee.email == authentication.name or hasRole('ADMIN')")
    void saveEmployee(Employee employee);
     
    @PostAuthorize("#employee.email == authentication.name or hasRole('ADMIN')")
    void updateEmployee(Employee employee);
    
    void updatePassword(Employee employee, String password);
    
    @PostAuthorize("hasRole('ADMIN')")
    void deleteEmployeeByEmpNumber(Integer empNumber);

    @PostAuthorize("hasRole('ADMIN')")
    List<Employee> findAllEmployees(); 
    
    @PostAuthorize("hasRole('ADMIN')")
    List<Employee> searchEmployees(String searchText); 
    
    @PostAuthorize("returnObject.email == authentication.name or hasRole('ADMIN')")
    Employee findEmployeeByEmpNumber(Integer ssn);
    
    Employee findEmployeeByEmail(String email);
 
    boolean isEmployeeEmailUnique(Integer id, String email);
     
}
