package com.sparta.delivery.repository;

import com.sparta.delivery.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    // 이름을 비교, 레스토랑 ID, name
    List<Food> findByRestaurantIdAndName(Long restaurantId, String name);

    List<Food> findAllByRestaurantId(Long restaurantId);
}
