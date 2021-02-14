package com.borzzzenko.clothingshop.controller;

import com.borzzzenko.clothingshop.model.Clothes;
import com.borzzzenko.clothingshop.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClothesController {
    @Autowired
    private ClothesService clothesService;

    @GetMapping("/")
    public String clothesList(Model model) {
        List<Clothes> clothes = clothesService.findAll();

        model.addAttribute("clothes", clothes);

        return "index";
    }
}
