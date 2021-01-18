package com.example.demo.service;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService 
{

    @Autowired
    private EmployeeRepository employeeRepository;

   
    public List < Employee > getAllEmployees() 
    {
        return employeeRepository.findAllByIsDeleteFalse();
    }
    
    
    @Override
    public void saveEmployee(Employee employee)
    {   employee.setDelete(false);
        this.employeeRepository.save(employee);
    }
    
    

    @Override
    public Employee getEmployeeById(long id) {
        Optional < Employee > optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }
    
    
    @Override
    public void deleteEmployeeById(long id) 
    {
    	Optional < Employee > optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
            employee.setDelete(true);
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        this.employeeRepository.save(employee);
    }
    
    public long getEmployeeCount() {
       
        return employeeRepository.count();
    }
 
    public Page <Employee> getPaginatedEmployee(final int pageNumber, final int pageSize) {
        
        final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,Sort.by("FirstName").ascending());
        return employeeRepository.findAll(pageable);
    }
    
    


}
