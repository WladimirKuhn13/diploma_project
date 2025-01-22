package com.example.trackingV2.service;

import com.example.trackingV2.enums.StatusAdditionalServices;
import com.example.trackingV2.enums.TypeOfAdditionalServices;
import com.example.trackingV2.model.GasOrder;
import com.example.trackingV2.model.OrderForAdditionalServices;
import com.example.trackingV2.repository.OrderForAdditionalServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderForAdditionalServicesService {

    private final OrderForAdditionalServicesRepository orderForAdditionalServicesRepository;

    @Autowired
    public OrderForAdditionalServicesService(OrderForAdditionalServicesRepository orderForAdditionalServicesRepository) {
        this.orderForAdditionalServicesRepository = orderForAdditionalServicesRepository;
    }

    public List<OrderForAdditionalServices> findAll() {
        return orderForAdditionalServicesRepository.findAll();
    }

    public OrderForAdditionalServices findById(Long id) {
        return orderForAdditionalServicesRepository.findById(id).orElse(null);
    }
    public OrderForAdditionalServices saveOrder(OrderForAdditionalServices orderForAdditionalServices) {
        return orderForAdditionalServicesRepository.save(orderForAdditionalServices);
    }

    public OrderForAdditionalServices createOrder(Long managerId,
                                                  Long stockEmployeeId,
                                                  Long orderId,
                                                  String typeService,
                                                  Integer numberOfServices) {
        String status = StatusAdditionalServices.ORDER_CREATED.getStatus();
        OrderForAdditionalServices orderForAdditionalServices = new OrderForAdditionalServices(managerId, stockEmployeeId, orderId, typeService, numberOfServices, status);
        saveOrder(orderForAdditionalServices);
        return orderForAdditionalServices;
    }

    public void changeStatus(OrderForAdditionalServices order, String newStatus) {
        order.setOrderStatus(newStatus);
        saveOrder(order);
    }


    public List<String> getAllTypeOfAdditionalServicesAsStrings() {
        List<String> allTypeOfAdditionalServicesAsStrings = new ArrayList<>();
        for (TypeOfAdditionalServices type : TypeOfAdditionalServices.values()) {
            allTypeOfAdditionalServicesAsStrings.add(type.getStatus());
        }
        return allTypeOfAdditionalServicesAsStrings;
    }
}
