package com.obsm.web.model;

import com.obsm.web.model.constant.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "date_of_order")
    private Date dateOfOrder;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;
    @Column(name = "total_amount")
    private Double totalAmount;
    @ManyToOne
    @JoinColumn(name = "client_profile_id")
    private ClientProfile clientProfile;

    @OneToMany(mappedBy = "order")
    private List<ProductOrder> productOrders;
    public Order() {
    }

    public Order(String orderNumber, Date dateOfOrder, OrderStatus status, Double totalAmount) {
        this.orderNumber = orderNumber;
        this.dateOfOrder = dateOfOrder;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ClientProfile getClientProfile() {
        return clientProfile;
    }

    public void setClientProfile(ClientProfile clientProfile) {
        this.clientProfile = clientProfile;
    }
}
