package com.example.trackingV2.repository;

import com.example.trackingV2.model.GasOrderArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface GasOrderArchiveRepository extends JpaRepository<GasOrderArchive, Long> {
    List<GasOrderArchive> findGasOrderArchivesByManagerId(Long managerId);
    List<GasOrderArchive> findGasOrderArchivesByClient(String client);
    List<GasOrderArchive> findGasOrderArchivesByCreationDate(LocalDate creationDate);
    List<GasOrderArchive> findGasOrderArchivesByDeadline(LocalDate deadline);
    List<GasOrderArchive> findGasOrderArchivesByGasMixture(String gasMixture);
    List<GasOrderArchive> findGasOrderArchivesByStatus(String status);
    List<GasOrderArchive> findGasOrderArchivesByTypeOrder(String typeOrder);
    List<GasOrderArchive> findGasOrderArchivesByResponsibleUserId(Long responsibleUserId);


}
