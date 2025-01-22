package com.example.trackingV2.utils.GasOrderFilter;

import com.example.trackingV2.model.GasOrder;

import java.util.List;

public class GasOrderFilterFactory {
    public static GasOrderFilter getGasOrderFilter(String role) {
        switch(role) {
            case"ROLE_STOCK_HEAD":
                return new StockGasOrderFilter();
            case"ROLE_STOCK":
                return new StockGasOrderFilter();
            case"ROLE_FILLING":
                return new FillingGasOrderFilter();
            case"ROLE_FILLING_HEAD":
                return new FillingGasOrderFilter();
            case"ROLE_LAB_HEAD":
                return new LabGasOrderFilter();
            case"ROLE_LAB":
                return new LabGasOrderFilter();
            case"ROLE_PREPARATION_HEAD":
                return new PreparationOrderFilter();
            case"ROLE_PREPARATION":
                return new PreparationOrderFilter();
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
