package com.borzzzenko.clothingshop.service;

import com.borzzzenko.clothingshop.model.Clothes;
import com.borzzzenko.clothingshop.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesService {
    @Autowired
    private ClothesRepository repository;

    public Clothes findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void saveClothes(Clothes clothes) {
        repository.save(clothes);
    }

    public void deleteClothesById(Long id) {
        repository.deleteById(id);
    }

    public List<Clothes> findAll() {
        return repository.findAll();
    }
}
