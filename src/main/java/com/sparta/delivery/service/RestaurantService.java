package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.dto.RestaurantRequestDto;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public Restaurant registRestaurant(RestaurantRequestDto requestDto) {

        // 최소 주문 가격(minOrderPrice)
        // 1. 허용값 : 1,000원 ~ 100,000원
        if (requestDto.getMinOrderPrice() < 1000 || requestDto.getMinOrderPrice() > 100000)
            throw new IllegalArgumentException();

        // 2. 100원 단위로만 입력 가능 (ex. 2,220원 입력 시 에러발생, 2,300원은 가능)
        if (requestDto.getMinOrderPrice() % 100 != 0)
            throw new IllegalArgumentException();

        // 3. 허용값이 아니거나 100원 단위가 아닌 경우 에러발생

        // 기본 배달비 (deliveryFee)
        // 1. 허용값 : 0원 ~ 10,000원
        if (requestDto.getDeliveryFee() < 0 || requestDto.getDeliveryFee() > 10000)
            throw new IllegalArgumentException();

        // 2. 500원 단위로만 입력 가능 (ex. 2,200원 입력 시 에러발생, 2,500원 입력 가능)
        if (requestDto.getDeliveryFee() % 500 != 0)
            throw new IllegalArgumentException();

        // 3. 허용값이 아니거나 1,000원 단위 입력이 아닌 경우 에러발생

        Restaurant restaurant = new Restaurant(requestDto);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getListRestaurant() {
        return restaurantRepository.findAll();
    }
}
