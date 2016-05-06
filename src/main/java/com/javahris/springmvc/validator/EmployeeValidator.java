/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.validator;

import com.javahris.springmvc.model.Employee;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author jem
 */
public class EmployeeValidator implements Validator {
    
    private Pattern pattern;  
    private Matcher matcher;
    
    String PHONE_PATTERN = "[0-9()-\\.]*";  
    
    @Override
    public boolean supports(Class clazz) {
        return Employee.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        
        Employee employee = (Employee)target;

        // phone number validation
        if (!(employee.getPhoneNumber() != null && employee.getPhoneNumber().isEmpty())) {
            pattern = Pattern.compile(PHONE_PATTERN);
            matcher = pattern.matcher(employee.getPhoneNumber());
            if (!matcher.matches()) {
                errors.rejectValue("phoneNumber", "Incorrect");
            }
        }
    }
    
}
