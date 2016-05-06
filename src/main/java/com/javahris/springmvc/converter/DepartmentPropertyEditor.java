/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.converter;

import com.javahris.springmvc.model.Department;
import com.javahris.springmvc.service.DepartmentService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jem
 */
public class DepartmentPropertyEditor extends PropertyEditorSupport{
    
    @Autowired
    DepartmentService dService;
    
    @Override
    public void setAsText(String id) {
        
        Department d = dService.findById(Integer.parseInt(id));
        this.setValue(d);
    }
    
}
