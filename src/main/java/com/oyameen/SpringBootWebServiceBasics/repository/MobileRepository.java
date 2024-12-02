package com.oyameen.SpringBootWebServiceBasics.repository;

import com.oyameen.SpringBootWebServiceBasics.model.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {
}
