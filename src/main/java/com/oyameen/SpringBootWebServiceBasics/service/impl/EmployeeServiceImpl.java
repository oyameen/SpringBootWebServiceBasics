package com.oyameen.SpringBootWebServiceBasics.service.impl;

import com.oyameen.SpringBootWebServiceBasics.exception.EmployeeManagementException;
import com.oyameen.SpringBootWebServiceBasics.model.*;
import com.oyameen.SpringBootWebServiceBasics.repository.*;
import com.oyameen.SpringBootWebServiceBasics.service.EmployeeService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Employee saveEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDto.getName());
        employee.setJobTitle(employeeRequestDto.getJobTitle());
        List<Laptop> laptops = new ArrayList<>();
        List<Mobile> mobiles = new ArrayList<>();
        List<Project> projects = new ArrayList<>();
        Vehicle vehicle = preSaveValidation(employeeRequestDto, employee, laptops, mobiles, projects);

        employeeRepository.save(employee);
        vehicleRepository.save(vehicle);
        laptopRepository.saveAll(laptops);
        mobileRepository.saveAll(mobiles);
        projectRepository.saveAll(projects);

        employee.setVehicle(vehicle);
        employee.getLaptops().addAll(laptops);
        employee.getMobiles().addAll(mobiles);
        employee.getProjects().addAll(projects);

        return employeeRepository.save(employee);
    }

    @PrePersist
    private Vehicle preSaveValidation(EmployeeRequestDto employeeRequestDto, Employee employee,
                                      List<Laptop> laptops, List<Mobile> mobiles, List<Project> projects) {

        Vehicle vehicle = vehicleRepository.findById(employeeRequestDto.getVehicleId()).orElse(null);
        if (vehicle == null)
            throw new EmployeeManagementException("Cannot add the employee, because the vehicle with id = " + employeeRequestDto.getVehicleId() + ", doesn't exist.");
        else if (vehicle.getEmployee() != null) {
            throw new EmployeeManagementException("Cannot add the employee, because the vehicle was used by other employee.");
        }
        vehicle.setEmployee(employee);


        if (employeeRequestDto.getLaptopIds() == null)
            throw new EmployeeManagementException("LaptopIds cannot be null.");
        employeeRequestDto.getLaptopIds().forEach(id -> {
            Laptop laptop = laptopRepository.findById(id).orElse(null);
            if (laptop == null)
                throw new EmployeeManagementException("Cannot add the employee, because the laptop with id = " + id + ", doesn't exist.");
            else if (laptop.getEmployee() != null)
                throw new EmployeeManagementException("Cannot add the employee, because the laptop was used by other employee.");
            laptop.setEmployee(employee);
            laptops.add(laptop);

        });


        if (employeeRequestDto.getMobileIds() == null)
            throw new EmployeeManagementException("MobileIds cannot be null.");
        employeeRequestDto.getMobileIds().forEach(id -> {
            Mobile mobile = mobileRepository.findById(id).orElse(null);
            if (mobile == null)
                throw new EmployeeManagementException("Cannot add the employee, because the mobile with id = " + id + ", doesn't exist.");
            else if (mobile.getEmployee() != null)
                throw new EmployeeManagementException("Cannot add the employee, because the mobile was used by other employee.");
            mobile.setEmployee(employee);
            mobiles.add(mobile);
        });


        if (employeeRequestDto.getProjectIds() == null)
            throw new EmployeeManagementException("ProjectIds cannot be null.");
        employeeRequestDto.getProjectIds().forEach(id -> {
            Project project = projectRepository.findById(id).orElse(null);
            if (project == null)
                throw new EmployeeManagementException("Cannot add the employee, because the project with id = " + id + ", doesn't exist.");
            project.getEmployees().add(employee);
            projects.add(project);
        });


        return vehicle;
    }

    @Override
    public Employee updateEmployee(EmployeeRequestDto employeeRequestDto) {

        Employee employee = employeeRepository.findById(employeeRequestDto.getId()).orElse(null);
        if (employee == null)
            throw new EmployeeManagementException("Cannot update the employee with id  = " + employeeRequestDto.getId() + ",  because it doesn't exist.");

        employee.setName(employeeRequestDto.getName());
        employee.setJobTitle(employeeRequestDto.getJobTitle());
        List<Laptop> laptops = new ArrayList<>();
        List<Mobile> mobiles = new ArrayList<>();
        List<Project> projects = new ArrayList<>();
        Vehicle vehicle = preUpdateValidation(employeeRequestDto, employee, laptops, mobiles, projects);

        vehicleRepository.save(vehicle);
        laptopRepository.saveAll(laptops);
        mobileRepository.saveAll(mobiles);
        projectRepository.saveAll(projects);

        employee.setVehicle(vehicle);
        employee.getLaptops().clear();
        employee.getMobiles().clear();
        employee.getProjects().clear();
        employee.getLaptops().addAll(laptops);
        employee.getMobiles().addAll(mobiles);
        employee.getProjects().addAll(projects);

        return employeeRepository.save(employee);
    }

    @PreUpdate
    private Vehicle preUpdateValidation(EmployeeRequestDto employeeRequestDto, Employee employee,
                                        List<Laptop> laptops, List<Mobile> mobiles, List<Project> projects) {

        Vehicle vehicle = vehicleRepository.findById(employeeRequestDto.getVehicleId()).orElse(null);
        if (vehicle == null)
            throw new EmployeeManagementException("Cannot update the employee, because the vehicle with id = " + employeeRequestDto.getVehicleId() + ", doesn't exist.");
        else if (vehicle.getEmployee() != null && !Objects.equals(vehicle.getEmployee().getId(), employee.getId())) {
            throw new EmployeeManagementException("Cannot update the employee, because the vehicle was used by other employee.");
        }
        if (employee.getVehicle() != null && employee.getVehicle() != vehicle) {
            employee.getVehicle().setEmployee(null);
            vehicleRepository.save(employee.getVehicle());
        }
        vehicle.setEmployee(employee);


        if (employeeRequestDto.getLaptopIds() == null)
            throw new EmployeeManagementException("LaptopIds cannot be null.");
        employeeRequestDto.getLaptopIds().forEach(id -> {
            Laptop laptop = laptopRepository.findById(id).orElse(null);
            if (laptop == null)
                throw new EmployeeManagementException("Cannot update the employee, because the laptop with id = " + id + ", doesn't exist.");
            else if (laptop.getEmployee() != null && !Objects.equals(laptop.getEmployee().getId(), employee.getId()))
                throw new EmployeeManagementException("Cannot update the employee, because the laptop was used by other employee.");
            if (!employee.getLaptops().contains(laptop))
                laptop.setEmployee(employee);

            laptops.add(laptop);

        });
        employee.getLaptops().forEach(laptop -> {
            if (!laptops.contains(laptop)) {
                laptop.setEmployee(null);
                laptopRepository.save(laptop);
            }
        });


        if (employeeRequestDto.getMobileIds() == null)
            throw new EmployeeManagementException("MobileIds cannot be null.");
        employeeRequestDto.getMobileIds().forEach(id -> {
            Mobile mobile = mobileRepository.findById(id).orElse(null);
            if (mobile == null)
                throw new EmployeeManagementException("Cannot update the employee, because the mobile with id = " + id + ", doesn't exist.");
            else if (mobile.getEmployee() != null && !Objects.equals(mobile.getEmployee().getId(), employee.getId()))
                throw new EmployeeManagementException("Cannot update the employee, because the mobile was used by other employee.");
            if (!employee.getMobiles().contains(mobile))
                mobile.setEmployee(employee);
            mobiles.add(mobile);
        });
        employee.getMobiles().forEach(mobile -> {
            if (!mobiles.contains(mobile)) {
                mobile.setEmployee(null);
                mobileRepository.save(mobile);
            }
        });


        if (employeeRequestDto.getProjectIds() == null)
            throw new EmployeeManagementException("ProjectIds cannot be null.");
        employeeRequestDto.getProjectIds().forEach(id -> {
            Project project = projectRepository.findById(id).orElse(null);
            if (project == null)
                throw new EmployeeManagementException("Cannot update the employee, because the project with id = " + id + ", doesn't exist.");
            if (!employee.getProjects().contains(project))
                project.getEmployees().add(employee);
            projects.add(project);
        });
        employee.getProjects().forEach(project -> {
            if (!projects.contains(project)) {
                project.getEmployees().remove(employee);
                projectRepository.save(project);
            }
        });


        return vehicle;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = null;
        if ((employee = employeeRepository.findById(id).orElse(null)) == null) {
            throw new EmployeeManagementException("Cannot delete this Employee, because it doesn't exist.");
        }
        if (employee.getVehicle() != null)
            throw new EmployeeManagementException("Cannot delete this Employee, because it's vehicle still exist.");
        if (employee.getLaptops() != null && !employee.getLaptops().isEmpty())
            throw new EmployeeManagementException("Cannot delete this Employee, because it's laptops still exist.");
        if (employee.getMobiles() != null && !employee.getMobiles().isEmpty())
            throw new EmployeeManagementException("Cannot delete this Employee, because it's mobiles still exist.");
        if (employee.getProjects() != null && !employee.getProjects().isEmpty())
            throw new EmployeeManagementException("Cannot delete this Employee, because it's projects still exist.");
        employeeRepository.deleteById(id);
    }

}
