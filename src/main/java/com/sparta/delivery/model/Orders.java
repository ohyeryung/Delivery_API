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

    // Orders Entity를 기준으로 FoodOrder가 여러개 이므로 OneToMany사용
    @OneToMany
    private List<FoodOrder> foodOrders;

    public Orders(String restaurantName, int deliveryFee, int totalPrice, List<FoodOrder> foodOrderList) {
        this.restaurantName = restaurantName;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.foodOrders = foodOrderList;
    }
}