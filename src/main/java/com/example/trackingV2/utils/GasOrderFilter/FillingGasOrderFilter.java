package com.example.trackingV2.utils.GasOrderFilter;

import com.example.trackingV2.enums.StatusGasOrder;
import com.example.trackingV2.model.GasOrder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FillingGasOrderFilter implements GasOrderFilter {
    private static final Set<String> STATUSES = Set.of(StatusGasOrder.TRANSFERRED_TO_FILLING_AREA.getStatus(), StatusGasOrder.ACCEPTED_AT_FILLING_AREA.getStatus());

    @Override
    public List<GasOrder> filterGasOrder(List<GasOrder> gasOrders) {
        return gasOrders.stream()
                .filter(order -> STATUSES.contains(order.getStatus()))
                .collect(Collectors.toList());
    }
}
