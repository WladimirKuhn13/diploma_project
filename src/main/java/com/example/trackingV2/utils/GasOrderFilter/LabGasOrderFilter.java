package com.example.trackingV2.utils.GasOrderFilter;

import com.example.trackingV2.enums.StatusGasOrder;
import com.example.trackingV2.model.GasOrder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LabGasOrderFilter implements GasOrderFilter {
    private static final Set<String> STATUSES = Set.of(StatusGasOrder.ACCEPTED_IN_LAB.getStatus(), StatusGasOrder.TRANSFERRED_TO_LABORATORY.getStatus());

    @Override
    public List<GasOrder> filterGasOrder(List<GasOrder> gasOrders) {
        return gasOrders.stream()
                .filter(order -> STATUSES.contains(order.getStatus()))
                .collect(Collectors.toList());
    }
}
