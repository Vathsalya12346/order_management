package com.example.order_management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.order_management.entity.Order;
import com.example.order_management.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setPickupLocation(updatedOrder.getPickupLocation());
            order.setDeliveryLocation(updatedOrder.getDeliveryLocation());
            order.setOrderDate(updatedOrder.getOrderDate());
            order.setDeliveryDate(updatedOrder.getDeliveryDate());
            order.setOrderStatus(updatedOrder.getOrderStatus());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

