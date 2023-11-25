package com.zosh.request;

import com.zosh.model.MenuItem;

import lombok.Data;

@Data
public class AddCartItemRequest {
	
	private Long menuItemId;
	public Long getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private int quantity;

}
