package com.sparta.delivery.model;

import com.sparta.delivery.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Long restaurantId;

    public Food(FoodRequestDto requestDto, Long restaurantId) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = restaurantId;
    }
}
