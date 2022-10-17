package com.codegym.service.item;

import com.codegym.model.Item;

import java.util.List;

public interface IItemService {
    public boolean addItem(Item item);
    public boolean updateItem(Item item);
    public boolean removeItem(int id);
    public Item findItemById(int id);
    public List<Item> getListItemInBill(int id);
}
