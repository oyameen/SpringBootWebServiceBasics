package com.oyameen.SpringBootWebServiceBasics.service;

import com.oyameen.SpringBootWebServiceBasics.model.Laptop;

import java.util.List;

public interface LaptopService {

    Laptop saveLaptop(Laptop laptop);

    Laptop updateLaptop(Laptop laptop);

    List<Laptop> getLaptops();

    Laptop getLaptop(Long id);

    void deleteLaptop(Long id);
}
