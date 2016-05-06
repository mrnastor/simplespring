package com.javahris.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javahris.springmvc.dao.EmployeeDao;
import com.javahris.springmvc.model.Employee;
 
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
 
    @Autowired
    private EmployeeDao dao;
     
    public Employee findById(int id) {
        return dao.findById(id);
    }
 
    public void saveEmployee(Employee employee) {
        dao.saveEmployee(employee);
    }
 
    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
    public void updateEmployee(Employee employee) {
        Employee entity = dao.findById(employee.getId());
        if(entity!=null){
            entity.setLastName(employee.getLastName());
            entity.setMiddleName(employee.getMiddleName());
            entity.setFirstName(employee.getFirstName());
            entity.setEmail(employee.getEmail());
            entity.setPosition(employee.getPosition());
            entity.setBirthDate(employee.getBirthDate());
            entity.setGender(employee.getGender());
            entity.setPhoneNumber(employee.getPhoneNumber());
            entity.setAddress(employee.getAddress());
        }
    }
    
    public void updatePassword(Employee employee, String password) {
        Employee entity = dao.findById(employee.getId());
        if(entity!=null){
        	entity.setPassword(password);
        }
    }
 
    public void deleteEmployeeByEmpNumber(Integer empNumber) {
        dao.deleteEmployeeByEmpNumber(empNumber);
    }
     
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }
    
    public List<Employee> searchEmployees(String searchText) {
        return dao.searchEmployees(searchText);
    }
 
    public Employee findEmployeeByEmpNumber(Integer ssn) {
        return dao.findEmployeeByEmpNumber(ssn);
    }
    
    public Employee findEmployeeByEmail(String email) {
        return dao.findEmployeeByEmail(email);
    }
 
    public boolean isEmployeeEmailUnique(Integer id, String email) {
        Employee employee = findEmployeeByEmail(email);
        return ( employee == null || ((id != null) && (employee.getId()== id)));
    }
     
}
