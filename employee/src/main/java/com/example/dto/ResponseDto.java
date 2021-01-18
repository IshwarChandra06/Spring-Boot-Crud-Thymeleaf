package com.example.dto;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.model.Employee;

public class ResponseDto {

    private final List<Employee> employee;
    private final Map<String, Integer> page;

    public ResponseDto(final List<Employee> employee, final Map<String, Integer> page) {
        this.employee = new ArrayList<>(employee);
        this.page = new HashMap<>(page);
    }

    public static ResponseDto create(final List<Employee> employee, final Map<String, Integer> page) {
        return new ResponseDto(employee, page);
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public Map<String, Integer> getPage() {
        return page;
    }
}



