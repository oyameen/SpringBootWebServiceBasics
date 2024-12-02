package com.oyameen.SpringBootWebServiceBasics.service;

import com.oyameen.SpringBootWebServiceBasics.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    List<Vehicle> getVehicles();

    Vehicle getVehicle(Long id);

    void deleteVehicle(Long id);
}
