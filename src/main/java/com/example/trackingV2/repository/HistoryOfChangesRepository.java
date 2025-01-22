package com.example.trackingV2.repository;

import com.example.trackingV2.model.GasOrderArchive;
import com.example.trackingV2.model.HistoryOfChanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoryOfChangesRepository extends JpaRepository<HistoryOfChanges, Long> {
    List<HistoryOfChanges> findHistoryOfChangesByGasOrderId(Long gasOrderId);
    List<HistoryOfChanges> findHistoryOfChangesByStatus(String status);

}
