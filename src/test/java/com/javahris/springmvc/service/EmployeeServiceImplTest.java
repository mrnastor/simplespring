package com.javahris.springmvc.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.javahris.springmvc.dao.EmployeeDao;
import com.javahris.springmvc.model.Employee;
 
public class EmployeeServiceImplTest {
 
    @Mock
    EmployeeDao dao;
    
    @Mock
    DepartmentService deptService;
     
    @InjectMocks
    EmployeeServiceImpl employeeService;
     
    @Spy
    List<Employee> employees = new ArrayList<Employee>();
     
    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        employees = getEmployeeList();
    }
 
    @Test
    public void findById(){
        Employee emp = employees.get(0);
        when(dao.findById(anyInt())).thenReturn(emp);
        Assert.assertEquals(employeeService.findById(emp.getId()),emp);
    }
 
    @Test
    public void saveEmployee(){
        doNothing().when(dao).saveEmployee(any(Employee.class));
        employeeService.saveEmployee(any(Employee.class));
        verify(dao, atLeastOnce()).saveEmployee(any(Employee.class));
    }
     
    @Test
    public void updateEmployee(){
        Employee emp = employees.get(0);
        when(dao.findById(anyInt())).thenReturn(emp);
        employeeService.updateEmployee(emp);
        verify(dao, atLeastOnce()).findById(anyInt());
    }
 
    @Test
    public void deleteEmployeeBySsn(){
        doNothing().when(dao).deleteEmployeeByEmpNumber(anyInt());
        employeeService.deleteEmployeeByEmpNumber(anyInt());
        verify(dao, atLeastOnce()).deleteEmployeeByEmpNumber(anyInt());
    }
     
    @Test
    public void findAllEmployees(){
        when(dao.findAllEmployees()).thenReturn(employees);
        Assert.assertEquals(employeeService.findAllEmployees(), employees);
    }
     
    @Test
    public void findEmployeeBySsn(){
        Employee emp = employees.get(0);
        when(dao.findEmployeeByEmpNumber(anyInt())).thenReturn(emp);
        Assert.assertEquals(employeeService.findEmployeeByEmpNumber(anyInt()), emp);
    }     
     
    public List<Employee> getEmployeeList(){
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setFirstName("Mickey");
        e1.setMiddleName("Cat");
        e1.setLastName("Mouse");
        e1.setPassword("abc123");
        e1.setEmail("mickey@mouse.com");
        e1.setDeptNumber(deptService.findById(1));
        e1.setPhoneNumber("1234567");
         
        Employee e2 = new Employee();
        e2.setId(2);
        e1.setFirstName("Minnie");
        e1.setMiddleName("Cat");
        e1.setLastName("Mouse");
        e1.setPassword("abc123");
        e1.setEmail("minnie@mouse.com");
        e1.setDeptNumber(deptService.findById(1));
        e1.setPhoneNumber("1234567");
         
        employees.add(e1);
        employees.add(e2);
        return employees;
    }
     
}
