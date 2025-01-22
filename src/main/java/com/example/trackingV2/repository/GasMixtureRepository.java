package com.example.trackingV2.repository;

import com.example.trackingV2.model.GasMixture;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GasMixtureRepository extends JpaRepository<GasMixture, Long> {
    GasMixture findByNameGasMixtureAndGasСylinderVolumeAndNumberOfGasCylinderGreaterThanEqual(
            String nameGasMixture, Integer gasСylinderVolume, Integer numberOfGasCylinder);

    GasMixture findByNameGasMixture(String nameGasMixture);

    List<GasMixture> findAllByNameGasMixture(String nameGasMixture);

}
