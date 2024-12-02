package com.oyameen.SpringBootWebServiceBasics.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://oyameen.com/ws/employee-web-service}001_EMPLOYEE_MGT_EXCEPTION")
public class EmployeeManagementException extends RuntimeException {
    public EmployeeManagementException(String message) {
        super(message);
    }
}
