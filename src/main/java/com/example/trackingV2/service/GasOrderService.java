package com.example.trackingV2.service;

import com.example.trackingV2.enums.StatusGasOrder;
import com.example.trackingV2.model.GasMixture;
import com.example.trackingV2.model.GasOrder;
import com.example.trackingV2.model.GasOrderArchive;
import com.example.trackingV2.repository.GasOrderArchiveRepository;
import com.example.trackingV2.repository.GasOrderRepository;
import com.example.trackingV2.utils.GasOrderFilter.GasOrderFilter;
import com.example.trackingV2.utils.GasOrderFilter.GasOrderFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class GasOrderService {


    private final GasOrderRepository gasOrderRepository;
    private final GasOrderArchiveRepository gasOrderArchiveRepository;
    private final UserService userService;


    public GasOrderService(GasOrderRepository gasOrderRepository, UserService userService,
                           GasOrderArchiveRepository gasOrderArchiveRepository) {

        this.gasOrderRepository = gasOrderRepository;
        this.userService = userService;
        this.gasOrderArchiveRepository = gasOrderArchiveRepository;
    }

    public List<GasOrder> findAll() {
        return (List<GasOrder>) gasOrderRepository.findAll();
    }

    public List<GasOrder> findAllByTypeOrder(String typeOrder) {
        return gasOrderRepository.findAllByTypeOrder(typeOrder);
    }

    public GasOrder findById(Long id) {
        return gasOrderRepository.findById(id).orElse(null);
    }

    public GasOrder saveOrder(GasOrder order) {
        return gasOrderRepository.save(order);
    }

    public void deleteById(Long id) {
        gasOrderRepository.deleteById(id);
    }

    public List<GasOrder> findAllByClient(String client) {
        return (List<GasOrder>) gasOrderRepository.findAllByClient(client);
    }

    public GasOrder createGasOrder(Long managerId,
                                   String client,
                                   String gasMixture,
                                   Integer gasСylinderVolume,
                                   Integer numberOfGasCylinder,
                                   String typeGasOrder,
                                   String responsibleUserName,
                                   Long responsibleUserId) {
        GasOrder gasOrder = new GasOrder(managerId, client, gasMixture, gasСylinderVolume, numberOfGasCylinder, StatusGasOrder.ORDER_CREATED.getStatus(), typeGasOrder, responsibleUserName, responsibleUserId);
        saveOrder(gasOrder);
        return gasOrder;
    }

    public void changeStatus(GasOrder gasOrder, String newStatus) {
        gasOrder.setStatus(newStatus);
        saveOrder(gasOrder);
    }

    public void changeResponsibleUserId(GasOrder gasOrder, Long responsibleUserId) {
        gasOrder.setResponsibleUserId(responsibleUserId);
        gasOrder.setResponsibleUserName(userService.getLastNameAndFirstLetterOfName(userService.findById(responsibleUserId)));
        saveOrder(gasOrder);
    }

    public List<String> getAllStatusesAsStrings() {
        List<String> allStatuses = new ArrayList<>();
        for (StatusGasOrder status : StatusGasOrder.values()) {
            allStatuses.add(status.getStatus());
        }
        return allStatuses;
    }

    public List<GasOrder> getGasOrdersForRole(String role) {
        GasOrderFilter filter = GasOrderFilterFactory.getGasOrderFilter(role);
        return filter.filterGasOrder(findAll());
    }

    public void moveToArchive(Long id) {
        GasOrder transferableOrder = gasOrderRepository.findById(id).orElse(null);
        if(transferableOrder != null) {
            GasOrderArchive gasOrderArchive = new GasOrderArchive(transferableOrder.getId(),
                    transferableOrder.getManagerId(), transferableOrder.getClient(), transferableOrder.getCreationDate(),
                    transferableOrder.getDeadline(), transferableOrder.getGasMixture(), transferableOrder.getGasСylinderVolume(),
                    transferableOrder.getNumberOfGasCylinder(), transferableOrder.getStatus(), transferableOrder.getTypeOrder(),
                    transferableOrder.getResponsibleUserName(), transferableOrder.getResponsibleUserId());
            gasOrderRepository.deleteById(id);
            gasOrderArchiveRepository.save(gasOrderArchive);
        }
    }

    public List<GasOrder> findByFilter(String field, String value) {
        LocalDate date = null;
        List<GasOrder> gasOrdersFiltered = new ArrayList<>();
        if (field.equals("creationDate") || field.equals("deadline")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(value, formatter);
        }
        switch (field) {
            case "managerId":
                gasOrdersFiltered.addAll(gasOrderRepository.findGasOrderByManagerId(Long.valueOf(value)));
            case "client":
                gasOrdersFiltered.addAll(gasOrderRepository.findGasOrderByClient(value));
            case "deadline":
            case "creationDate":
                gasOrdersFiltered.addAll(gasOrderRepository.findGasOrderByCreationDate(date));
            case "gasMixture":
                gasOrdersFiltered.addAll(gasOrderRepository.findGasOrderByGasMixture(value));
            case "status":
                gasOrdersFiltered.addAll(gasOrderRepository.findGasOrderByStatus(value));
            case "typeOrder":
                gasOrdersFiltered.addAll(gasOrderRepository.findGasOrderByTypeOrder(value));
            case "responsibleUserId":
                gasOrdersFiltered.addAll(gasOrderRepository.findGasOrderByResponsibleUserId(Long.valueOf(value)));
        }

        return gasOrdersFiltered;
    }
}
