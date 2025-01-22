package com.example.trackingV2.enums;

public enum StatusGasOrder {
    ORDER_CREATED("Заявка создана"),
    CHECKING_STOCK_AVAILABILITY("Проверка наличия на складе"),
    TRANSFERRED_TO_FILLING_AREA("Передано на участок заправки"),
    ACCEPTED_AT_FILLING_AREA("Принято на учатске заправки"),
    TRANSFERRED_TO_LABORATORY("Передано в лабораторию"),
    ACCEPTED_IN_LAB("Принято в лаборатории"),
    TRANSFERRED_TO_STOCK("Передано на склад"),
    ACCEPTED_IN_STOCK("Принято на складе"),
    SENT_TO_CLIENT("Отправлено клиенту"),
    TRANSFERRED_TO_PREPARATION_AREA("Передано на участок подготовки"),
    ACCEPTED_AT_PREPARATION_AREA("Принято на участке подготовки"),
    TRANSFERRED_TO_MANAGER("Передано менеджеру"),
    ACCEPTED_BY_MANAGER("Принято менеджером"),
    WAITING_TO_PAY_FOR_ADDITIONAL_SERVICES("Ожидание оплаты доп.услуг"),
    ACCEPTED_AT_STOCK_FOR_SHIPMENT("Принято на складе для отгрузки"),
    TRANSFERRED_FOR_CYLINDER_ACCEPTANCE("Передано на приемку баллонов"),
    ACCEPTED_AT_CYLINDER_ACCEPTANCE("Принято на приемке баллонов"),
    ADDITIONAL_SERVICES_PAID("Дополнительные услуги оплачены"),
    TRANSFERRED_TO_STOCK_FOR_SHIPMENT("Передано на склад для отгрузки");

    private String status;

    private StatusGasOrder(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
