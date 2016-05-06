/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.service;

import com.javahris.springmvc.dao.DepartmentDao;
import com.javahris.springmvc.model.Department;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jem
 */
@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
    
    @Autowired
    private DepartmentDao dao;
    
    public Department findById(int id) {
        return dao.findById(id);
    }
    
    public List<Department> findAllDepartments() {
        return dao.findAllDepartments();
    }
}
