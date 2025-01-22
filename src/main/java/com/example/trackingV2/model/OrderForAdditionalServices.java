package com.example.trackingV2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "additional_services")
public class OrderForAdditionalServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "manager_id")
    private Long managerId;
    @Column(name = "stock_employee_id")
    private Long stockEmployeeId;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "type_service")
    private String typeService;
    @Column(name = "number_of_services")
    @NotNull(message = "Поле обязательно для заполнения")
    private Integer numberOfServices;
    @Column(name = "order_status")
    private String orderStatus;

    //region constructors

    public OrderForAdditionalServices(Long managerId, Long stockEmployeeId, Long orderId, String typeService, Integer numberOfServices, String orderStatus) {
        this.managerId = managerId;
        this.stockEmployeeId = stockEmployeeId;
        this.orderId = orderId;
        this.typeService = typeService;
        this.numberOfServices = numberOfServices;
        this.orderStatus = orderStatus;
    }

    public OrderForAdditionalServices() {
    }

    //endregion

    //region getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getStockEmployeeId() {
        return stockEmployeeId;
    }

    public void setStockEmployeeId(Long stockEmployeeId) {
        this.stockEmployeeId = stockEmployeeId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public Integer getNumberOfServices() {
        return numberOfServices;
    }

    public void setNumberOfServices(Integer numberOfServices) {
        this.numberOfServices = numberOfServices;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    //endregion

}
