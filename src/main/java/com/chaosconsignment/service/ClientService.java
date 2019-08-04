package com.chaosconsignment.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.chaosconsignment.dao.ClientDao;
import com.chaosconsignment.model.Client;
import com.chaosconsignment.model.Error;

public class ClientService {
	
	private ClientDao dao = new ClientDao();
	
	public List<Client> getAllClients() {
		return dao.getAllClients();
	}
	
	public Client addClient(Client newClient) {
		int rowCount = dao.insertClients(newClient);
		System.out.println("Number of client inserts: " + rowCount);
		return newClient;
	}
	
	public List<Client> getByClientId(int clientId) {
		validateClientId(clientId);
		return dao.getByClientId(clientId);
	}
	
	public Client updateClient(Client updateClient) {
		validateClientId(updateClient.getClientId());
		return dao.updateClient(updateClient);
	}
	
	public boolean validateClientId(int clientId) {
		boolean valid = false;
		
		if (0 != clientId) {
			List<Client> clientsByClientId = dao.getByClientId(clientId);
			if (clientsByClientId.size() == 1) {
				valid = true;
			}
		}
		
		if (!valid) {
			Error error = new Error(100, "Invalid value for ClientId or ClientId not found");
			Response response = Response.status(400)
					.entity(error)
					.build();
			throw new WebApplicationException(response);
		}
		return valid;
	}
	
}
