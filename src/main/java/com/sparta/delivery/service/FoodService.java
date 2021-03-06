package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Transactional
    // 메뉴 등록
    public void registFood(Long restaurantId, List<FoodRequestDto> requestDto) {

        for (FoodRequestDto foodRequestDto : requestDto) {

            // 가격(price)
            if (foodRequestDto.getPrice() < 100 || foodRequestDto.getPrice() > 1000000)
                throw new IllegalArgumentException();
            if(foodRequestDto.getPrice() % 100 != 0)
                throw new IllegalArgumentException();

            extracted(restaurantId, foodRequestDto);

            Food food = new Food(foodRequestDto, restaurantId);
            foodRepository.save(food);

        }

    }

    private void extracted(Long restaurantId, FoodRequestDto foodRequestDto) {
        // 음식명 중복
        List<Food> foodList = foodRepository.findByRestaurantIdAndName(restaurantId, foodRequestDto.getName());
        if (foodList.size() > 0) {
            throw new IllegalArgumentException();
        }
    }

    // 메뉴판 조회
    public List<Food> getListFood(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
