package com.borzzzenko.clothingshop.service;

import com.borzzzenko.clothingshop.model.Order;
import com.borzzzenko.clothingshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public void saveOrder(Order order) {
        repository.save(order);
    }
}
