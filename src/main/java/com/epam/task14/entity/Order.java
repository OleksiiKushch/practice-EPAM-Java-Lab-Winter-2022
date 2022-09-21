package com.epam.task14.entity;

import com.epam.task11.entity.Entity;
import com.epam.task11.entity.user.User;
import com.epam.task14.util.Cart;

import java.time.LocalDateTime;

public class Order extends Entity {
    private OrderStatus orderStatus;
    private String stateDetail;
    private String delivery;
    private LocalDateTime dateTime;
    private User user;
    private Cart cart;

    public Order(String delivery, User user, Cart cart) {
        this.delivery = delivery;
        this.user = user;
        this.cart = cart;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStateDetail() {
        return stateDetail;
    }

    public void setStateDetail(String stateDetail) {
        this.stateDetail = stateDetail;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", stateDetail='" + stateDetail + '\'' +
                ", delivery='" + delivery + '\'' +
                ", dateTime=" + dateTime +
                ", user=" + user +
                ", cart=" + cart +
                '}';
    }
}
