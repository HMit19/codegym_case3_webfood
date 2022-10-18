package com.codegym.service.item;

import com.codegym.model.Item;

import java.util.List;

public interface IItemService {
	boolean addItem(Item item);

	boolean updateItem(Item item);

	boolean removeItem(int id);

	Item findItemById(int id);

	List<Item> getListItemInBill(int id);
}
