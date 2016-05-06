package com.javahris.springmvc.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.javahris.springmvc.converter.DatePropertyEditor;
import com.javahris.springmvc.converter.DepartmentPropertyEditor;
import com.javahris.springmvc.model.Department;
import com.javahris.springmvc.model.Employee;
import com.javahris.springmvc.model.Project;
import com.javahris.springmvc.service.DepartmentService;
import com.javahris.springmvc.service.EmployeeService;
import com.javahris.springmvc.service.ProjectService;
import com.javahris.springmvc.validator.EmployeeValidator;
import java.util.Locale;
import org.springframework.validation.FieldError;

@Controller
@SessionAttributes({"admin", "employee"})
@RequestMapping("/")
public class AppController {

	@Autowired
	EmployeeService service;
        
    @Autowired
    DepartmentService departmentService;
    
    @Autowired
    ProjectService projService;

	@Autowired
	MessageSource messageSource;
	

	@ModelAttribute
	public void commonAttributes(Model model) {
		Employee employee = service.findEmployeeByEmail(getPrincipal());
		if( employee == null )
		{
			return;
		}

		Department dept = departmentService.findById(employee.getDeptNumber().getDeptNumber());
		boolean isAdmin = dept.getDeptName().equals("Administration") ? true: false;
		
		Project proj = projService.findByTeamLeadId(employee.getId());
		boolean isApprover = proj != null ? true: false;
		
		model.addAttribute("admin", isAdmin);
		model.addAttribute("leaveapprover", isAdmin | isApprover);
		model.addAttribute("username", employee.getFirstName() + " " + employee.getLastName());
		model.addAttribute("uid", employee.getId());
	}

	/*
	 * This method will show employee profile page.
	 */
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String employeeProfile(ModelMap model) {

		Employee employee = service.findEmployeeByEmail(getPrincipal());
		Date primBday = employee.getBirthDate().toDate();
		
		LocalDate now = new LocalDate();
		int age = Years.yearsBetween(employee.getBirthDate(), now).getYears();
		System.out.println(age);
	
		model.addAttribute("employee", employee);
		model.addAttribute("primBday", primBday);
		model.addAttribute("age", age);
		model.addAttribute("home", true);
		return "home";
	}
	
