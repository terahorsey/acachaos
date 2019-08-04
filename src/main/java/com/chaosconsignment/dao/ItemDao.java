package com.chaosconsignment.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.chaosconsignment.model.Client;
import com.chaosconsignment.model.Item;

public class ItemDao {
	
	private final static String allItemsListColumns = 
			" ItemId, ItemName, ClientId, ItemDescription, ItemCategoryId, ItemSize, " +
			" InitialPrice, MinimumPrice, PostedDate, UpdateDate ";
	
	private static String sqlGetAllItems = 
			" SELECT " + allItemsListColumns + 
			" FROM itemslist ";
	
	private static String sqlInsertItem =
			" INSERT INTO itemslist (ItemName, ClientId, ItemDescription, ItemCategoryId, " + 
			" ItemSize, InitialPrice, MinimumPrice) " +
			" VALUES " + " (?, ?, ?, ?, ?, ?, ?) "; 
	
	private static String sqlDeleteItem = 
			" DELETE FROM itemslist WHERE ItemId = ? ";
	
	private static String sqlGetByItemId = 
			" SELECT " + allItemsListColumns +
			" FROM itemslist " +
			" WHERE ItemId = ? ";
	
	private static String sqlGetItemByClientId = 
			" SELECT " + allItemsListColumns +
			" FROM itemslist " +
			" WHERE ClientId = ? ";
	
	private static String sqlGetItemByItemCategoryId =
			" SELECT " + allItemsListColumns +
			" FROM itemslist " +
			" WHERE ItemCategoryId = ? ";
	
	private static String sqlUpdateItem = 
			" UPDATE itemslist " +
			" SET ItemDescription = ? " +
			" WHERE ItemId = ? ";
	
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(sqlGetAllItems);
			
			while (result.next()) {
				Item item = makeItem(result);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	private Item makeItem(ResultSet result) throws SQLException {
		Item item = new Item();
		item.setItemId(result.getInt("ItemId"));
		item.setItemName(result.getString("ItemName"));  
		item.setClientId(result.getInt("ClientId")); 
		item.setItemDescription(result.getString("ItemDescription")); 
		item.setItemCategoryId(result.getInt("ItemCategoryId"));   
		item.setItemSize(result.getString("ItemSize"));  
		item.setInitialPrice(result.getBigDecimal("InitialPrice"));  
		item.setMinimumPrice(result.getBigDecimal("MinimumPrice"));  
		item.setPostedDate(result.getObject("PostedDate", LocalDateTime.class));  
		item.setUpdateDate(result.getObject("UpdateDate", LocalDateTime.class));
				
		return item;
	}
	
	public Item addItem(Item newItem) {
		int rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlInsertItem);
			statement.setString(1, newItem.getItemName());
			statement.setInt(2, newItem.getClientId());
			statement.setString(3, newItem.getItemDescription());
			statement.setInt(4, newItem.getItemCategoryId());
			statement.setString(5, newItem.getItemSize());
			statement.setBigDecimal(6, newItem.getInitialPrice());
			statement.setBigDecimal(7, newItem.getMinimumPrice());
			
			rowCount = statement.executeUpdate();
			
			newItem.setItemId(getLastKey(conn));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return newItem;		
	}
	
	private Integer getLastKey(Connection conn) throws SQLException {
		Integer key = 0;
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(" SELECT LAST_INSERT_ID() ");
		
		while (result.next()) {
			key = result.getInt("LAST_INSERT_ID()");
		}
		return key;
	}
	
	public List<Item> deleteByItemId(int itemId) {
		List<Item> items = this.getByItemId(itemId);
		int rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlDeleteItem);
			statement.setInt(1, itemId);
			rowCount = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	public List<Item> getByItemId(int itemId) {
		List<Item> items = new ArrayList<Item>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
				
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlGetByItemId);
			statement.setInt(1, itemId);
			result = statement.executeQuery();				
					
			while (result.next()) {
				items.add(makeItem(result));
			}
							
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally {
				try {				
					statement.close();
					conn.close();
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}
			return items;		
	}
	
	public List<Item> getItemByClientId(int clientId) {
		List<Item> items = new ArrayList<Item>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
				
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlGetItemByClientId);
			statement.setInt(1, clientId);
			result = statement.executeQuery();				
					
			while (result.next()) {
				items.add(makeItem(result));
			}
							
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally {
				try {				
					statement.close();
					conn.close();
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}
			return items;		
		}
	
	public List<Item> getItemByItemCategoryId(int itemCategoryId) {
		List<Item> items = new ArrayList<Item>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
				
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlGetItemByItemCategoryId);
			statement.setInt(1, itemCategoryId);
			result = statement.executeQuery();				
					
			while (result.next()) {
				items.add(makeItem(result));
			}
							
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally {
				try {				
					statement.close();
					conn.close();
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}
			return items;		
		}
	
	public Item updateItem(Item updateItem) {
		int rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlUpdateItem);
			statement.setString(1, updateItem.getItemDescription());
			statement.setInt(2, updateItem.getItemId());
			
			rowCount = statement.executeUpdate();
			System.out.println("Rows updated: " + rowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateItem;
	}
	
	
	
}
