package com.oyameen.SpringBootWebServiceBasics.controller;

import com.oyameen.SpringBootWebServiceBasics.model.FourWheelVehicle;
import com.oyameen.SpringBootWebServiceBasics.model.TwoWheelVehicle;
import com.oyameen.SpringBootWebServiceBasics.model.Vehicle;
import com.oyameen.SpringBootWebServiceBasics.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(value = "/vehicle/bike")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody TwoWheelVehicle vehicle) {
        return ResponseEntity.status(201).body(vehicleService.saveVehicle(vehicle));
    }

    @PostMapping(value = "/vehicle/car")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody FourWheelVehicle vehicle) {
        return ResponseEntity.status(201).body(vehicleService.saveVehicle(vehicle));
    }

    @PutMapping(value = "/vehicle/bike")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody TwoWheelVehicle vehicle) {
        Vehicle vehicleResult = vehicleService.updateVehicle(vehicle);
        if (vehicleResult != null)
            return ResponseEntity.ok(vehicleResult);
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/vehicle/car")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody FourWheelVehicle vehicle) {
        Vehicle vehicleResult = vehicleService.updateVehicle(vehicle);
        if (vehicleResult != null)
            return ResponseEntity.ok(vehicleResult);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/vehicle")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return ResponseEntity.ok(vehicleService.getVehicles());
    }

    @GetMapping(value = "/vehicle/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable("id") Long id) {
        Vehicle vehicle = vehicleService.getVehicle(id);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/vehicle/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
