package com.example.demo.service;
import java.util.List;

import com.example.demo.model.Department;

public interface DepartmentService
{
 List<Department> getAllDepartment();
 
 void saveDepartment(Department dept);
 
 Department getDepartmentById(long id);
 
 void deleteDepartmentById(long id);
 
}
