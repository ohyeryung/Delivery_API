package com.sparta.delivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private int price;

    @Column (nullable = false)
    private int quantity;

    public FoodOrder(Food food, int quantity) {
        this.name = food.getName();
        this.price = food.getPrice()*quantity;
        this.quantity = quantity;
    }

}