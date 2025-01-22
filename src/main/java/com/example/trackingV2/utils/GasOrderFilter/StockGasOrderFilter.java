package com.example.trackingV2.utils.GasOrderFilter;

import com.example.trackingV2.enums.StatusGasOrder;
import com.example.trackingV2.model.GasOrder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StockGasOrderFilter implements GasOrderFilter {

    private static final Set<String> STATUSES = Set.of(StatusGasOrder.TRANSFERRED_TO_STOCK.getStatus(),
            StatusGasOrder.ACCEPTED_IN_STOCK.getStatus(),
            StatusGasOrder.TRANSFERRED_TO_STOCK_FOR_SHIPMENT.getStatus(),
            StatusGasOrder.TRANSFERRED_FOR_CYLINDER_ACCEPTANCE.getStatus(),
            StatusGasOrder.ACCEPTED_AT_STOCK_FOR_SHIPMENT.getStatus(),
            StatusGasOrder.ACCEPTED_AT_CYLINDER_ACCEPTANCE.getStatus());

    @Override
    public List<GasOrder> filterGasOrder(List<GasOrder> gasOrders) {
        return gasOrders.stream()
                .filter(order -> STATUSES.contains(order.getStatus()))
                .collect(Collectors.toList());
    }
}
