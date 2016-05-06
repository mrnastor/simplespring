/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.joda.time.LocalDate;



@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EMP_NUMBER", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)    
    private Integer id;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    
    @Size(min = 0, max = 45)
    @Column(name = "MIDDLE_NAME", nullable = true)
    private String middleName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    
    @Size(min = 0, max = 45)
    @Column(name = "EXTENSION", nullable = true)
    private String extension;
    
    @Size(min = 0, max = 256)
    @Column(name = "ADDRESS", nullable = true)
    private String address;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @Transient
    private String oldPassword;

    @Transient
    private String newPassword;

    @Transient
    private String confirmPassword;
    
    @Digits(integer = 11, fraction = 0)
    @Column(name = "NO_OF_LEAVES", nullable = true)
    private Integer numLeaves;
    
    @NotNull
    @Email
    @Basic(optional = false)    
    @Size(min = 1, max = 45)
    @Column(name = "EMAIL", unique=true, nullable = false)
    private String email;
    
    @Size(min = 0, max = 45)
    @Column(name = "GENDER", nullable = true)
    private String gender;
    
    @Size(min = 0, max = 45)
    @Column(name = "PHONE_NUMBER", nullable = true)
    private String phoneNumber;
    
    @Size(min = 0, max = 45)
    @Column(name = "POSITION", nullable = true)
    private String position;
    
    @JoinColumn(name = "department_dept_number", referencedColumnName = "dept_number")
    @ManyToOne(optional = false)
    private Department deptNumber;
    
//    @Digits(integer = 11, fraction = 0)
//    @Column(name = "DEPARTMENT_DEPT_NUMBER", nullable = false)
//    private int deptNumber;
    
    @Past
    @Column(name = "BIRTHDATE", nullable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate birthDate;
    
    
    
    public Employee() {
    	this.id = (Integer) 0;
    }

    public Employee(Integer empNumber) {
        this.id = empNumber;
    }

    public Employee(Integer empNumber, String firstName, String lastName, String password, String email) {
        this.id = empNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return firstName + ' ' + middleName + ' ' + lastName + ' ';
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getNumLeaves() {
        return numLeaves;
    }

    public void setNumLeaves(Integer numLeaves) {
        this.numLeaves = numLeaves;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public Department getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(Department deptNumber) {
        this.deptNumber = deptNumber;
    }
    
//    public int getDeptNumber() {
//        return deptNumber;
//    }
//
//    public void setDeptNumber(int deptNumber) {
//        this.deptNumber = deptNumber;
//    }

//    public Date getBirthdate() {
//        return birthdate;
//    }
//
//    public void setBirthdate(Date birthdate) {
//        this.birthdate = birthdate;
//    }
    public LocalDate getBirthDate() {
            return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id != null ? id : 0);
        result = prime * result + getFullName().hashCode();
        return result;
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
                return true;
        if (obj == null)
                return false;
        if (!(obj instanceof Employee))
                return false;
        Employee other = (Employee) obj;
        if (id != other.id)
                return false;
        if (!getFullName().equals(other.getFullName()))
                return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + firstName + " " + middleName + " " + lastName + "]";
    }

    public Integer getBirthYear() {
        if (this.birthDate != null) {
            return this.birthDate.getYear();
        }
        return null;
    }

    public Integer getBirthMonth() {
//        Calendar cal = Calendar.getInstance();
        if (this.birthDate != null) {
//            cal.setTime(this.birthDate);
//            return cal.get(Calendar.MONTH) + 1;
            return this.birthDate.getMonthOfYear();
        }
        return null;
        
    }

    public Integer getBirthDay() {
        if (this.birthDate != null) {
            return this.birthDate.getDayOfMonth();
        }
        return null;
    }
    
    
    
}
