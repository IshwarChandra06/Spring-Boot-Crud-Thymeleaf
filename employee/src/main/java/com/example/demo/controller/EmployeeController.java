package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Excel.ExportExcel;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.FileServices;
import com.example.dto.ResponseDto;

@Controller

public class EmployeeController {

	private static final int DEFAULT_PAGE_NUMBER = 1;
	private static final int DEFAULT_PAGE_SIZE = 2;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private FileServices fileServices;

	@GetMapping("/employee")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());

		return "redirect:employee/paginated/" + DEFAULT_PAGE_NUMBER + "/" + DEFAULT_PAGE_SIZE;
	}

	@GetMapping("/menu")
	public String viewMenuPage(Model model) {
		return "menu";
	}

	@GetMapping("/employee/new")
	public String showNewEmployeeForm(Model model) {
		model.addAttribute("listDepartment", departmentService.getAllDepartment());
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";

	}

	@PostMapping("/employee/add")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, @Valid Employee emp, Errors errors,
			Model model) {

		if (errors.hasErrors()) {
			model.addAttribute("listDepartment", departmentService.getAllDepartment());
			return "new_employee";
		} else {
			model.addAttribute("message", "Add Successfully");
			employeeService.saveEmployee(employee);
			return "redirect:/employee";
		}

	}

	@GetMapping("/employee/update/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		model.addAttribute("listDepartment", departmentService.getAllDepartment());
		Employee employee = employeeService.getEmployeeById(id);

		model.addAttribute("employee", employee);
		return "update_employee";

	}

	@GetMapping("/employee/delete/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {

		this.employeeService.deleteEmployeeById(id);
		return "redirect:/employee";

	}

	@GetMapping("/employee/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Employee> listUsers = employeeService.getAllEmployees();

		ExportExcel excelExporter = new ExportExcel(listUsers);

		excelExporter.export(response);
	}

	@GetMapping("/employee/upload")
	public String index() {
		return "upload";
	}

	@PostMapping("/employee/import/excel")
	public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, Model model) {
		try {
			fileServices.store(file);
			model.addAttribute("message", "File uploaded successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
		}
		return "upload";
	}

	
	@GetMapping(value = "/employee/paginated/{page}/{page-size}")
	public String getPaginatedEmployee(@PathVariable(name = "page") final int pageNumber,
			@PathVariable(name = "page-size") final int pageSize, final Model model) {

		final Page<Employee> paginatedEmployee = employeeService.getPaginatedEmployee(pageNumber, pageSize);
		model.addAttribute("responseEntity", createResponseDto(paginatedEmployee, pageNumber));
		return "index";
	}

	
	private ResponseDto createResponseDto(final Page<Employee> employeePage, final int pageNumber) {
		final Map<String, Integer> page = new HashMap<>();
		page.put("currentPage", pageNumber);
		page.put("totalPages", employeePage.getTotalPages());
		page.put("totalElements", (int) employeePage.getTotalElements());
		return ResponseDto.create(employeePage.getContent(), page);
	}

}