package com.codegym.service.product;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getListFood();
    public Product findFoodById(int id);
    public List<Product> getListFoodByCategory(int id_category);
    public Product findFoodByName(String name);
    public boolean removeFood(int id);
    public boolean updateProduct(Product product);
    public boolean addProduct(Product product);
}
