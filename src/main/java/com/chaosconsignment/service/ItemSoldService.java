package com.chaosconsignment.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.chaosconsignment.dao.ItemSoldDao;
import com.chaosconsignment.model.Error;
import com.chaosconsignment.model.Item;
import com.chaosconsignment.model.ItemSold;

public class ItemSoldService {
	
	private ItemSoldDao dao = new ItemSoldDao();
	
	public List<ItemSold> getAllItemsSold() {
		return dao.getAllItemsSold();
	}
	
	public ItemSold addItemSold(ItemSold newItemSold) {
		ItemService validateIdMethod = new ItemService();
		validateIdMethod.validateItemId(newItemSold.getItemId());
		
		ClientService validateMethod = new ClientService();
		validateMethod.validateClientId(newItemSold.getClientId());
		
		validateWebsiteId(newItemSold.getWebsiteId());
		
		ItemSold rowCount = dao.addItemSold(newItemSold);
		System.out.println("Number of items sold inserts: " + rowCount);
		return newItemSold;
	}
	
	public boolean validateWebsiteId(int websiteId) {
		boolean valid = false;
		
		if (websiteId >= 1 && websiteId <= 6) {
			valid = true;
		}
		
		if (!valid) {
			Error error = new Error(100, "Invalid value for WebsiteId or WebsiteId not found");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		return valid;
		}

	public List<ItemSold> getByItemSoldId(int itemSoldId) {
		validateItemSoldId(itemSoldId);
		return dao.getByItemSoldId(itemSoldId);
	}

	private boolean validateItemSoldId(int itemSoldId) {
		boolean valid = false;
			
			if (0 != itemSoldId) {
				List<ItemSold> itemsByItemSoldId = dao.getByItemSoldId(itemSoldId);
				if (itemsByItemSoldId.size() == 1) {
					valid = true;
				}
			}
			if (!valid) {
				Error error = new Error(100, "Invalid value for ItemSoldId or ItemSoldId not found");
				Response response = Response.status(400)
						.entity(error)
						.build();
				throw new WebApplicationException(response);
			}
			return valid;
		}
	
	public List<ItemSold> getItemSoldByItemId(int itemId) {
		ItemService validateIdMethod = new ItemService();
		validateIdMethod.validateItemId(itemId);
		return dao.getItemSoldByItemId(itemId);
	}
	
	public List<ItemSold> getItemSoldByClientId(int clientId) {
		ClientService validateMethod = new ClientService();
		validateMethod.validateClientId(clientId);
		return dao.getItemSoldByClientId(clientId);
	}
	
	public List<ItemSold> getByWebSiteId(int websiteId) {
		validateWebsiteId(websiteId);
		return dao.getByWebSiteId(websiteId);
	}
	
	public ItemSold updateItemSold(ItemSold updateItemSold) {
		validateItemSoldId(updateItemSold.getItemSoldId());
		return dao.updateItemSold(updateItemSold);
		
	}
		
}
