package com.springbootProject.Main.global;

import com.springbootProject.Main.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart=new ArrayList<Product>();
    }
}
