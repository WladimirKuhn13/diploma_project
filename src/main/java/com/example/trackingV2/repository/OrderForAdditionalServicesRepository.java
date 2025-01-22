package com.example.trackingV2.repository;

import com.example.trackingV2.model.OrderForAdditionalServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderForAdditionalServicesRepository extends JpaRepository<OrderForAdditionalServices, Long> {
}
