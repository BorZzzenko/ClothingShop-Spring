package com.borzzzenko.clothingshop.service;

import com.borzzzenko.clothingshop.model.ClothesCategory;
import com.borzzzenko.clothingshop.repository.ClothesCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesCategoryService {
    @Autowired
    ClothesCategoryRepository categoryRepository;

    public List<ClothesCategory> findAll() {
        return categoryRepository.findAll();
    }
}
