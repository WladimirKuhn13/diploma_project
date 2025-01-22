package com.example.trackingV2.enums;

public enum TypeOfAdditionalServices {
    PAINTING_OF_CYLINDERS("Покраска баллонов"),
    APPLYING_STRIPES("Нанесение полос"),
    REPLACING_VALVE("Замена вентиля"),
    TVT("Термовакуумная обработка"),
    EXTENSION_OF_CYLINDER_CERTIFICATION("Продление аттестации баллонов");

    private String status;

    private TypeOfAdditionalServices(String status) {

        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
