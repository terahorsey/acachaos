package com.chaosconsignment.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemSold {
	
	private int itemSoldId;
	private int itemId;
	private LocalDate soldDate;
	private BigDecimal priceSold;
	private BigDecimal agreedCommission;
	private BigDecimal commissionAmount;
	private boolean commissionPaid;
	private int websiteId;
	private int clientId;
	
	public ItemSold() {};
		
	public ItemSold(int itemSoldId, int itemId, LocalDate soldDate, BigDecimal priceSold,
			BigDecimal agreedCommission, BigDecimal commissionAmount, boolean commissionPaid,
			int websiteId, int clientId) {
		this.itemSoldId = itemSoldId;
		this.itemId = itemId;
		this.soldDate = soldDate;
		this.priceSold = priceSold;
		this.agreedCommission = agreedCommission;
		this.commissionAmount = commissionAmount;
		this.commissionPaid = commissionPaid;
		this.websiteId = websiteId;
		this.clientId = clientId;
	}
	
	public int getItemSoldId() {
		return itemSoldId;
	}

	public void setItemSoldId(int itemSoldId) {
		this.itemSoldId = itemSoldId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public LocalDate getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(LocalDate soldDate) {
		this.soldDate = soldDate;
	}

	public BigDecimal getPriceSold() {
		return priceSold;
	}

	public void setPriceSold(BigDecimal priceSold) {
		this.priceSold = priceSold;
	}

	public BigDecimal getAgreedCommission() {
		return agreedCommission;
	}

	public void setAgreedCommission(BigDecimal agreedCommission) {
		this.agreedCommission = agreedCommission;
	}

	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public boolean getCommissionPaid() {
		return commissionPaid;
	}

	public void setCommissionPaid(boolean commissionPaid) {
		this.commissionPaid = commissionPaid;
	}

	public int getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
