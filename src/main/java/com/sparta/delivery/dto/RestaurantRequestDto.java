package com.sparta.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestaurantRequestDto {
    private String name;
    private Long minOrderPrice;
    private Long deliveryFee;
}
