package com.javahris.springmvc.dao;

import com.javahris.springmvc.model.Employee;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
 
@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {
 
    public Employee findById(int id) {
        return getByKey(id);
    }
 
    public void saveEmployee(Employee employee) {
        persist(employee);
    }
 
    public void deleteEmployeeByEmpNumber(Integer empNumber) {
        Query query = getSession().createQuery("delete from Employee where emp_number = :empNumber");
        query.setParameter("empNumber", empNumber);
        query.executeUpdate();
    }
 
    @SuppressWarnings("unchecked")
    public List<Employee> findAllEmployees() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("lastName"));
        return (List<Employee>) criteria.list();
    }
    
    public List<Employee> searchEmployees(String term) {
        Criteria criteria = createEntityCriteria();
        if (term.length() > 0) {
            Criterion c1 = Restrictions.like("firstName", term, MatchMode.ANYWHERE);
            Criterion c2 = Restrictions.like("middleName", term, MatchMode.ANYWHERE);
            Criterion c3 = Restrictions.like("lastName", term, MatchMode.ANYWHERE);
            Disjunction or = Restrictions.disjunction();
            or.add(c1);
            or.add(c2);
            or.add(c3);
            
            if (term.matches("^-?\\d+$")) { // a number
                Criterion c4 = Restrictions.eq("id", Integer.parseInt(term));
                or.add(c4); 
            }
            
            criteria.add(or);
        }
        criteria.addOrder(Order.asc("lastName"));
        return (List<Employee>) criteria.list();        
    }
 
    public Employee findEmployeeByEmpNumber(Integer empNumber) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", empNumber));
        return (Employee) criteria.uniqueResult();
    }
    
    public Employee findEmployeeByEmail(String email) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (Employee) criteria.uniqueResult();
    }
}
