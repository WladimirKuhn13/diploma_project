package com.example.trackingV2.enums;

public enum StatusAdditionalServices {
    ORDER_CREATED("Заявка создана"),
    TRANSFERRED_TO_MANAGER("Передано менеджеру"),
    ACCEPTED_BY_MANAGER("Принято менеджером"),
    WAITING_TO_PAY_FOR_ADDITIONAL_SERVICES("Ожидание оплаты доп.услуг"),
    SERVICES_PAID("Услуги оплачены");

    private String status;

    private StatusAdditionalServices(String status) {

        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
