package com.tohir.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tohir.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
