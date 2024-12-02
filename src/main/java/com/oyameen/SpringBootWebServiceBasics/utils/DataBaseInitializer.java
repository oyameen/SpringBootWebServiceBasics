package com.oyameen.SpringBootWebServiceBasics.utils;


import com.oyameen.SpringBootWebServiceBasics.model.*;
import com.oyameen.SpringBootWebServiceBasics.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;


@Component
public class DataBaseInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LaptopRepository laptopRepository;
    @Autowired
    private MobileRepository mobileRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void run(String... args) throws Exception {

        Employee employee = new Employee();
        employee.setName("Employee 1");
        employee.setJobTitle("Software Developer");
        employeeRepository.save(employee);
        ////

        Vehicle vehicle = new TwoWheelVehicle(24);
        vehicle.setModel("BMW");
        vehicle.setLicenceNumber("XYZ");
        vehicle.setEmployee(employee);
        vehicleRepository.save(vehicle);
        ////
        Laptop laptop1 = new Laptop();
        laptop1.setBrand("DELL");
        laptop1.setRamSize(16);
        laptop1.setOsType(LaptopOSType.WINDOWS);
        laptop1.setEmployee(employee);
        laptopRepository.save(laptop1);

        Laptop laptop2 = new Laptop();
        laptop2.setBrand("HP");
        laptop2.setRamSize(32);
        laptop2.setOsType(LaptopOSType.LINUX);
        laptop2.setEmployee(employee);
        laptopRepository.save(laptop2);
        ////
        Mobile mobile1 = new Mobile();
        mobile1.setBrand("SAMSUNG");
        mobile1.setCameraNumber(4);
        mobile1.setRamSize(12);
        mobile1.setOsType(MobileOSType.ANDROID);
        mobile1.setEmployee(employee);
        mobileRepository.save(mobile1);

        Mobile mobile2 = new Mobile();
        mobile2.setBrand("IPHONE");
        mobile2.setCameraNumber(3);
        mobile2.setRamSize(6);
        mobile2.setOsType(MobileOSType.IOS);
        mobile2.setEmployee(employee);
        mobileRepository.save(mobile2);
        ////
        Project project1 = new Project();
        project1.setName("XYZ1 Project");
        project1.setStartDate(Date.from(Instant.now()));
        project1.setEndDate(Date.from(Instant.now().plusSeconds(2592000)));
        project1.getEmployees().add(employee);
        projectRepository.save(project1);

        Project project2 = new Project();
        project2.setName("XYZ2 Project");
        project2.setStartDate(Date.from(Instant.now()));
        project2.setEndDate(Date.from(Instant.now().plusSeconds(2592000)));
        project2.getEmployees().add(employee);
        projectRepository.save(project2);
        ////

        employee.setVehicle(vehicle);
        employee.getLaptops().addAll(Arrays.asList(laptop1, laptop2));
        employee.getMobiles().addAll(Arrays.asList(mobile1, mobile2));
        employee.getProjects().addAll(Arrays.asList(project1, project2));
        employeeRepository.save(employee);

    }
}
