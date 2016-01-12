package com.india.healthcare.analysis.service;

import java.util.List;

import com.india.healthcare.analysis.domain.Employee;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getList();

}
