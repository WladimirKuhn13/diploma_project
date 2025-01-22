package com.example.trackingV2.enums;

public enum StatusGasMixture {

    CREATED("Создано"),
    REFUELED("Заправлено"),
    TRANSFERRED_FOR_ANALYSIS("Передано на анализ"),
    ACCEPTED_ON_ANALYSIS("Принято на анализе"),
    ANALYSIS("Анализ"),
    TRANSFERRED_TO_STOCK("Передано на склад"),
    ACCEPTED_TO_STOCK("Принято на складе");




    private String status;

    private StatusGasMixture(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
