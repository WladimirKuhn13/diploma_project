package com.example.trackingV2.utils.GasOrderFilter;

import com.example.trackingV2.model.GasOrder;
import java.util.List;

public interface GasOrderFilter {
    List<GasOrder> filterGasOrder(List<GasOrder> gasOrders);
}
