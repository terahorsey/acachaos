package com.chaosconsignment.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.chaosconsignment.dao.ClientDao;
import com.chaosconsignment.dao.ItemDao;
import com.chaosconsignment.model.Client;
import com.chaosconsignment.model.Error;
import com.chaosconsignment.model.Item;

public class ItemService {
	
	private ItemDao dao = new ItemDao();
		
	public List<Item> getAllItems() {
		return dao.getAllItems();
	}
	
	public Item addItem(Item newItem) {
		ClientService validateMethod = new ClientService();
		validateMethod.validateClientId(newItem.getClientId());
		validateItemCategoryId(newItem.getItemCategoryId());
		Item rowCount = dao.addItem(newItem);
		System.out.println("Number of item inserts: " + rowCount);
		return newItem;
	}
	
	public List<Item> deleteByItemId(int itemId) {
		validateItemId(itemId);
		System.out.println("Item Id Number deleted: " + itemId);
		return dao.deleteByItemId(itemId);
	}
	
	public List<Item> getByItemId(int itemId) {
		validateItemId(itemId);
		return dao.getByItemId(itemId);
	}
	
	public List<Item> getItemByClientId(int clientId) {
		ClientService validateMethod = new ClientService();
		validateMethod.validateClientId(clientId);
		return dao.getItemByClientId(clientId);
	}
	
	public boolean validateItemId(int itemId) {
		boolean valid = false;
		
		if (0 != itemId) {
			List<Item> itemsByItemId = dao.getByItemId(itemId);
			if (itemsByItemId.size() == 1) {
				valid = true;
			}
		}
		if (!valid) {
			Error error = new Error(100, "Invalid value for ItemId or ItemId not found");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		return valid;
	}
	
	public List<Item> getItemByItemCategoryId(int itemCategoryId) {
		validateItemCategoryId(itemCategoryId);
		return dao.getItemByItemCategoryId(itemCategoryId);
	}
	
	public boolean validateItemCategoryId(int itemCategoryId) {
		boolean valid = false;
		
		if (itemCategoryId >= 1 && itemCategoryId <= 23) {
			valid = true;
		}
		
		if (!valid) {
			Error error = new Error(100, "Invalid value for ItemCategoryId or ItemCategoryId not found");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		return valid;
		}
	
	public Item updateItem(Item updateItem) {
		validateItemId(updateItem.getItemId());
		return dao.updateItem(updateItem);
	}
		
}
