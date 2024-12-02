package com.oyameen.SpringBootWebServiceBasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.oyameen.ws.employee_web_service", "com.oyameen.SpringBootWebServiceBasics"})
public class SpringBootWebServiceBasicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebServiceBasicsApplication.class, args);
    }

}
