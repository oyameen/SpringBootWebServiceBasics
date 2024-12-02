package com.oyameen.SpringBootWebServiceBasics.service.impl;

import com.oyameen.SpringBootWebServiceBasics.exception.EmployeeManagementException;
import com.oyameen.SpringBootWebServiceBasics.model.FourWheelVehicle;
import com.oyameen.SpringBootWebServiceBasics.model.TwoWheelVehicle;
import com.oyameen.SpringBootWebServiceBasics.model.Vehicle;
import com.oyameen.SpringBootWebServiceBasics.repository.EmployeeRepository;
import com.oyameen.SpringBootWebServiceBasics.repository.VehicleRepository;
import com.oyameen.SpringBootWebServiceBasics.service.VehicleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        Vehicle vehicleResult = vehicleRepository.findById(vehicle.getId()).orElse(null);
        if (vehicleResult != null) {
            vehicleResult.setModel(vehicle.getModel());
            vehicleResult.setLicenceNumber(vehicle.getLicenceNumber());
            if (vehicle instanceof TwoWheelVehicle &&
                    vehicleResult instanceof TwoWheelVehicle &&
                    ((TwoWheelVehicle) vehicle).getSize() > 0)

                ((TwoWheelVehicle) vehicleResult).setSize(((TwoWheelVehicle) vehicle).getSize());

            else if (vehicle instanceof FourWheelVehicle &&
                    vehicleResult instanceof FourWheelVehicle &&
                    ((FourWheelVehicle) vehicle).getWeight() > 0)

                ((FourWheelVehicle) vehicleResult).setWeight(((FourWheelVehicle) vehicle).getWeight());

            return vehicleRepository.save(vehicleResult);
        }
        throw new EmployeeManagementException("Cannot update this Vehicle, because it doesn't exist.");
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = null;
        if ((vehicle = vehicleRepository.findById(id).orElse(null)) == null) {
            throw new EmployeeManagementException("Cannot delete this Vehicle, because it doesn't exist.");
        }
        if (vehicle.getEmployee() != null && vehicle.getEmployee().getId() != null) {
            employeeRepository.findById(vehicle.getEmployee().getId()).ifPresent(employee -> employee.setVehicle(null));
        }
        vehicleRepository.deleteById(id);
    }
}
