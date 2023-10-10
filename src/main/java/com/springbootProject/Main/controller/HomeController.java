package com.springbootProject.Main.controller;

import com.springbootProject.Main.global.GlobalData;
import com.springbootProject.Main.service.CategoryService;
import com.springbootProject.Main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/","/home"})
    public String home(Model model){
        model.addAttribute("cartSize", GlobalData.cart.size());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories",categoryService.getCat());
        model.addAttribute("products",productService.getProduct());
        model.addAttribute("cartSize",GlobalData.cart.size());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String viewProductByCat(Model model, @PathVariable int id){
        model.addAttribute("categories",categoryService.getCat());
        model.addAttribute("products",productService.getProductByCatId(id));
        model.addAttribute("cartSize",GlobalData.cart.size());
        return "shop";
    }


    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id){
        model.addAttribute("product",productService.getProductById(id).get());
        model.addAttribute("cartSize",GlobalData.cart.size());
        return "viewProduct";
    }

}
