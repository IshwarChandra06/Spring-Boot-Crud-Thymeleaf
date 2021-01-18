package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

  
    
 
    @GetMapping("/department")
    public String viewHomePage(Model model)
    {
        model.addAttribute("Department", departmentService.getAllDepartment());
        return "index1";
    }
    @GetMapping("/department/new")
    public String showNewDepartmentForm(Model model)
    {
      
        Department dept = new Department();
        model.addAttribute("department", dept);
        return "new_department";
    }
    
    @PostMapping("/department/add")
    public String saveDepartment(@ModelAttribute("department") Department dept)
    {
       
        departmentService.saveDepartment(dept);
        return "redirect:/department";
    }
    
    
    @GetMapping("/department/update/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
     
     
     Department dept = departmentService.getDepartmentById(id);
     model.addAttribute("department",dept);
     return "update_department";
    }
    
    
    
    @GetMapping("/department/delete/{id}")
    public String deleteDepartment(@PathVariable (value = "id") long id) 
    {
     
     this.departmentService.deleteDepartmentById(id);
     return "redirect:/department";
    }
}
