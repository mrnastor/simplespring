package com.javahris.springmvc.dao;

import com.javahris.springmvc.model.Employee;
import java.util.List;


public interface EmployeeDao {
 
    Employee findById(int id);
 
    void saveEmployee(Employee employee);
     
    void deleteEmployeeByEmpNumber(Integer ssn);
     
    List<Employee> findAllEmployees();
    
    List<Employee> searchEmployees(String name);
 
    Employee findEmployeeByEmpNumber(Integer ssn);
    
    Employee findEmployeeByEmail(String email);
 
}
