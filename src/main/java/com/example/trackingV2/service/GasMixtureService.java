package com.example.trackingV2.service;

import com.example.trackingV2.enums.StatusGasMixture;
import com.example.trackingV2.model.GasMixture;
import com.example.trackingV2.repository.GasMixtureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GasMixtureService {


    private final GasMixtureRepository gasMixtureRepository;

    @Autowired
    public GasMixtureService(GasMixtureRepository gasMixtureRepository) {
        this.gasMixtureRepository = gasMixtureRepository;
    }

    public List<GasMixture> findAll() {
        return gasMixtureRepository.findAll();
    }

    public GasMixture findById(Long id) {
        return gasMixtureRepository.findById(id).orElse(null);
    }

    public GasMixture saveGasMixture(GasMixture gasMixture) {
        return gasMixtureRepository.save(gasMixture);
    }

    public void deleteById(Long id) {
        gasMixtureRepository.deleteById(id);
    }

    public GasMixture findByNameGasMixture(String nameGasMixture) {
        return gasMixtureRepository.findByNameGasMixture(nameGasMixture);
    }

    public List<GasMixture> findAllByNameGasMixture(String nameGasMixture) {
        return gasMixtureRepository.findAllByNameGasMixture(nameGasMixture);
    }

    public GasMixture searchGasMixturesInStock(String nameGasMixture,
                                               Integer gasCylinderVolume,
                                               Integer numberOfGasCylinder) {
        return gasMixtureRepository
                .findByNameGasMixtureAndGasСylinderVolumeAndNumberOfGasCylinderGreaterThanEqual
                        (nameGasMixture, gasCylinderVolume, numberOfGasCylinder);
    }

    public boolean checkGasMixtureInStock(String nameGasMixture,
                                          Integer gasCylinderVolume,
                                          Integer numberOfGasCylinder) {
        GasMixture gasMixture = gasMixtureRepository
                .findByNameGasMixtureAndGasСylinderVolumeAndNumberOfGasCylinderGreaterThanEqual
                        (nameGasMixture, gasCylinderVolume, numberOfGasCylinder);

        if(gasMixture == null) {
            return false;
        } else return gasMixture.getStatus().equals(StatusGasMixture.ACCEPTED_TO_STOCK.getStatus());
    }

    public GasMixture createGasMixture(String nameGasMixture,
                                       Integer gasСylinderVolume,
                                       Integer numberOfGasCylinder,
                                       String responsibleUserName,
                                       Long responsibleUserId,
                                       Long idEmployeeFilligArea) {

        GasMixture gasMixture = new GasMixture(nameGasMixture, gasСylinderVolume, numberOfGasCylinder,
                responsibleUserName, responsibleUserId, idEmployeeFilligArea);
        gasMixtureRepository.save(gasMixture);
        return gasMixture;
    }

    public void addCylindersToGasMixture(Long id, Integer amount) {
       GasMixture changeableGasMixture = gasMixtureRepository.findById(id).orElse(null);
       changeableGasMixture.setNumberOfGasCylinder(changeableGasMixture.getNumberOfGasCylinder() + amount);
       gasMixtureRepository.save(changeableGasMixture);
    }

    public void removeCylindersFromGasMixture(Long id, Integer amount) {
        GasMixture changeableGasMixture = gasMixtureRepository.findById(id).orElse(null);
        changeableGasMixture.setNumberOfGasCylinder(changeableGasMixture.getNumberOfGasCylinder() - amount);
        gasMixtureRepository.save(changeableGasMixture);
    }

    public void changeStatus(GasMixture gasMixture, String newStatus) {
        gasMixture.setStatus(newStatus);
        saveGasMixture(gasMixture);
    }

    public boolean checkingStockEmptiness() {
        return findAll().isEmpty();
    }

}
