package com.chaosconsignment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Item {
	
	private int itemId;
	private String itemName; 
	private int clientId;
	private String itemDescription;
	private int itemCategoryId;  
	private String itemSize; 
	private BigDecimal initialPrice; 
	private BigDecimal minimumPrice; 
	private LocalDateTime postedDate; 
	private LocalDateTime updateDate; 
	
	
	public Item() {};
		
	public Item (int itemId, String itemName, int clientId, String itemDescription, int itemCategoryId, 
			String itemSize, BigDecimal initialPrice, BigDecimal minimumPrice, LocalDateTime postedDate,  
			LocalDateTime updateDate) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.clientId = clientId;
		this.itemDescription = itemDescription;
		this.itemCategoryId = itemCategoryId;
		this.itemSize = itemSize;
		this.initialPrice = initialPrice;
		this.minimumPrice = minimumPrice;
		this.postedDate = postedDate;
		this.updateDate = updateDate;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(int itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public BigDecimal getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}

	public BigDecimal getMinimumPrice() {
		return minimumPrice;
	}

	public void setMinimumPrice(BigDecimal minimumPrice) {
		this.minimumPrice = minimumPrice;
	}

	public LocalDateTime getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDateTime postedDate) {
		this.postedDate = postedDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
}
