package com.sparta.delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodOrderDto {
    String name;
    int quantity;
    int price;

    public FoodOrderDto(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
