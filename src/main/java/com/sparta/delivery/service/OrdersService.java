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
                () -> new IllegalArgumentException("음식점 정보가 존재하지 않습니다.")
        );

        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();
        int totalPrice = 0;

        // DB 저장하기 위한 FoodOrder List
        List<FoodOrder> FoodOrderList = new ArrayList<>();

        // 응답 보내기 위한 LIST
        List<FoodOrderDto> FoodOrderDto = new ArrayList<>();

        for (FoodOrderRequestDto foodOrderRequestDto : requestDto.getFoods()) {
            Food food = foodRepository.findById(foodOrderRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("음식 정보가 존재하지 않습니다.")
            );

            int quantity = foodOrderRequestDto.getQuantity();

            extracted(quantity);

            // foodOrder에 food(name, price, restaurantId)와 quantity 저장 (DB용)
            FoodOrder foodOrder = new FoodOrder(food, quantity);

            totalPrice += foodOrder.getPrice();

            FoodOrderList.add(foodOrder);

            foodOrderRepository.save(foodOrder);
//--------------------------------------------------------------------------------------------------------------

            // foodsOrderDto에 음식name, quantity, totalPrice 저장 (Controller용)
            FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), quantity, foodOrder.getPrice());

            FoodOrderDto.add(foodOrderDto);

        }

        // "주문 음식 가격들의 총합"이 주문 음식점의 "최소주문 가격" 을 넘지 않을 시 에러 발생
        if (totalPrice < restaurant.getMinOrderPrice())
            throw new IllegalArgumentException("최소 주문 금액을 넘지 않았습니다.");

        totalPrice += deliveryFee;

        Orders orders = new Orders(restaurantName, deliveryFee, totalPrice, FoodOrderList);

        OrderDto orderDto = new OrderDto(restaurantName, deliveryFee, totalPrice, FoodOrderDto);

        ordersRepository.save(orders);

        return orderDto;
    }

    private void extracted(int quantity) {
        // 주문수량(quantity) 허용값(1 ~ 100)이 아니면 에러 발생
        if (quantity < 1 || quantity > 100)
            throw new IllegalArgumentException();
    }

    // 주문 조회하기
    public List<Orders> getOrders() {
        return ordersRepository.findAll();

//        // Orders의 전체 주문 정보 가져오기
//        List<Orders> orders = ordersRepository.findAll();
//
//        // OrderDto 형식의 List 생성해주기 (빈 값)
//        List<OrderDto> ordersList = new ArrayList<>();
//
//        // 전체 주문 정보(Orders) List 중 하나만 찾기, 하나에 List에 해당하는 값 : orders1
//        for (Orders orders1 : orders) {
//            String restaurantName = orders1.getRestaurantName();
//            int deliveryFee = orders1.getDeliveryFee();
//            int totalPrice = orders1.getTotalPrice();
//
//            // FoodOrderDto 형식의 List 생성 (빈 값)
//            List<FoodOrderDto> foodOrderDtos = new ArrayList<>();
//
//            // 전체 음식 정보(FoodOrder) List 중 하나만 찾기 , 하나의 List에 해당하는 값 : foodOrder
//            for (Foods foodOrder : orders1.getFoodOrders()) {
//                String name = foodOrder.getName();
//                int quantity = foodOrder.getQuantity();
//                int price = foodOrder.getPrice();
//
//                // 빈 List에 값 넣어주기
//                FoodOrderDto foodOrderDto = new FoodOrderDto(name, quantity, price);
//                foodOrderDtos.add(foodOrderDto);
//            }
//            // 빈 List에 값 넣어주기
//            OrderDto orders2 = new OrderDto(restaurantName, deliveryFee,totalPrice, foodOrderDtos);
//            ordersList.add(orders2);

    }
}

