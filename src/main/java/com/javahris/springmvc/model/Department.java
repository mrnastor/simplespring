/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jem
 */
@Entity
@Table(name = "department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")})
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dept_number")
    private Integer deptNumber;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dept_name")
    private String deptName;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentDeptNumber")
//    private List<Employee> employeeList;

    public Department() {
    }

    public Department(Integer deptNumber) {
        this.deptNumber = deptNumber;
    }

    public Department(Integer deptNumber, String deptName) {
        this.deptNumber = deptNumber;
        this.deptName = deptName;
    }

    public Integer getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(Integer deptNumber) {
        this.deptNumber = deptNumber;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

//    @XmlTransient
//    public List<Employee> getEmployeeList() {
//        return employeeList;
//    }
//
//    public void setEmployeeList(List<Employee> employeeList) {
//        this.employeeList = employeeList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptNumber != null ? deptNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.deptNumber == null && other.deptNumber != null) || (this.deptNumber != null && !this.deptNumber.equals(other.deptNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.javahris.springmvc.model.Department[ deptNumber=" + deptNumber + " ]";
    }
    
}
