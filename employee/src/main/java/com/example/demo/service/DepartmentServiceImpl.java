package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAllByIsDeleteFalse();
    }

	
	@Override
    public void saveDepartment(Department dept) {
		dept.setDelete(false);
        this.departmentRepository.save(dept);
    }

    @Override
    public Department getDepartmentById(long id) {
    	 Optional < Department > optional = departmentRepository.findById(id);
        Department dept = null;
        if (optional.isPresent()) {
            dept = optional.get();
        }
        else 
        {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return dept;
    }
    
    
  @Override
 public void deleteDepartmentById(long id) 
 {
 Optional < Department > optional = departmentRepository.findById(id);
    Department dept= null;
    if (optional.isPresent())
    {
        dept = optional.get();
        dept.setDelete(true);
    }
    else 
    {
        throw new RuntimeException(" Employee not found for id :: " + id);
    }
    this.departmentRepository.save(dept);
}
    
}
