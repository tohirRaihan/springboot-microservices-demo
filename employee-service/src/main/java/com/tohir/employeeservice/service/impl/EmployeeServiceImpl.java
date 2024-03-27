package com.tohir.employeeservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tohir.employeeservice.dto.APIResponseDto;
import com.tohir.employeeservice.dto.DepartmentDto;
import com.tohir.employeeservice.dto.EmployeeDto;
import com.tohir.employeeservice.entity.Employee;
import com.tohir.employeeservice.mapper.EmployeeMapper;
import com.tohir.employeeservice.repository.EmployeeRepository;
import com.tohir.employeeservice.service.APIClient;
import com.tohir.employeeservice.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    // private final WebClient webClient;
    private final APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee saveDEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(saveDEmployee);

        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId).get();

        // DepartmentDto departmentDto = webClient.get()
        //         .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
        //         .retrieve()
        //         .bodyToMono(DepartmentDto.class)
        //         .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

}
