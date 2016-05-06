package com.javahris.springmvc.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		
        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery(
         "select email as username, password as password, true as enabled from employee where email=?")
        .authoritiesByUsernameQuery(
         "SELECT e.email, " + 
         "CASE d.dept_name " +  
         	"WHEN 'Software Development' THEN 'ROLE_USER' " +  
         	"WHEN 'Hardware Development' THEN 'ROLE_USER' " +  
         	"WHEN 'Administration' THEN 'ROLE_ADMIN' " +  
         	"END AS role " +  
         "FROM employee e, department d " +  
         "WHERE e.department_dept_number = d.dept_number AND e.email = ?");
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
      http.authorizeRequests()
        .antMatchers("/", "/list").access("hasRole('USER') or hasRole('ADMIN')")
        .antMatchers("/home").access("hasRole('USER') or hasRole('ADMIN')")
        .antMatchers("/changepassword").access("hasRole('USER') or hasRole('ADMIN')")
        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .antMatchers("/new").access("hasRole('ADMIN')")
        .antMatchers("/edit-{empNumber}-employee").access("hasRole('USER') or hasRole('ADMIN')")
        .antMatchers("/delete-{empNumber}-employee").access("hasRole('USER') or hasRole('ADMIN')")
        .and().formLogin().loginPage("/login")
        .usernameParameter("ssoId").passwordParameter("password")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}