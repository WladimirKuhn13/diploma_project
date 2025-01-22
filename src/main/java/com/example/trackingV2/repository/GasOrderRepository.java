package com.example.trackingV2.repository;

import com.example.trackingV2.model.GasOrder;
import com.example.trackingV2.model.GasOrderArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GasOrderRepository extends JpaRepository<GasOrder, Long> {
    List<GasOrder> findAllByClient(String client);
    List<GasOrder> findAllByTypeOrder(String typeOrder);
    List<GasOrder> findGasOrderByManagerId(Long managerId);
    List<GasOrder> findGasOrderByClient(String client);
    List<GasOrder> findGasOrderByCreationDate(LocalDate creationDate);
    List<GasOrder> findGasOrderByDeadline(LocalDate deadline);
    List<GasOrder> findGasOrderByGasMixture(String gasMixture);
    List<GasOrder> findGasOrderByStatus(String status);
    List<GasOrder> findGasOrderByTypeOrder(String typeOrder);
    List<GasOrder> findGasOrderByResponsibleUserId(Long responsibleUserId);
}
