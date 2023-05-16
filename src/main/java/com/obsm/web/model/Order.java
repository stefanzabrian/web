package com.obsm.web.model;

import com.obsm.web.model.constant.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "date_of_order")
    private Date dateOfOrder;
    @Column(name = "order_status")
    private OrderStatus status;
    @Column(name = "total_amount")
    private Integer totalAmount;
    @ManyToOne
    @JoinColumn(name = "client_profile_id")
    private ClientProfile clientProfile;

    public Order() {
    }

    public Order(String orderNumber, Date dateOfOrder, OrderStatus status, Integer totalAmount) {
        this.orderNumber = orderNumber;
        this.dateOfOrder = dateOfOrder;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ClientProfile getClientProfile() {
        return clientProfile;
    }

    public void setClientProfile(ClientProfile clientProfile) {
        this.clientProfile = clientProfile;
    }
}
