package com.example.trackingV2.service;

import com.example.trackingV2.model.GasOrderArchive;
import com.example.trackingV2.repository.GasOrderArchiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class GasOrderArchiveService {

    private final GasOrderArchiveRepository gasOrderArchiveRepository;

    public GasOrderArchiveService(GasOrderArchiveRepository gasOrderArchiveRepository) {
        this.gasOrderArchiveRepository = gasOrderArchiveRepository;
    }


    public List<GasOrderArchive> findAll() {
        return (List<GasOrderArchive>) gasOrderArchiveRepository.findAll();
    }

    public GasOrderArchive findById(Long id) {
        return gasOrderArchiveRepository.findById(id).orElse(null);
    }

    public GasOrderArchive save(GasOrderArchive gasOrderArchive) {
        return gasOrderArchiveRepository.save(gasOrderArchive);
    }

    public List<GasOrderArchive> findByFilter(String field, String value) {
        LocalDate date = null;
        List<GasOrderArchive> gasOrdersArchiveFiltered = new ArrayList<>();
        if (field.equals("creationDate") || field.equals("deadline")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(value, formatter);
        }
        switch (field) {
            case "managerId":
                gasOrdersArchiveFiltered.addAll(gasOrderArchiveRepository.findGasOrderArchivesByManagerId(Long.valueOf(value)));
            case "client":
                gasOrdersArchiveFiltered.addAll(gasOrderArchiveRepository.findGasOrderArchivesByClient(value));
            case "deadline":
            case "creationDate":
                gasOrdersArchiveFiltered.addAll(gasOrderArchiveRepository.findGasOrderArchivesByCreationDate(date));
            case "gasMixture":
                gasOrdersArchiveFiltered.addAll(gasOrderArchiveRepository.findGasOrderArchivesByGasMixture(value));
            case "status":
                gasOrdersArchiveFiltered.addAll(gasOrderArchiveRepository.findGasOrderArchivesByStatus(value));
            case "typeOrder":
                gasOrdersArchiveFiltered.addAll(gasOrderArchiveRepository.findGasOrderArchivesByTypeOrder(value));
            case "responsibleUserId":
                gasOrdersArchiveFiltered.addAll(gasOrderArchiveRepository.findGasOrderArchivesByResponsibleUserId(Long.valueOf(value)));
        }
        return gasOrdersArchiveFiltered;
    }
}
