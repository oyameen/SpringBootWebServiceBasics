package com.oyameen.SpringBootWebServiceBasics.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorModel {
    private long timeStamp;
    private int status;
    private String error;
    private String message;
}
