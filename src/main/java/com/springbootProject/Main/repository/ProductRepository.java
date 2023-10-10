package com.springbootProject.Main.repository;

import com.springbootProject.Main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByCategory_id(int id);

    List<Product> findAllById(int id);
}
