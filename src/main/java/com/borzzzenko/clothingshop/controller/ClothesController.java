package com.borzzzenko.clothingshop.controller;

import com.borzzzenko.clothingshop.model.*;
import com.borzzzenko.clothingshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class ClothesController {
    @Autowired
    private ClothesService clothesService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private ClothesCategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('read')")
    public String startPageRedirection(Model model, Principal principal) {
        String currentUserName = principal.getName();
        User currentUser = userService.findByUserName(currentUserName).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));

        if (currentUser.getRole().equals(Role.ADMIN))
            return "redirect:/admin";

        return "redirect:/catalog";
    }

    @GetMapping("/catalog")
    @PreAuthorize("hasAuthority('read')")
    public String clothesList(Model model) {
        List<Clothes> clothesList = clothesService.findAll();

        model.addAttribute("clothes", clothesList);

        return "index";
    }

    @GetMapping("/product/{id}")
    @PreAuthorize("hasAuthority('read')")
    public String clothesInfo(Model model, Order order, @PathVariable("id") Long id) {
        Clothes clothes = clothesService.findById(id);
        model.addAttribute("clothes", clothes);

        return "product";
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('read')")
    public String ordersList(Model model, Principal principal) {
        String currentUserName = principal.getName();
        User currentUser = userService.findByUserName(currentUserName).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));

        List<Order> orders = currentUser.getOrders();

        if (orders.size() == 0)
            orders = null;

        model.addAttribute("orders", orders);

        return "orders";
    }

    @PostMapping("/orders/create")
    @PreAuthorize("hasAuthority('read')")
    public String makeOrder(Order order, Principal principal) {
        String currentUserName = principal.getName();
        User currentUser = userService.findByUserName(currentUserName).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));

        order.setOwner(currentUser);
        order.setDate(new Date());

        orderService.saveOrder(order);

        return "redirect:/orders";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('write')")
    public String clothesListAdmin(Model model) {
        List<Clothes> clothesList = clothesService.findAll();

        model.addAttribute("clothes", clothesList);

        return "admin";
    }

    @GetMapping("/product/create")
    @PreAuthorize("hasAuthority('write')")
    public String createClothesForm(Clothes clothes, Model model) {
        model.addAttribute("metaTitle", "Добавление одежды");

        List<Color> colors = colorService.findAll();
        model.addAttribute("colors", colors);

        List<ClothesCategory> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "create_product";
    }

    @PostMapping("/product/create")
    @PreAuthorize("hasAuthority('write')")
    public String createClothes(Clothes clothes, @RequestParam("image") MultipartFile image) throws IOException {
        String path = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

        if (!path.equals(""))
            FileUploadUtil.saveFile(path, image);

        clothes.setImagePath(path);
        clothesService.saveClothes(clothes);

        return "redirect:/admin";
    }

    @PostMapping("/product/delete/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String deleteClothes(Model model, @PathVariable("id") Long id) {
        clothesService.deleteClothesById(id);
        return "redirect:/admin";
    }

    @GetMapping("/product/update/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String updateClothesForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("metaTitle", "Изменение информации об одежде");

        Clothes clothes = clothesService.findById(id);
        model.addAttribute("clothes", clothes);

        List<Color> colors = colorService.findAll();
        model.addAttribute("colors", colors);

        List<ClothesCategory> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "update_product";
    }

    @PostMapping("/product/update")
    @PreAuthorize("hasAuthority('write')")
    public String updateClothes(Clothes clothes, @RequestParam("image") MultipartFile image) throws IOException {
        String path = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

        if (!path.equals(""))
            FileUploadUtil.saveFile(path, image);

        clothes.setImagePath(path);
        clothesService.saveClothes(clothes);

        return "redirect:/admin";
    }
}
