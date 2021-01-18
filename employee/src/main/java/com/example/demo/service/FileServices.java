package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.repository.EmployeeRepository;
import com.example.demo.Excel.*;
import com.example.demo.model.Employee;
@Service
public class FileServices
{
@Autowired
EmployeeRepository employeerepository;


// Store File Data to Database
public void store(MultipartFile file){
try {
List<Employee> listemployees = ImportExcel.parseExcelFile(file.getInputStream());
    // Save Customers to DataBase
    employeerepository.saveAll(listemployees);
        } catch (IOException e) {
        throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
}


public InputStream loadFile() 
{
	List<Employee> employee = (List<Employee>)employeerepository.findAll();
	return null;
}


}

