package com.chaosconsignment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Client {

	private int clientId;
	public String firstName;
	public String lastName;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String phone;
	private String email;
	private BigDecimal agreedCommission;
	private int itemsPosted;
	private int itemsSold;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
		
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getAgreedCommission() {
		return agreedCommission;
	}
	public void setAgreedCommission(BigDecimal agreedCommission) {
		this.agreedCommission = agreedCommission;
	}
	public int getItemsPosted() {
		return itemsPosted;
	}
	public void setItemsPosted(int itemsPosted) {
		this.itemsPosted = itemsPosted;
	}
	public int getItemsSold() {
		return itemsSold;
	}
	public void setItemsSold(int itemsSold) {
		this.itemsSold = itemsSold;
	}
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
		
	
}
