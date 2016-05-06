/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.dao;

import com.javahris.springmvc.configuration.HibernateTestConfiguration;
import com.javahris.springmvc.model.Department;
import com.javahris.springmvc.model.Employee;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * JUnit implementation of the EmployeeDao test
 *
 * @author jem
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(classes=HibernateTestConfiguration.class) 
public class EmployeeDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    
    @Autowired
    DataSource dataSource;
    
    @Resource
    private EmployeeDaoImpl employeeDao;
    
    @Resource
    private DepartmentDaoImpl deptDao;
    
//    @Autowired
//    SessionFactory sessionFactory;
    
    public EmployeeDaoTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        IDatabaseConnection dbConn = new DatabaseDataSourceConnection(
                dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
        
//        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(sessionFactory.openSession()));
    }
    
    protected IDataSet getDataSet() throws Exception{
        IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Employee.xml"));
        return dataSet;
    }
    
//    @After
//    public void tearDown() {
//        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
//        SessionFactoryUtils.closeSession(sessionHolder.getSession());
//    }

    /**
     * Test of findById method, of class EmployeeDao.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Employee result = employeeDao.findById(11111);
        assertNotNull(result);
    }

    /**
     * Test of deleteEmployeeByEmpNumber method, of class EmployeeDao.
     */
    @Test
    public void testDeleteEmployeeByEmpNumber() {
        System.out.println("deleteEmployeeByEmpNumber");
        Integer empNumber = 2;
        employeeDao.deleteEmployeeByEmpNumber(empNumber);
        assertNull(employeeDao.findById(empNumber));
    }

    /**
     * Test of findAllEmployees method, of class EmployeeDao.
     */
    @Test
    public void testFindAllEmployees() {
        System.out.println("findAllEmployees");
        assertFalse(employeeDao.findAllEmployees().isEmpty());
    }
    
    /**
     * Test of searchEmployees method, of class EmployeeDao.
     */
    @Test
    public void testSearchEmployees() {
        System.out.println("searchEmployees");
        String name = "Mouse";
        List<Employee> result = employeeDao.searchEmployees(name);
        assertEquals(result.size(), 2);
    }

    /**
     * Test of findEmployeeByEmpNumber method, of class EmployeeDao.
     */
    @Test
    public void testFindEmployeeByEmpNumber() {
        System.out.println("findEmployeeByEmpNumber");
        Integer empNumber = 2;
        Employee result = employeeDao.findEmployeeByEmpNumber(empNumber);
        assertNotNull(result);
    }

    /**
     * Test of findEmployeeByEmail method, of class EmployeeDao.
     */
    @Test
    public void testFindEmployeeByEmail() {
        System.out.println("findEmployeeByEmail");
        String email = "minnie@mouse.com";
        Employee result = employeeDao.findEmployeeByEmail(email);
        assertNotNull(result);
    }
    
    /**
     * Test of saveEmployee method, of class EmployeeDao.
     */
    @Test
    @Rollback(true)
    public void testSaveEmployee() {
        System.out.println("saveEmployee");
        
        Employee employee = new Employee();
        employee.setLastName("Simpson");
//        employee.setMiddleName("John");
        employee.setFirstName("Homer");
        employee.setEmail("simps@gmail.com");
        employee.setPassword("abc123");
        employee.setBirthDate(new LocalDate(1980, 12, 10));
        employee.setDeptNumber(deptDao.findById(1));
        employee.setPhoneNumber("55661212");
        employeeDao.saveEmployee(employee);
        
        List<Employee> employees = employeeDao.findAllEmployees();
        
        assertEquals(employees.size(), 3);
        assertEquals(employee.getEmail(), employees.get(employees.size()-1).getEmail());
    }
}
