package com.borzzzenko.clothingshop.repository;

import com.borzzzenko.clothingshop.model.ClothesCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesCategoryRepository extends JpaRepository<ClothesCategory, Long> {
}
