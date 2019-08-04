package com.chaosconsignment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.chaosconsignment.model.Client;

public class ClientDao {
	
	private final static String ClientIdColumn = "ClientId";
	
	private final static String allChaosClientColumns = 
			" FirstName, LastName, Address, City, State, ZipCode, Phone, " +
			" Email, AgreedCommission, ItemsPosted, ItemsSold, CreateDate, UpdateDate ";
		
	private final static String sqlSelectAllChaosClients = 
			" SELECT " + allChaosClientColumns + " , " + ClientIdColumn +
			" FROM chaosclient ";
	
	private final static String insertChaosClientColumns = 
			" FirstName, LastName, Address, City, State, ZipCode, Phone, " +
			" Email, AgreedCommission, ItemsPosted, ItemsSold ";
	
	private final static String sqlInsertChaosClient =
			" INSERT INTO chaosclient ( " + insertChaosClientColumns + " ) " +
			" VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
	
	private final static String sqlUpdateItemsByClientId = 
			" UPDATE chaosclient " +
			" SET ItemsPosted = ?, ItemsSold = ? " +
			" WHERE ClientId = ? ";
	
	private final static String sqlGetByClientId = 
			" SELECT ClientId, FirstName, LastName, Address, City, State, ZipCode, Phone, " +
			" Email, AgreedCommission, ItemsPosted, ItemsSold, CreateDate, UpdateDate " +
			" FROM chaosclient " +
			" WHERE ClientId = ? ";
	
	
	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<Client>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(sqlSelectAllChaosClients);
			
			while (result.next()) {
				Client client = makeClient(result);
				clients.add(client);
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
		return clients;
	}

	private Client makeClient(ResultSet result) throws SQLException {
		Client client = new Client();
		
		client.setClientId(result.getInt("ClientId")); 
		client.setFirstName(result.getString("FirstName")); 
		client.setLastName(result.getString("LastName")); 
		client.setAddress(result.getString("Address")); 
		client.setCity(result.getString("City")); 
		client.setState(result.getString("State")); 
		client.setZipCode(result.getString("ZipCode")); 
		client.setPhone(result.getString("Phone")); 
		client.setEmail(result.getString("Email")); 
		client.setAgreedCommission(result.getBigDecimal("AgreedCommission")); 
		client.setItemsPosted(result.getInt("ItemsPosted")); 
		client.setItemsSold(result.getInt("ItemsSold"));
		client.setCreateDateTime(result.getObject("CreateDate", LocalDateTime.class));
		client.setUpdateDateTime(result.getObject("UpdateDate", LocalDateTime.class));
		
		return client;
	} 
	
	public int insertClients(Client newClient) {
		int rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlInsertChaosClient);
			statement.setString(1, newClient.getFirstName());
			statement.setString(2, newClient.getLastName());
			statement.setString(3, newClient.getAddress());
			statement.setString(4, newClient.getCity());
			statement.setString(5, newClient.getState());
			statement.setString(6, newClient.getZipCode());
			statement.setString(7, newClient.getPhone());
			
			if (newClient.getEmail() != null) {
				statement.setString(8, newClient.getEmail());
			} else {
				statement.setNull(8, java.sql.Types.VARCHAR);
			}
			
			statement.setBigDecimal(9, newClient.getAgreedCommission());
			statement.setInt(10, newClient.getItemsPosted());
			statement.setInt(11, newClient.getItemsSold());
			
			rowCount = statement.executeUpdate();
			
			newClient.setClientId(getLastKey(conn));
			
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
		return rowCount;
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
	
	public Client updateClient(Client updateClient) {
		int rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = MariaDbUtilChaos.getConnection();
		
		try {
			statement = conn.prepareStatement(sqlUpdateItemsByClientId);
			statement.setInt(1, updateClient.getItemsPosted());
			statement.setInt(2, updateClient.getItemsSold()); 
			statement.setInt(3, updateClient.getClientId());
			
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
		return updateClient;
	}

	public List<Client> getByClientId(int clientId) {
		List<Client> clients = new ArrayList<Client>();
			
			ResultSet result = null;
			PreparedStatement statement = null;
			
			Connection conn = MariaDbUtilChaos.getConnection();

			try {				
				statement = conn.prepareStatement(sqlGetByClientId);
				statement.setInt(1, clientId);
				result = statement.executeQuery();				
				
				while (result.next()) {
					clients.add(makeClient(result));
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
			
			return clients;		
		}
	
}
