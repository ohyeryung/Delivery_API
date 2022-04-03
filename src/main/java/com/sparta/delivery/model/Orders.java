package com.sparta.delivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Orders {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column (nullable = false)
    public String restaurantName;

    @Column (nullable = false)
    private int deliveryFee;

    @Column (nullable = false)
    private int totalPrice;

    @OneToMany
    private List<FoodOrder> foodOrders;

    public Orders(String restaurantName, int deliveryFee, int totalPrice, List<FoodOrder> foodOrderList) {
        this.restaurantName = restaurantName;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.foodOrders = foodOrderList;
    }
}