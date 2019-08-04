package com.chaosconsignment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.chaosconsignment.model.Item;
import com.chaosconsignment.model.ItemSold;

public class ItemSoldDao {
	
	private final static String allItemsSoldListColumns = 
			" ItemSoldId, ItemId, SoldDate, PriceSold, AgreedCommission, CommissionAmount, " +  
			" CommissionPaid, WebsiteId, ClientId ";
	
	private static String sqlGetAllItemsSold = 
			" SELECT " + allItemsSoldListColumns +
			" FROM itemssoldlist ";
	
	private static String sqlInsertItemSold = 
			" INSERT INTO itemssoldlist (ItemId, SoldDate, PriceSold, AgreedCommission, " +
			" CommissionAmount, CommissionPaid, WebsiteId, ClientId) " +
			" VALUES " + " (?, ?, ?, ?, (? * ?), ?, ?, ?) ";
	
	private static String sqlGetByItemSoldId = 
			" SELECT " + allItemsSoldListColumns +
			" FROM itemssoldlist " +
			" WHERE ItemSoldId = ? ";
	
	private static String sqlGetItemSoldByItemId = 
			" SELECT " + allItemsSoldListColumns +
			" FROM itemssoldlist " +
			" WHERE ItemId = ? ";
	
	private static String sqlGetItemSoldByClientId = 
			" SELECT " + allItemsSoldListColumns +
			" FROM itemssoldlist " +
			" WHERE ClientId = ? ";

	private static String sqlGetByWebSiteId = 
			" SELECT " + allItemsSoldListColumns +
			" FROM itemssoldlist " +
			" WHERE WebsiteId = ? ";
	
	private static String sqlUpdateItemSold = 
			" UPDATE itemssoldlist " +
			" SET CommissionPaid = ? " +
			" WHERE ItemSoldId = ? ";
	
	public List<ItemSold> getAllItemsSold() {
		List<ItemSold> itemsSold = new ArrayList<ItemSold>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(sqlGetAllItemsSold);
			
			while (result.next()) {
				ItemSold itemSold = makeItemSold(result);
				itemsSold.add(itemSold);
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
		return itemsSold;
	}

	private ItemSold makeItemSold(ResultSet result) throws SQLException {
		
			ItemSold itemSold = new ItemSold();
			itemSold.setItemSoldId(result.getInt("ItemSoldId"));
			itemSold.setItemId(result.getInt("ItemId"));  
			itemSold.setSoldDate(result.getObject("SoldDate", LocalDate.class)); 
			itemSold.setPriceSold(result.getBigDecimal("PriceSold")); 
			itemSold.setAgreedCommission(result.getBigDecimal("AgreedCommission"));   
			itemSold.setCommissionAmount(result.getBigDecimal("CommissionAmount"));  
			itemSold.setCommissionPaid(result.getBoolean("CommissionPaid"));  
			itemSold.setWebsiteId(result.getInt("WebsiteId"));  
			itemSold.setClientId(result.getInt("ClientId"));  
								
			return itemSold;
		}
	
	public ItemSold addItemSold(ItemSold newItemSold) {
		int rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlInsertItemSold);
			statement.setInt(1, newItemSold.getItemId());
			statement.setObject(2, newItemSold.getSoldDate());
			statement.setBigDecimal(3, newItemSold.getPriceSold());
			statement.setBigDecimal(4, newItemSold.getAgreedCommission());
			statement.setBigDecimal(5, newItemSold.getPriceSold());
			statement.setBigDecimal(6, newItemSold.getAgreedCommission());
			statement.setBoolean(7, newItemSold.getCommissionPaid());
			statement.setInt(8, newItemSold.getWebsiteId());
			statement.setInt(9, newItemSold.getClientId());
			
			rowCount = statement.executeUpdate();
			
			newItemSold.setItemId(getLastKey(conn));
			
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
		
		return newItemSold;		
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
	
	public List<ItemSold> getByItemSoldId(int itemSoldId) {
		List<ItemSold> itemsSold = new ArrayList<ItemSold>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
				
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlGetByItemSoldId);
			statement.setInt(1, itemSoldId);
			result = statement.executeQuery();				
					
			while (result.next()) {
				itemsSold.add(makeItemSold(result));
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
			return itemsSold;		
	}

	public List<ItemSold> getItemSoldByItemId(int itemId) {
		List<ItemSold> itemsSold = new ArrayList<ItemSold>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
				
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlGetItemSoldByItemId);
			statement.setInt(1, itemId);
			result = statement.executeQuery();				
					
			while (result.next()) {
				itemsSold.add(makeItemSold(result));
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
			return itemsSold;		
	}
	
	public List<ItemSold> getItemSoldByClientId(int clientId) {
		List<ItemSold> itemsSold = new ArrayList<ItemSold>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
				
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlGetItemSoldByClientId);
			statement.setInt(1, clientId);
			result = statement.executeQuery();				
					
			while (result.next()) {
				itemsSold.add(makeItemSold(result));
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
			return itemsSold;		
	}
	
	public List<ItemSold> getByWebSiteId(int websiteId) {
		List<ItemSold> itemsSold = new ArrayList<ItemSold>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
				
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {				
			statement = conn.prepareStatement(sqlGetByWebSiteId);
			statement.setInt(1, websiteId);
			result = statement.executeQuery();				
					
			while (result.next()) {
				itemsSold.add(makeItemSold(result));
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
			return itemsSold;		
	}
	
	public ItemSold updateItemSold(ItemSold updateItemSold) {
		int rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlUpdateItemSold);
			statement.setBoolean(1, updateItemSold.getCommissionPaid());
			statement.setInt(2, updateItemSold.getItemSoldId());
			
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
		return updateItemSold;
	}

}
