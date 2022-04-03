package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodOrderDto;
import com.sparta.delivery.dto.FoodOrderRequestDto;
import com.sparta.delivery.dto.OrderDto;
import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.FoodOrder;
import com.sparta.delivery.model.Orders;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.FoodOrderRepository;
import com.sparta.delivery.repository.OrdersRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final OrdersRepository ordersRepository;

    // 주문하기
    public OrderDto registOrder(OrderRequestDto requestDto) {

        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException()
        );

        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();
        int totalPrice = deliveryFee;

        // 응답 보내기 위한 LIST
        List<FoodOrderDto> FoodOrderDto = new ArrayList<>();

        // DB 저장하기 위한 FoodOrder List
        List<FoodOrder> FoodOrderList = new ArrayList<>();

        for (FoodOrderRequestDto foodOrderRequestDto : requestDto.getFoods()) {
            Food food  = foodRepository.findById(foodOrderRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException()
            );

            int quantity = foodOrderRequestDto.getQuantity();

            // quantity 조건 주기


            // foodOrder에 food(name, price, restaurantId)와 quantity 저장 (DB용)
            FoodOrder foodOrder = new FoodOrder(food, quantity);

            totalPrice += foodOrder.getPrice();

            FoodOrderList.add(foodOrder);

            foodOrderRepository.save(foodOrder);

//--------------------------------------------------------------------------------------------------------------
            FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), quantity, food.getPrice()*quantity);

            FoodOrderDto.add(foodOrderDto);

        }

        Orders orders = new Orders(restaurantName, deliveryFee, totalPrice, FoodOrderList);

        OrderDto orderDto = new OrderDto(restaurantName, deliveryFee, totalPrice, FoodOrderDto);

        ordersRepository.save(orders);

        return orderDto;

    }
}
