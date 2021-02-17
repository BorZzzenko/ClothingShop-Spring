package com.borzzzenko.clothingshop.service;

import com.borzzzenko.clothingshop.model.Color;
import com.borzzzenko.clothingshop.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    ColorRepository colorRepository;

    public List<Color> findAll() {
        return colorRepository.findAll();
    }
}
