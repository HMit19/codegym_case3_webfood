package vn.codegym.service.product;

import vn.codegym.model.Product;

import java.util.List;

public interface IProductService {
	List<Product> getListFood();

	Product findFoodById(int id);

	List<Product> getListFoodByCategory(int id_category);

	Product findFoodByName(String name);

	boolean removeFood(int id);

	boolean updateProduct(Product product);

	boolean addProduct(Product product);
}
