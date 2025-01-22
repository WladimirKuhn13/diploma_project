package com.example.trackingV2.service;

import com.example.trackingV2.model.GasOrder;
import com.example.trackingV2.model.HistoryOfChanges;
import com.example.trackingV2.repository.HistoryOfChangesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryOfChangesService {

    @Autowired
    private HistoryOfChangesRepository historyOfChangesRepository;

    public List<HistoryOfChanges> getAllHistoryOfChanges() {
        return historyOfChangesRepository.findAll();
    }

    public List<HistoryOfChanges> findByFilter(String field, String value) {

        List<HistoryOfChanges> historyFiltered = new ArrayList<>();

        switch (field) {
            case "gasOrderId":
                historyFiltered.addAll(historyOfChangesRepository.findHistoryOfChangesByGasOrderId(Long.valueOf(value)));
            case "status":
                historyFiltered.addAll(historyOfChangesRepository.findHistoryOfChangesByStatus(value));
        }

        return historyFiltered;
    }
}
