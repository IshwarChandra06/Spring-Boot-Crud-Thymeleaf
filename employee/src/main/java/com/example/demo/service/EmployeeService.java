package com.example.demo.service;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Employee;

public interface EmployeeService 
{
	
	List<Employee> getAllEmployees();
	
	void saveEmployee(Employee employee);
	
	 Employee getEmployeeById(long id);
	 
	 void deleteEmployeeById(long id);
	 public long getEmployeeCount();
	 public Page<Employee> getPaginatedEmployee(final int pageNumber, final int pageSize);

	
	
}