package com.example.tracking_service.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tracking",uniqueConstraints = {@UniqueConstraint(columnNames = {"orderId", "itemId"})})
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int orderId;

    private int itemId;

    private String status;

    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
