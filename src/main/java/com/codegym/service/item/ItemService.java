package com.codegym.service.item;

import com.codegym.model.Item;
import com.codegym.model.Product;
import com.codegym.service.DatabaseHandle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements IItemService {
    DatabaseHandle databaseHandler;

    public ItemService() {
        databaseHandler = new DatabaseHandle();
    }

    @Override
    public boolean addItem(Item item) {
        boolean check = false;
        try {
//            INSERT INTO webfood.item (id_product, id_bill, quantity, note, price) VALUES (5, 2, 1, null, '111')
            check = databaseHandler.addRecord("item", "id_product, id_bill, quantity, note, price",
                    item.getId_product() + ", " + item.getId_bill() + ", " + item.getQuantity() + ", '" + item.getNote() + "', " + item.getPrice());
            System.out.println("Add item status: " + check);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public boolean removeItem(int id) {
        try {
            return databaseHandler.deleteRecord("item", "id_bill = " + id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item findItemById(int id) {
        return null;
    }

    @Override
    public List<Item> getListItemInBill(int id) {
        List<Item> listItem = null;
        try {
            listItem = new ArrayList<>();
            ResultSet result = databaseHandler.getRecords("*", "item join product on item.id_product = product.id_product", "id_bill = " + id);
            while (result.next()) {

                int id_product = result.getInt("item.id_product");
                int idBill = result.getInt("id_bill");
                int quantity = result.getInt("quantity");
                long priceProduct = result.getLong("price_product");
                String note = result.getString("note");
                String nameProduct = result.getString("name_product");
                String imageProduct = result.getString("image_product");
                boolean available = result.getBoolean("available");
                String description = result.getString("description");
                int id_category = result.getInt("id_category");
                Product product = new Product(id_product, nameProduct, priceProduct, imageProduct, description, available, id_category);
                listItem.add(new Item(id_product, idBill, quantity, note, priceProduct, product));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listItem;
    }

    // test bill service
    public static void main(String[] args) {
        ItemService itemService = new ItemService();
        // test get list item
        List<Item> listItem = itemService.getListItemInBill(1);
        for (Item item : listItem) {
            System.out.println(item.toString());
        }

    }
}
