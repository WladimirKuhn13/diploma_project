package com.example.trackingV2.model;

import com.example.trackingV2.repository.HistoryOfChangesRepository;
import com.example.trackingV2.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class GasOrderLoggingAspect {

    @Autowired
    private HistoryOfChangesRepository historyOfChangesRepository;

    @Autowired
    private UserService userService;

    @Before(value = "execution(* com.example.trackingV2.service.GasOrderService.changeStatus(..)) && args(gasOrder, newStatus)", argNames = "gasOrder,newStatus")
    public void logChangeStatus(GasOrder gasOrder, String newStatus) {

        Long userId = userService.getCurrentUser().getId();

        HistoryOfChanges log = new HistoryOfChanges();
        log.setGasOrderId(gasOrder.getId());
        log.setStatus(newStatus);
        log.setEmployeeId(userId);
        log.setDate(LocalDateTime.now());
        historyOfChangesRepository.save(log);

    }
}
