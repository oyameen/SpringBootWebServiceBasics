package com.oyameen.SpringBootWebServiceBasics.repository;

import com.oyameen.SpringBootWebServiceBasics.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
