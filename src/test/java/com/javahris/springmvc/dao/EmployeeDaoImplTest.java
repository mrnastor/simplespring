package com.javahris.springmvc.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.javahris.springmvc.model.Employee;
import com.javahris.springmvc.service.DepartmentService;
 
 
public class EmployeeDaoImplTest extends EntityDaoImplTest{
 
    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    DepartmentDao deptDao;
 
    @Override
    protected IDataSet getDataSet() throws Exception{
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Employee.xml"));
        return dataSet;
    }
     
    /* In case you need multiple datasets (mapping different tables) and you do prefer to keep them in separate XML's
    @Override
    protected IDataSet getDataSet() throws Exception {
      IDataSet[] datasets = new IDataSet[] {
              new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Employee.xml")),
              new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Benefits.xml")),
              new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Departements.xml"))
      };
      return new CompositeDataSet(datasets);
    }
    */
    

 
    @Test
    public void findById(){
        Assert.assertNotNull(employeeDao.findById(11111));
        Assert.assertNull(employeeDao.findById(3));
    }
 
     
    @Test
    public void saveEmployee(){
        employeeDao.saveEmployee(getSampleEmployee());
        Assert.assertEquals(employeeDao.findAllEmployees().size(), 3);
    }
     
    @Test
    public void deleteEmployeeByEmpNumber(){
        employeeDao.deleteEmployeeByEmpNumber(11111);
        Assert.assertEquals(employeeDao.findAllEmployees().size(), 1);
    }
     
    @Test
    public void deleteEmployeeByInvalidEmpNumber(){
        employeeDao.deleteEmployeeByEmpNumber(22222);
        Assert.assertEquals(employeeDao.findAllEmployees().size(), 2);
    }
 
    @Test
    public void findAllEmployees(){
        Assert.assertEquals(employeeDao.findAllEmployees().size(), 2);
    }
     
    @Test
    public void findEmployeeByEmpNumber(){
        Assert.assertNotNull(employeeDao.findEmployeeByEmpNumber(11111));
        Assert.assertNull(employeeDao.findEmployeeByEmpNumber(14545));
    }
 
    public Employee getSampleEmployee(){
        Employee e1 = new Employee();
        e1.setFirstName("Donald");
        e1.setMiddleName("Pato");
        e1.setLastName("Duck");
        e1.setPassword("abc123");
        e1.setEmail("donald@duck.com");
        e1.setDeptNumber(deptDao.findById(1));
        e1.setPhoneNumber("1234567");
        return e1;
    }
 
}