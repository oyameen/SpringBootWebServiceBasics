package com.oyameen.SpringBootWebServiceBasics.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("Car")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class FourWheelVehicle extends Vehicle {
    private int weight;
}
