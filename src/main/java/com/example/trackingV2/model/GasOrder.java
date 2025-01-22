package com.example.trackingV2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="orders")
public class GasOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="manager_id")
    private Long managerId;
    @Column(name="client")
    @NotNull(message = "Поле обязательно для заполнения")
    private String client;
    @Column(name="creation_date")
    private LocalDate creationDate;
    @Column(name="deadline")
    private LocalDate deadline;
    @Column(name="gas_mixture")
    @NotBlank(message = "Поле обязательно для заполнения")
    private String gasMixture;
    @Column(name = "gas_cylinder_volume")
    @NotNull(message = "Поле обязательно для заполнения")
    private Integer gasСylinderVolume;
    @Column(name = "number_of_gas_cylinder")
    @NotNull(message = "Поле обязательно для заполнения")
    private Integer numberOfGasCylinder;
    @Column(name = "status")
    private String status;
    @Column(name = "type_order")
    private String typeOrder;
    @Column(name = "responsible_user_name")
    private String responsibleUserName;
    @Column(name = "responsible_user_id")
    private Long responsibleUserId;

    //region constructors


    public GasOrder(Long managerId, String client, String gasMixture, Integer gasСylinderVolume, Integer numberOfGasCylinder, String status, String typeOrder, String responsibleUserName, Long responsibleUserId) {
        this.managerId = managerId;
        this.client = client;
        creationDate = LocalDate.now();
        deadline = creationDate.plusDays(14);
        this.gasMixture = gasMixture;
        this.gasСylinderVolume = gasСylinderVolume;
        this.numberOfGasCylinder = numberOfGasCylinder;
        this.status = status;
        this.typeOrder = typeOrder;
        this.responsibleUserName = responsibleUserName;
        this.responsibleUserId = responsibleUserId;
    }

    public GasOrder() {
    }

    //endregion

    //region getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getGasMixture() {
        return gasMixture;
    }

    public void setGasMixture(String gasMixture) {
        this.gasMixture = gasMixture;
    }

    public Integer getGasСylinderVolume() {
        return gasСylinderVolume;
    }

    public void setGasСylinderVolume(Integer gasСylinderVolume) {
        this.gasСylinderVolume = gasСylinderVolume;
    }

    public Integer getNumberOfGasCylinder() {
        return numberOfGasCylinder;
    }

    public void setNumberOfGasCylinder(Integer numberOfGasCylinder) {
        this.numberOfGasCylinder = numberOfGasCylinder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
    }

    public String getResponsibleUserName() {
        return responsibleUserName;
    }

    public void setResponsibleUserName(String responsibleUserName) {
        this.responsibleUserName = responsibleUserName;
    }

    public Long getResponsibleUserId() {
        return responsibleUserId;
    }

    public void setResponsibleUserId(Long responsibleUserId) {
        this.responsibleUserId = responsibleUserId;
    }

    //endregion

}
