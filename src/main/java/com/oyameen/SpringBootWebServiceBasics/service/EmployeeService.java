package com.oyameen.SpringBootWebServiceBasics.service;

import com.oyameen.SpringBootWebServiceBasics.model.Employee;
import com.oyameen.SpringBootWebServiceBasics.model.EmployeeRequestDto;

import java.util.List;


public interface EmployeeService {

    Employee saveEmployee(EmployeeRequestDto employee);

    Employee updateEmployee(EmployeeRequestDto employee);

    List<Employee> getEmployees();

    Employee getEmployee(Long id);

    void deleteEmployee(Long id);
}
