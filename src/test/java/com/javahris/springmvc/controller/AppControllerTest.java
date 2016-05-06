package com.javahris.springmvc.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.javahris.springmvc.model.Employee;
import com.javahris.springmvc.service.DepartmentService;
import com.javahris.springmvc.service.EmployeeService;

public class AppControllerTest {

	@Mock
	EmployeeService service;

	@Mock
	DepartmentService deptService;

	@Mock
	MessageSource message;

	@Mock
	Authentication authentication;

	@Mock
	Principal principal;

	@Mock
	SecurityContext securityContext;

	@InjectMocks
	AppController appController;

	@Spy
	List<Employee> employees = new ArrayList<Employee>();

	@Spy
	ModelMap model;

	@Mock
	BindingResult result;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		employees = getEmployeeList();
	}

	@Test
	public void employeeProfile() {
		setupAuth();
		
		Employee emp = employees.get(0);
		when(authentication.getPrincipal()).thenReturn(emp.getEmail());
		when(service.findEmployeeByEmail(anyString())).thenReturn(emp);

		Assert.assertEquals(appController.employeeProfile(model), "home");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertNotNull(model.get("primBday"));
		Assert.assertNotNull(model.get("age"));
		Assert.assertTrue((Boolean) model.get("home"));
		Assert.assertEquals((int) ((Employee) model.get("employee")).getId(), 1);
	}

	@Test
	public void employeeInfoSameUser() {
		setupAuth();

		Employee emp1 = employees.get(0);
		when(authentication.getPrincipal()).thenReturn(emp1.getEmail());
		when(service.findById(anyInt())).thenReturn(emp1);
		when(service.findEmployeeByEmail(anyString())).thenReturn(emp1);

		Assert.assertEquals(appController.employeeInfo(emp1.getId(), model), "employeeinfo");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertNotNull(model.get("primBday"));
		Assert.assertTrue((Boolean) model.get("self"));
		Assert.assertTrue((Boolean) model.get("employeeinfo"));
	}

	@Test
	public void employeeInfoDiffUser() {
		setupAuth();

		Employee emp1 = employees.get(0);
		Employee emp2 = employees.get(1);
		when(authentication.getPrincipal()).thenReturn(emp1.getEmail());
		when(service.findById(anyInt())).thenReturn(emp1);
		when(service.findEmployeeByEmail(anyString())).thenReturn(emp2);

		Assert.assertEquals(appController.employeeInfo(emp1.getId(), model), "employeeinfo");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertNotNull(model.get("primBday"));
		Assert.assertFalse((Boolean) model.get("self"));
		Assert.assertTrue((Boolean) model.get("employeeinfo"));
	}

	@Test
	public void changePassword() {
		setupAuth();

		Employee emp1 = employees.get(0);
		when(authentication.getPrincipal()).thenReturn(emp1.getEmail());
		when(service.findEmployeeByEmail(anyString())).thenReturn(emp1);

		Assert.assertEquals(appController.changePassword(model), "changepassword");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertTrue((Boolean) model.get("changepassword"));
	}

	@Test
	public void savePasswordWrongOldPass() {
		setupAuth();

		Employee emp1 = employees.get(0);
		when(authentication.getPrincipal()).thenReturn(emp1.getEmail());
		when(service.findById(anyInt())).thenReturn(emp1);

		Assert.assertEquals(appController.savePassword(anyInt(), "bad", "haha", model), "passwordfail");
		Assert.assertTrue((Boolean) model.get("passwordfail"));
	}

	@Test
	public void savePasswordCorrectOldPass() {
		setupAuth();

		Employee emp1 = employees.get(0);
		when(authentication.getPrincipal()).thenReturn(emp1.getEmail());
		when(service.findById(anyInt())).thenReturn(emp1);

		Assert.assertEquals(appController.savePassword(anyInt(), "haha", "abc123", model), "success");
		Assert.assertEquals(model.get("passwordmsg"), "Password changed successfully");
	}

	@Test
	public void listEmployees() {
		when(service.findAllEmployees()).thenReturn(employees);
		Assert.assertEquals(appController.listEmployees(model), "allemployees");
		Assert.assertEquals(model.get("employees"), employees);
		verify(service, atLeastOnce()).findAllEmployees();
	}

	@Test
	public void newEmployee() {
		Assert.assertEquals(appController.newEmployee(model), "registration");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertFalse((Boolean) model.get("edit"));
		Employee employee = (Employee) (model.get("employee"));
		Assert.assertEquals(employee.getId(), (Integer) 0);
	}

	@Test
	public void saveEmployeeWithValidationError() {
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveEmployee(any(Employee.class));
		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "registration");
	}

	@Test
	public void saveEmployeeWithValidationErrorNonUniqueEmail() {
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeEmailUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "registration");
	}

	@Test
	public void saveEmployeeWithSuccess() {
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeEmailUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).saveEmployee(any(Employee.class));
		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Employee Mickey Cat Mouse  was registered successfully.");
	}

	@Test
	public void editEmployee() {
		Employee emp = employees.get(0);
		when(service.findEmployeeByEmpNumber(anyInt())).thenReturn(emp);
		Assert.assertEquals(appController.editEmployee(anyInt(), model), "registration");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertTrue((Boolean) model.get("edit"));
		Assert.assertEquals((int) ((Employee) model.get("employee")).getId(), 1);
	}

	@Test
	public void updateEmployeeWithValidationError() {
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateEmployee(any(Employee.class));
		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model, ""), "registration");
	}

	@Test
	public void updateEmployeeWithValidationErrorNonUniqueSSN() {
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeEmailUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model, ""), "registration");
	}

	@Test
	public void updateEmployeeWithSuccess() {
		when(result.hasErrors()).thenReturn(false);
		when(service.isEmployeeEmailUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).updateEmployee(any(Employee.class));
		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model, ""), "success");
		Assert.assertEquals(model.get("success"), "Employee Mickey Cat Mouse  was updated successfully.");
	}

	@Test
	public void deleteEmployee() {
		doNothing().when(service).deleteEmployeeByEmpNumber(anyInt());
		Assert.assertEquals(appController.deleteEmployee(model, 123), "success");
	}

	public List<Employee> getEmployeeList() {
		Employee e1 = new Employee();
		e1.setId(1);
		e1.setFirstName("Mickey");
		e1.setMiddleName("Cat");
		e1.setLastName("Mouse");
		e1.setPassword("abc123");
		e1.setEmail("mickey@mouse.com");
		e1.setDeptNumber(deptService.findById(1));
		e1.setPhoneNumber("1234567");
		e1.setBirthDate(new LocalDate());

		Employee e2 = new Employee();
		e2.setId(2);
		e2.setFirstName("Minnie");
		e2.setMiddleName("Cat");
		e2.setLastName("Mouse");
		e2.setPassword("abc123");
		e2.setEmail("minnie@mouse.com");
		e2.setDeptNumber(deptService.findById(1));
		e2.setPhoneNumber("1234567");
		e2.setBirthDate(new LocalDate());

		employees.add(e1);
		employees.add(e2);
		return employees;
	}

	public void setupAuth() {
		// Set mock auth
		SecurityContextHolder.setContext(securityContext);
		when(securityContext.getAuthentication()).thenReturn(authentication);
	}
}
