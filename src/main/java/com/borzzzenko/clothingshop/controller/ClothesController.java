package com.borzzzenko.clothingshop.controller;

import com.borzzzenko.clothingshop.model.Clothes;
import com.borzzzenko.clothingshop.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClothesController {
    @Autowired
    private ClothesService clothesService;

    @GetMapping("/")
    public String clothesList(Model model) {
        List<Clothes> clothesList = clothesService.findAll();

        model.addAttribute("clothes", clothesList);

        return "index";
    }

    @GetMapping("/product/{id}")
    public String clothesInfo(Model model, @PathVariable("id") Long id) {
        Clothes clothes = clothesService.findById(id);

        model.addAttribute("clothes", clothes);

        return "product";
    }

    @GetMapping("/admin")
    public String clothesListAdmin(Model model) {
        List<Clothes> clothesList = clothesService.findAll();

        model.addAttribute("clothes", clothesList);

        return "admin";
    }
}
