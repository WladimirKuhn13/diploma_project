package com.example.trackingV2.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "history_of_changes")
public class HistoryOfChanges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "gas_order_id")
    private Long gasOrderId;
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "status")
    private String status;

    //region constructors

    public HistoryOfChanges(Long gasOrderId, Long employeeId, LocalDateTime date, String status) {
        this.gasOrderId = gasOrderId;
        this.employeeId = employeeId;
        this.date = date;
        this.status = status;
    }

    public HistoryOfChanges() {
    }

    //endregion

    //region getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGasOrderId() {
        return gasOrderId;
    }

    public void setGasOrderId(Long gasOrderId) {
        this.gasOrderId = gasOrderId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //endregion
}
