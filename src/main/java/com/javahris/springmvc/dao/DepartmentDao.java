/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.dao;

import com.javahris.springmvc.model.Department;
import java.util.List;

/**
 *
 * @author jem
 */
public interface DepartmentDao {
    
    Department findById(int id);
    
    List<Department> findAllDepartments();
    
}
