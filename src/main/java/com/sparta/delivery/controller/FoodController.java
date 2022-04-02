package com.sparta.delivery.controller;

import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registFood(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> FoodRequestDto) {
        foodService.registFood(restaurantId, FoodRequestDto);
    }

    // 메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getListFood(@PathVariable Long restaurantId) {
        List<Food> foodList = foodService.getListFood(restaurantId);
        return foodList;
    }
}

