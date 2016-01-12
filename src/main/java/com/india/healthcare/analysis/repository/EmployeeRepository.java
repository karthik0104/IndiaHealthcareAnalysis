package com.india.healthcare.analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.healthcare.analysis.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
