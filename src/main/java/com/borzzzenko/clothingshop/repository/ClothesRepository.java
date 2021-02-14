package com.borzzzenko.clothingshop.repository;

import com.borzzzenko.clothingshop.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
}
