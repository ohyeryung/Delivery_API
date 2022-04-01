package com.sparta.delivery.controller;

import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public Restaurant registRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.registRestaurant(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getListRestaurant() {
        List<Restaurant> restaurantList = restaurantService.getListRestaurant();
        return restaurantList;
    }
}
