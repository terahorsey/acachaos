package com.chaosconsignment.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.chaosconsignment.model.Client;
import com.chaosconsignment.model.Item;
import com.chaosconsignment.model.ItemSold;
import com.chaosconsignment.service.ClientService;
import com.chaosconsignment.service.ItemService;
import com.chaosconsignment.service.ItemSoldService;

@Path("/chaosconsignment")
public class ChaosConsignmentController {
	
	
	@GET
	@Path("/getclients")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Client> getAllClients() {
		ClientService service = new ClientService();
		return service.getAllClients();
	}
	
	@POST
	@Path("/addclient")
	@Produces({MediaType.APPLICATION_JSON})
	public Client addClient(Client newClient) {
		ClientService service = new ClientService();
		return service.addClient(newClient);
	}
	
	@PUT
	@Path("/updateclient")
	@Produces({MediaType.APPLICATION_JSON})
	public Client updateClient(Client updateClient) {
		ClientService service = new ClientService();
		return service.updateClient(updateClient);
	}
	
	@GET
	@Path("/clientid/{clientId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Client> getByClientId(@PathParam("clientId") int clientId) {
		ClientService service = new ClientService();
		return service.getByClientId(clientId);
	}
	
		
	@GET
	@Path("/getitems")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Item> getAllItems() {
		ItemService service = new ItemService();
		return service.getAllItems();
	}
	
	@POST
	@Path("/additem")
	@Produces({MediaType.APPLICATION_JSON})
	public Item addItem(Item newItem) {
		ItemService service = new ItemService();
		return service.addItem(newItem);
	}
	
	@GET
	@Path("/itemid/{itemId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Item> getByItemId(@PathParam("itemId") int itemId) {
		ItemService service = new ItemService();
		return service.getByItemId(itemId);
	}
	
	@GET
	@Path("/itembyclientid/{clientId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Item> getItemByClientId(@PathParam("clientId") int clientId) {
		ItemService service = new ItemService();
		return service.getItemByClientId(clientId);
	}
	
	@GET
	@Path("/itemcategoryid/{itemCategoryId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Item> getItemByItemCategoryId(@PathParam("itemCategoryId") int itemCategoryId) {
		ItemService service = new ItemService();
		return service.getItemByItemCategoryId(itemCategoryId);
	}
	
	@DELETE
	@Path("/delete/{itemId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Item> deleteByItemId(@PathParam("itemId") int itemId) {
		ItemService service = new ItemService();
		return service.deleteByItemId(itemId);
	}
	
	@PUT
	@Path("/updateitem")
	@Produces({MediaType.APPLICATION_JSON})
	public Item updateItem(Item updateItem) {
		ItemService service = new ItemService();
		return service.updateItem(updateItem);
	}
	
	@GET
	@Path("/getitemssold")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ItemSold> getAllItemsSold() {
		ItemSoldService service = new ItemSoldService();
		return service.getAllItemsSold();
	}
	
	@POST
	@Path("/additemsold")
	@Produces({MediaType.APPLICATION_JSON})
	public ItemSold addItemSold(ItemSold newItemSold) {
		ItemSoldService service = new ItemSoldService();
		return service.addItemSold(newItemSold);
	}
	
	@GET
	@Path("/itemsoldid/{itemSoldId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ItemSold> getByItemSoldId(@PathParam("itemSoldId") int itemSoldId) {
		ItemSoldService service = new ItemSoldService();
		return service.getByItemSoldId(itemSoldId);
	}
	
	@GET
	@Path("/itemsoldbyitemid/{itemId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ItemSold> getItemSoldByItemId(@PathParam("itemId") int itemId) {
		ItemSoldService service = new ItemSoldService();
		return service.getItemSoldByItemId(itemId);
	}
	
	@GET
	@Path("/itemsoldbyclientid/{clientId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ItemSold> getItemSoldByClientId(@PathParam("clientId") int clientId) {
		ItemSoldService service = new ItemSoldService();
		return service.getItemSoldByClientId(clientId);
	}
	
	@GET
	@Path("/itemsoldbywebsiteid/{websiteId}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ItemSold> getByWebSiteId(@PathParam("websiteId") int websiteId) {
		ItemSoldService service = new ItemSoldService();
		return service.getByWebSiteId(websiteId);
	}
	
	@PUT
	@Path("/updateitemsold")
	@Produces({MediaType.APPLICATION_JSON})
	public ItemSold updateItemSold(ItemSold updateItemSold) {
		ItemSoldService service = new ItemSoldService();
		return service.updateItemSold(updateItemSold);
	}
	
	
	
	
	

}
