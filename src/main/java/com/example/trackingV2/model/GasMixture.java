package com.example.trackingV2.model;

import com.example.trackingV2.enums.StatusGasMixture;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "gas_mixtures_stock")
public class GasMixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="gas_mixture")
    @NotBlank(message = "Поле обязательно для заполнения")
    private String nameGasMixture;
    @Column(name = "gas_cylinder_volume")
    @NotNull(message = "Поле обязательно для заполнения")
    private Integer gasСylinderVolume;
    @Column(name = "number_of_gas_cylinder")
    @NotNull(message = "Поле обязательно для заполнения")
    private Integer numberOfGasCylinder;
    @Column(name = "status")
    private String status;
    @Column(name = "responsible_user_name")
    private String responsibleUserName;
    @Column(name = "responsible_user_id")
    private Long responsibleUserId;
    @Column(name = "id_employee_fillig_area")
    private Long idEmployeeFilligArea;


    //region constructors


    public GasMixture(String nameGasMixture, Integer gasСylinderVolume, Integer numberOfGasCylinder,
                      String responsibleUserName, Long responsibleUserId,
                      Long idEmployeeFilligArea) {

        this.nameGasMixture = nameGasMixture;
        this.gasСylinderVolume = gasСylinderVolume;
        this.numberOfGasCylinder = numberOfGasCylinder;
        status = StatusGasMixture.CREATED.getStatus();
        this.responsibleUserName = responsibleUserName;
        this.responsibleUserId = responsibleUserId;
        this.idEmployeeFilligArea = idEmployeeFilligArea;
    }

    public GasMixture() {
    }

    //endregion

    //region getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameGasMixture() {
        return nameGasMixture;
    }

    public void setNameGasMixture(String nameGasMixture) {
        this.nameGasMixture = nameGasMixture;
    }

    public Integer getGasСylinderVolume() {
        return gasСylinderVolume;
    }

    public void setGasСylinderVolume(Integer gasСylinderVolume) {
        this.gasСylinderVolume = gasСylinderVolume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumberOfGasCylinder() {
        return numberOfGasCylinder;
    }

    public void setNumberOfGasCylinder(Integer numberOfGasCylinder) {
        this.numberOfGasCylinder = numberOfGasCylinder;
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

    public Long getIdEmployeeFilligArea() {
        return idEmployeeFilligArea;
    }

    public void setIdEmployeeFilligArea(Long idEmployeeFilligArea) {
        this.idEmployeeFilligArea = idEmployeeFilligArea;
    }


    //endregion


    @Override
    public String toString() {
        return "GasMixture{" +
                "id=" + id +
                ", nameGasMixture='" + nameGasMixture + '\'' +
                ", gasСylinderVolume=" + gasСylinderVolume +
                ", numberOfGasCylinder=" + numberOfGasCylinder +
                ", status='" + status + '\'' +
                ", responsibleUserName='" + responsibleUserName + '\'' +
                ", responsibleUserId=" + responsibleUserId +
                ", idEmployeeFilligArea=" + idEmployeeFilligArea +
                '}';
    }
}

