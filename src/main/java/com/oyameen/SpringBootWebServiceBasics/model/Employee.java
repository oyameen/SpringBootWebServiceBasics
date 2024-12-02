package com.oyameen.SpringBootWebServiceBasics.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String jobTitle;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "employee")
    private List<Laptop> laptops = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Mobile> mobiles = new ArrayList<>();

    @ManyToMany(mappedBy = "employees")
    private List<Project> projects = new ArrayList<>();

}
