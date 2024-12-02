package com.oyameen.SpringBootWebServiceBasics.repository;

import com.oyameen.SpringBootWebServiceBasics.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
