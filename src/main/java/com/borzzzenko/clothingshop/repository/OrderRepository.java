package com.borzzzenko.clothingshop.repository;

import com.borzzzenko.clothingshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
