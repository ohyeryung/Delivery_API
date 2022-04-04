package com.sparta.delivery.controller;

import com.sparta.delivery.dto.OrderDto;
import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping("/order/request")
    public OrderDto registOrder(@RequestBody OrderRequestDto requestDto) {
        return ordersService.registOrder(requestDto);
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        List<OrderDto> ordersList = ordersService.getOrders();
        return ordersList;
    }
}
