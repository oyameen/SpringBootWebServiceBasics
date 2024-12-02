package com.oyameen.SpringBootWebServiceBasics.service.impl;

import com.oyameen.SpringBootWebServiceBasics.exception.EmployeeManagementException;
import com.oyameen.SpringBootWebServiceBasics.model.Laptop;
import com.oyameen.SpringBootWebServiceBasics.repository.LaptopRepository;
import com.oyameen.SpringBootWebServiceBasics.service.LaptopService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public Laptop updateLaptop(Laptop laptop) {
        Laptop laptopResult = laptopRepository.findById(laptop.getId()).orElse(null);
        if (laptopResult != null) {
            laptopResult.setBrand(laptop.getBrand());
            laptopResult.setRamSize(laptop.getRamSize());
            laptopResult.setOsType(laptop.getOsType());
            return laptopRepository.save(laptopResult);
        }
        throw new EmployeeManagementException("Cannot update this Laptop, because it doesn't exist.");
    }

    @Override
    public List<Laptop> getLaptops() {
        return laptopRepository.findAll();
    }

    @Override
    public Laptop getLaptop(Long id) {
        return laptopRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteLaptop(Long id) {
        if (laptopRepository.findById(id).isEmpty()) {
            throw new EmployeeManagementException("Cannot delete this Laptop, because it doesn't exist.");
        }
        laptopRepository.deleteById(id);
    }
}
