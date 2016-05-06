/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.dao;

import com.javahris.springmvc.model.Department;
import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jem
 */
@Repository("departmentDao")
public class DepartmentDaoImpl extends AbstractDao<Integer, Department> implements DepartmentDao {
    
    public Department findById(int id) {
        return getByKey(id);
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Department> findAllDepartments() {
        Criteria criteria = createEntityCriteria();
        return (List<Department>) criteria.list();
    }
}
