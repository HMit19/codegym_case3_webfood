package vn.codegym.service.product;

import vn.codegym.model.Product;
import vn.codegym.service.DatabaseHandle;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    DatabaseHandle databaseHandle = null;

    public ProductService() {
        databaseHandle = new DatabaseHandle();
    }

    @Override
    public List<Product> getListFood() {
        List<Product> listProduct = new ArrayList<>();
        try {
            ResultSet result = databaseHandle.getRecords("*", "product", "");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("id_product"));
                String name = result.getString("name_product");
                long price = Long.parseLong(result.getString("price_product"));
                String image = result.getString("image_product");
                String description = result.getString("description");
                boolean is_available = Integer.valueOf(result.getString("available")) > 0;
                int id_category = Integer.valueOf(result.getString("id_category"));
                Product product = new Product(id, name, price, image, description, is_available, id_category);
                System.out.println(product);
                listProduct.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listProduct;
    }

    @Override
    public Product findFoodById(int id) {
        Product product = null;
        try {
            ResultSet result = databaseHandle.getRecords("*", "product", "id_product = " + id);
            while (result.next()) {
                int id_product = Integer.valueOf(result.getString("id_product"));
                String name = result.getString("name_product");
                long price = Long.parseLong(result.getString("price_product"));
                String image = result.getString("image_product");
                String description = result.getString("description");
                boolean is_available = Integer.valueOf(result.getString("available")) > 0;
                int id_category = Integer.valueOf(result.getString("id_category"));
                product = new Product(id_product, name, price, image, description, is_available, id_category);
                System.out.println(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<Product> getListFoodByCategory(int id_category) {
        List<Product> listProduct = new ArrayList<>();
        try {
            ResultSet result = databaseHandle.getRecords("*", "product", "id_category = " + id_category);
            while (result.next()) {
                int id = Integer.parseInt(result.getString("id_product"));
                String name = result.getString("name_product");
                long price = Long.parseLong(result.getString("price_product"));
                String image = result.getString("image_product");
                String description = result.getString("description");
                boolean is_available = Integer.parseInt(result.getString("available")) > 0;
                Product product = new Product(id, name, price, image, description, is_available, id_category);
                System.out.println(product);
                listProduct.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listProduct;
    }

    @Override
    public Product findFoodByName(String name) {
        return null;
    }

    @Override
    public boolean removeFood(int id) {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean check = false;
        try {
            check = databaseHandle.updateRecord("product", "name_product = '" + product.getName_product() + "', price_product = "
                    + product.getPrice_product() + " , image_product = '" + product.getImage_product() + "', description = '" + product.getDescription()
                    + "', available = " + product.isAvailable() + ", id_category = " + product.getId_category(), "id_product = " + product.getId_product());
            System.out.println("Update product status: " + check);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean check = false;
        try {
            check = databaseHandle.addRecord("product", "name_product, price_product, image_product, description, available, id_category", "'" + product.getName_product() + "', " + product.getPrice_product() + ", '" + product.getImage_product() + "', '" + product.getDescription() + "', " + product.isAvailable() + ", " + product.getId_category() + "");
            System.out.println("Add product status: " + check);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    //test
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        // test get list product
//        System.out.println(productService.findFoodById("1"));
        // test add product
//        Product product = new Product("test", 1000, "test", "test", true, 1);
//        System.out.println(productService.addProduct(product));
        // test get product
//        System.out.println(productService.findFoodById(495).toString());
        // test update product
//        Product product = new Product(495, "test", 1000, "test", "test", true, 1);
//        System.out.println(productService.updateProduct(product));
//        test get list product by category
//        productService.getListFoodByCategory(1);
    }
}
