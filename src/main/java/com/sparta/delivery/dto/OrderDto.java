package com.sparta.delivery.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderDto {
    private String restaurantName;
    private int deliveryFee;
    private int totalPrice;
    private List<FoodOrderDto> foods;

    public OrderDto(String restaurantName, int deliveryFee, int totalPrice, List<FoodOrderDto> foodOrderDto) {
        this.restaurantName = restaurantName;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.foods = foodOrderDto;
    }
}
