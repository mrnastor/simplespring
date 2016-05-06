/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.service;

import com.javahris.springmvc.model.Department;
import java.util.List;

/**
 *
 * @author jem
 */
public interface DepartmentService {
    
    Department findById(int id);
    
    List<Department> findAllDepartments();
    
}
