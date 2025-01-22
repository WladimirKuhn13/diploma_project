package com.example.trackingV2.enums;

public enum TypeGasOrder {
    CUSTOMER_CYLINDERS("Баллоны клиента"),
    NEW_CYLINDERS("Новые баллоны"),
    CIRCULATING_CYLINDERS("Оборотные баллоны");

    private String typeGasOrder;

    private TypeGasOrder(String typeGasOrder) {
        this.typeGasOrder = typeGasOrder;
    }

    public String getTypeGasOrder() {
        return typeGasOrder;
    }
}
