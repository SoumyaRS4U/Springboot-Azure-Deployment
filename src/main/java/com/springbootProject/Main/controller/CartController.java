package com.springbootProject.Main.controller;

import com.springbootProject.Main.global.GlobalData;
import com.springbootProject.Main.model.Product;
import com.springbootProject.Main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";

    }
    @GetMapping("/cart")
    public String getCart(Model model){
        model.addAttribute("cart",GlobalData.cart);
        model.addAttribute("cartSize",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "cart";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String removeCart(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";

    }
    @GetMapping("/checkout")
    public String checkOutPage(Model model){
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";

    }

}
