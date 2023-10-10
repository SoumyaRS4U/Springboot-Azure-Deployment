package com.springbootProject.Main.service;

import com.springbootProject.Main.model.Product;
import com.springbootProject.Main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getProduct(){
        return productRepository.findAll();


    }


    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getProductId(int id){
        return productRepository.findAllById(id);
    }
    public List<Product> getProductByCatId(int id){
        return productRepository.findAllByCategory_id(id);
    }


}