	/*
	 * This method will show employee information page.
	 */
	@RequestMapping(value = { "/empinfo-{empNumber}"}, method = RequestMethod.GET)
	public String employeeInfo(@PathVariable Integer empNumber, ModelMap model) {

		Employee employee = service.findById(empNumber);
		Date primBday = employee.getBirthDate().toDate();
		
		Employee loggedEmployee = service.findEmployeeByEmail(getPrincipal());
		
		model.addAttribute("self", loggedEmployee.getId().equals(empNumber));
		model.addAttribute("employee", employee);
		model.addAttribute("primBday", primBday);
		model.addAttribute("employeeinfo", true);
		return "employeeinfo";
	}
	
	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> employees = service.findAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";
	}
        
        /*
	 * For employee search
	 */
	@RequestMapping(value = "/search" , method = RequestMethod.GET)
	public String searchEmployees(@RequestParam(value = "searchText", required = false) String pSearchText, ModelMap model) {                

		List<Employee> employees = service.searchEmployees(pSearchText);
		model.addAttribute("employees", employees);
                
                model.addAttribute("searchText", pSearchText);
                
		return "allemployees";
	}

	/*
	 * This method will provide the medium to change the current password.
	 */
	@RequestMapping(value = { "/changepassword" }, method = RequestMethod.GET)
	public String changePassword(ModelMap model) {
		Employee employee = service.findEmployeeByEmail(getPrincipal());
		
		model.addAttribute("employee", employee);
		model.addAttribute("changepassword", true);
		return "changepassword";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving new password in database. It also validates the input current password
	 */
	@RequestMapping(value = { "/changepassword" }, method = RequestMethod.POST)
	@ResponseBody
	public String savePassword(	@RequestParam("empId") int empId, 
								@RequestParam("newPass") String newPass,
								@RequestParam("oldPass") String oldPass,
								ModelMap model) {
				
		Employee employee = service.findById(empId);
		if( !employee.getPassword().equals(oldPass) )
		{
			model.addAttribute("passwordfail", true);
			return "passwordfail";
		}
		
		service.updatePassword(employee, newPass);

		model.addAttribute("passwordmsg", "Password changed successfully");
		return "success";
	}	
	
	
	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap model) {

                EmployeeValidator formValidation = new EmployeeValidator();
                
                formValidation.validate(employee, result);
                model.addAttribute("edit", false);
                
		if (result.hasErrors()) {
			return "registration";
		}

                if(!service.isEmployeeEmailUnique(employee.getId(), employee.getEmail())){
                    FieldError emailError =new FieldError("employee","email",messageSource.getMessage("non.unique.email", new String[]{employee.getEmail()}, Locale.getDefault()));
                    result.addError(emailError);
                    return "registration";
                }

		service.saveEmployee(employee);
                
		model.addAttribute("success", "Employee " + employee.getFullName() + " was registered successfully.");
		return "success";
	}

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{empNumber}-employee" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable Integer empNumber, ModelMap model) {
		Employee employee = service.findEmployeeByEmpNumber(empNumber);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{id}-employee" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model,
			@PathVariable String id) {
            
                EmployeeValidator formValidation = new EmployeeValidator();
                
                formValidation.validate(employee, result);
                model.addAttribute("edit", true);
                
		if (result.hasErrors()) {
			return "registration";
		}

                if(!service.isEmployeeEmailUnique(employee.getId(), employee.getEmail())){
                    FieldError emailError =new FieldError("employee","email",messageSource.getMessage("non.unique.email", new String[]{employee.getEmail()}, Locale.getDefault()));
                    result.addError(emailError);
                    return "registration";
                }

		service.updateEmployee(employee);
		model.addAttribute("success", "Employee " + employee.getFullName() + " was updated successfully.");
		return "success";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		((Model) model).asMap().clear();
		return "redirect:/login?logout";
	}

	/*
	 * This method will delete an employee by it's empNumber value.
	 */
	@RequestMapping(value = { "/delete-{id}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(ModelMap model, @PathVariable Integer id) {
		service.deleteEmployeeByEmpNumber(id);
                model.addAttribute("deleted", true);
                model.addAttribute("success", "The employee was deleted successfully.");
		return "success";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
        
        @InitBinder
        public void initBinder(WebDataBinder binder, WebRequest webRequest) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            dateFormat.setLenient(false);
            binder.registerCustomEditor(LocalDate.class, "birthDate", new DatePropertyEditor(webRequest));
            binder.registerCustomEditor(Department.class, new DepartmentPropertyEditor());
        }
        
        @ModelAttribute("yearList")
        public List<String> birthYearList() {
            List<String> yearList = new ArrayList<String>();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            for (int i=year-10; i>(year-70); i-- ) {
                yearList.add(Integer.toString(i));
            }
            return yearList;
	}
        
        @ModelAttribute("monthList")
        public List<String> birthMonthList() {
            List<String> monthList = new ArrayList<String>();
            for (int i=1; i <= 12; i++ ) {
                monthList.add(Integer.toString(i));
            }
            return monthList;
	}
        
        @ModelAttribute("dayOfMonthList")
        public List<String> dayofMonthList() {
            List<String> dayOfMonthList = new ArrayList<String>();
            for (int i=1; i <= 31; i++ ) {
                dayOfMonthList.add(Integer.toString(i));
            }
            return dayOfMonthList;
	}
        
        @ModelAttribute("departmentList")
        public List<Department> departmentList() {
            
            List<Department> departments = departmentService.findAllDepartments();
            
            return departments;
        }

}
