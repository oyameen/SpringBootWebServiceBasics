package com.oyameen.SpringBootWebServiceBasics.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oyameen.SpringBootWebServiceBasics.model.EmployeeRequestDto;
import com.oyameen.SpringBootWebServiceBasics.service.EmployeeService;
import com.oyameen.ws.employee_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class EmployeeEndpoint {
    private static final String NAMESPACE_URI = "http://oyameen.com/ws/employee-web-service";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest employeeRequest) {
        if (employeeRequest != null && employeeRequest.getEmployeeRequestDto() != null) {
            EmployeeRequestDto employeeRequestDto = objectMapper.convertValue(employeeRequest.getEmployeeRequestDto(), EmployeeRequestDto.class);
            employeeRequestDto.setId(null);
            AddEmployeeResponse employeeResponse = new AddEmployeeResponse();
            employeeResponse.setEmployee(objectMapper.convertValue(employeeService.saveEmployee(employeeRequestDto), Employee.class));
            return employeeResponse;
        }
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest employeeRequest) {
        if (employeeRequest != null && employeeRequest.getEmployeeRequestDto() != null) {
            EmployeeRequestDto employeeRequestDto = objectMapper.convertValue(employeeRequest.getEmployeeRequestDto(), EmployeeRequestDto.class);
            UpdateEmployeeResponse employeeResponse = new UpdateEmployeeResponse();
            employeeResponse.setEmployee(objectMapper.convertValue(employeeService.updateEmployee(employeeRequestDto), Employee.class));
            return employeeResponse;
        }
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeeRequest")
    @ResponsePayload
    public GetAllEmployeeResponse getAllEmployee(@RequestPayload GetAllEmployeeRequest getAllEmployeeRequest) {

        GetAllEmployeeResponse getAllEmployeeResponse = new GetAllEmployeeResponse();
        List<com.oyameen.SpringBootWebServiceBasics.model.Employee> employeeList = employeeService.getEmployees();
        List<Employee> employeeListToReturn = new ArrayList<>();
        employeeList.forEach(employee -> employeeListToReturn.add(objectMapper.convertValue(employee, Employee.class)));
        getAllEmployeeResponse.getEmployees().addAll(employeeListToReturn);
        return getAllEmployeeResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest getEmployeeRequest) {

        if (getEmployeeRequest != null) {
            GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
            getEmployeeResponse.setEmployees(objectMapper.convertValue(employeeService.getEmployee(getEmployeeRequest.getId()), Employee.class));
            return getEmployeeResponse;
        }
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest deleteEmployeeRequest) {
        if (deleteEmployeeRequest != null) {
            long employeeId = deleteEmployeeRequest.getId();
            DeleteEmployeeResponse deleteEmployeeResponse = new DeleteEmployeeResponse();
            employeeService.deleteEmployee(employeeId);
            deleteEmployeeResponse.setStatus("Employee with id = [ " + employeeId + " ] deleted successfully.");
            return deleteEmployeeResponse;
        }
        return null;
    }
}
