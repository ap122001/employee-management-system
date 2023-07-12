package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!

    //add a method to sort by last name
    //this method automatcially creates query for the given method remember this is not a default method , it is writen in a syntex in which we spring finds
    public List<Employee> findAllByOrderByLastNameAsc();
	
}
