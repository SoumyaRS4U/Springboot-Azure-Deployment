package com.springbootProject.Main.controller;

import com.springbootProject.Main.dto.ProductDTO;
import com.springbootProject.Main.model.Category;
import com.springbootProject.Main.model.Product;
import com.springbootProject.Main.service.CategoryService;
import com.springbootProject.Main.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class adminController {


    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    public static  String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategory(Model model){
        model.addAttribute("categories",categoryService.getCat());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCategories(Model model){
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategories(@ModelAttribute("category")Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategories(@PathVariable int id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategories(@PathVariable int id,Model model){
      Optional<Category> category=categoryService.updateCategory(id) ;
      if(category.isPresent()){
          model.addAttribute("category",category.get());
          return "categoriesAdd";
      }else{
          return "404";
      }
    }
    //PRODUCT
    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products",productService.getProduct());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String addProductGet(Model model){
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getCat());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public  String addProductPost(@ModelAttribute("productDTO") ProductDTO productDTO,@RequestParam("productImage") MultipartFile file,@RequestParam("imgName") String imgName) throws IOException {
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.updateCategory(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if (!file.isEmpty()){
            imageUUID=file.getOriginalFilename();
            Path fileNameAndPath= Paths.get(uploadDir,imageUUID);
            Files.write(fileNameAndPath,file.getBytes());

        }else {
            imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id,Model model){
        Product product=productService.getProductById(id).get();
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        model.addAttribute("categories",categoryService.getCat());
        model.addAttribute("productDTO",productDTO);
        return "productsAdd";
    }





}
