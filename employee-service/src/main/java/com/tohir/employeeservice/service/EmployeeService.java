package com.tohir.employeeservice.service;

import com.tohir.employeeservice.dto.APIResponseDto;
import com.tohir.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
